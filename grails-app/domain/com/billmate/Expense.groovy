package com.billmate

import com.lucastex.grails.fileuploader.UFile
import com.nanlabs.grails.plugin.logicaldelete.LogicalDelete
import com.sun.org.apache.xpath.internal.operations.Bool

@LogicalDelete
class Expense {
    static belongsTo = [Circle, RegisteredUser, ExpenseType]
    static hasMany = [debts: Debt, actions: Action, assignedUsers: User]

    RegularExpense regularExpense
    RegisteredUser responsible
    ExpenseType expenseType
    Circle circle
    UFile invoice
    UFile receipt

    String title
    String description
    Double value
    Date beginDate = new Date()
    Date endDate
    Date paymentDeadline
    Date receptionDeadline
    Date createdAt = new Date()
    Date paymentDate
    Date receptionDate
    Boolean isDeleted = false

    static constraints = {
        regularExpense nullable: true
        responsible nullable: false
        invoice nullable: true
        receipt nullable: true
        circle nullable: false
        expenseType nullable: false

        title nullable: false, blank: false
        description maxSize: 2000, nullable: true
        value min: 0D, nullable: false
        beginDate nullable: false
        endDate nullable: true
        paymentDeadline nullable: true
        receptionDeadline nullable: true
        createdAt nullable: false
        paymentDate nullable: true
        receptionDate nullable: true
        isDeleted nullable: false
    }

    public Expense(Map map, RegularExpense rExpense, RegisteredUser rUser) {
        map.each { k,v -> setProperty(k,v) }
        if(!regularExpense) regularExpense = rExpense
        if(!responsible) responsible = rUser
        if(!expenseType) expenseType = rExpense.getExpenseType()
        if(!circle) circle = rExpense.getCircle()
        if(!title) title = rExpense.getTitle()
        if(!description) description = rExpense.getDescription()
        if(!value) value = rExpense.getValue()
        if(!beginDate) beginDate = rExpense.getBeginDate()
        if(!endDate) endDate = rExpense.getEndDate()
        if(!paymentDeadline) paymentDeadline = rExpense.getPaymentDeadline()
        if(!paymentDeadline) paymentDeadline = rExpense.getReceptionDeadline()

        if(!debts || debts.isEmpty()){
            rExpense.getDebts().each { this.addToDebts(it) }
        }
        if(!assignedUsers || assignedUsers.isEmpty()){
            rExpense.getAssignedUsers().each { this.addToAssignedUsers(it) }
        }
    }

    public String toString(){
        title
    }

    public boolean saveAndPostponeRegularExpense(){
        List<String> listUsers = new LinkedList<>()
        List<Double> listValues = new LinkedList<>()
        int position = 0
        Set<User> detailedListOfUsers = regularExpense.getAssignedUsers()
        int numberOfUsers = detailedListOfUsers.size()
        detailedListOfUsers.each { listUsers.add(it.getId().toString()); listValues.add((getValue()/numberOfUsers)) }
        withTransaction { status ->
            try {
                regularExpense.postpone()
                regularExpense.save(flush: true, failOnError: true)
                Expense expense = save(flush: true, failOnError: true)

                for(String str : listUsers){
                    User user = User.findById(Long.parseLong(str))
                    Debt debt = new Debt(value: listValues[position], user: user, expense: expense).save()
                    position++;
                }

                return true
            }catch(Exception ignored){
                status.setRollbackOnly()
                return false
            }
        }
    }

    public void persist() throws Exception{
        regularExpense.postpone()
        regularExpense.save(flush: true, failOnError: true)
        save(flush: true, failOnError: true)
    }

    public boolean secureSave(){
        withTransaction { status ->
            try {
                persist()
                return true
            }catch(Exception eSave){
                eSave.printStackTrace()
                status.setRollbackOnly()
                return false
            }
        }
    }

    public boolean secureSave(Action action){
        withTransaction { status ->
            try {
                persist()
                return true
            }catch(Exception eSave){
                eSave.printStackTrace()
                status.setRollbackOnly()
                return false
            }
        }
    }

    public List<Action> getActionsWithoutType(ActionType type){

    }

    public Set<Payment> unvalidatedPayments(Long userID){
        debtOf(userID)?.getPayments().findAll{ !it.getIsValidated() }
    }

    public boolean haveUnvalidatedPayments(Long userID){
        unvalidatedPayments(userID).size()
    }

    public boolean isResolved(){
        receptionDate || amountInDebt() == 0
    }

    public Double amountAssignedTo(Long userID){
        Debt debt = debtOf(userID)
        debt ? debt.getValue() : 0D
    }

    public Double amountInDebt(){
        value - amountPaid()
    }

    public Double amountInDebtOf(Long userID){
        Debt debt = debtOf(userID)
        debt ? debt.amountInDebt() : 0D
    }

    public Double amountPaid(){
        Double amount = debts.sum{ it.amountPaidWithoutValidation() }
        amount? amount : 0D
    }

    public Double amountPaidBy(Long userID){
        Debt debt = debtOf(userID)
        debt ? debt.amountPaid() : 0D
    }

    public Debt debtOf(Long userID){
        debts.find{ it.getUserId() == userID }
    }

    public boolean isAssignedTo(Long userID){
        debtOf(userID)
    }

    public boolean isResolvedBy(Long userID){
        Debt debt = debtOf(userID)
        debt && debt.isResolved()
    }

    public boolean waitingPaymentBy(Long userID){
        Debt debt = debtOf(userID)
        debt && (!debt.getExpense().isResolved() && !debt.getExpense().getIsDeleted() && debt.amountInDebt() > 0)
    }

    public Set<User> assignedUsersInDebt(){
        assignedUsers.findAll{ !isResolvedBy(it.getId()) }
    }

    public Set<User> assignedUsersInDebtOrWaitingValidation(){
        assignedUsers.findAll{ waitingPaymentBy(it.getId()) }
    }

    public Set<Payment> unconfirmedPayments(){
        Set<Payment> payments = new HashSet<Payment>()
        debts.each{ payments.addAll(it.unconfirmedPayments()) }
        payments
    }

    public Set<User> assignedUsersAndResponsible(){
        Set<User> users = getAssignedUsers()
        users.add(responsible.getUser())
        users
    }

    public List<Action> latestEvents(){
        actions.sort{ it.getActionDate() }
    }

    public Set<Payment> validatedPayments(){
        Set<Payment> payments = new HashSet<Payment>()
        debts.each{ payments.addAll(it.validatedPayments()) }
        payments
    }

    public Set<Debt> debtsWithoutResponsible(){
        debts.findAll{ it.getUserId() != responsible.getUserId() }
    }

    public Set<Payment> validatedPaymentsWithoutResponsible(){
        Set<Payment> payments = new HashSet<Payment>()
        debtsWithoutResponsible().each{ payments.addAll(it.validatedPayments()) }
        payments
    }

    public Set<Payment> payments(){
        Set<Payment> payments = new HashSet<Payment>()
        debts.each{ payments.addAll(it.getPayments()) }
        payments
    }

    public Set<Payment> paymentsOf(Long userID){
        Debt debt = debtOf(userID)
        debt ? debt.getPayments().findAll { it.getValue() > 0 } : new HashSet<Payment>()
    }

    public boolean haveValidatedPayments(){
        validatedPayments().size()
    }

    public boolean haveValidatedPaymentsWithoutResponsible(){
        validatedPaymentsWithoutResponsible().size()
    }

    public boolean create(List<String> idsUsers, List<Double> value, Long id){
        Double acc = 0
        Integer numberOfUsers = 0
        boolean result = false;
        int position = 0
        withTransaction {status ->
            try{
                Expense expense = save();
                if(regularExpense){
                    regularExpense.postpone()
                    regularExpense.save(flush: true, failOnError: true)
                }
                if(idsUsers.size() == 1) expense.setReceptionDate(new Date())
                value.each { if(it > 0) numberOfUsers++ }

                def newExpenseAction = new Action(actionType: ActionType.findWhere(type: 'addExpenseCircle'), actor: expense.getResponsible(), circle: expense.getCircle(), expense: expense)
                newExpenseAction.save()

                for(String str : idsUsers){
                    value[position] = value[position].round(2)
                    if( value[position] > 0 ){
                        User user = User.findById(Long.parseLong(str))
                        if(numberOfUsers <= (position + 1))
                            value[position] = (expense.getValue() - acc);
                        Debt debt = new Debt(value: value[position], user: user, expense: expense).save()
                        this.addToAssignedUsers(user)
                        RegisteredUser registeredUser = user.getRegisteredUser()
                        if(registeredUser && registeredUser.getId() == getResponsibleId()) {
                            new Payment(user: user, debt: debt, value: value[position], validationDate: new Date(), isValidated: true).save()
                            debt.setResolvedDate(new Date())
                        }
                        acc += value[position]

                        if(user.getRegisteredUser()){
                            def newExpenseNotification = new SystemNotification(action: newExpenseAction, registeredUser: user.getRegisteredUser())
                            newExpenseNotification.secureSave()
                        }
                    }
                    position++;
                }
                result = true;
            }
            catch(Exception e){
                result = false
                status.setRollbackOnly()
            }
        }
        return result;
    }

    public boolean delete(){
        boolean result = true
        Set<Debt> debtSet = new HashSet<Debt>()
        withTransaction { status ->
            try {
                this.isDeleted = true
                Expense expense
                debtSet.addAll(getDebts())
                debtSet.each {
                    expense = it.getExpense()
                    expense.removeFromDebts(it)
                    expense.removeFromAssignedUsers(it.getUser())
                    it.delete(flush:true)
                }
                save()
            }catch(Exception eDelete){
                eDelete.printStackTrace()
                status.setRollbackOnly()
                result = false
            }
        }
        return result
    }

    public void payAndConfirmExpense(Double val, Debt debt, boolean flag, User user){
        if(flag){
            new Payment(debt: debt, user: user, value: val).save()
        }else{
            withTransaction { status ->
                try {
                        Payment payment = new Payment(debt: debt, user: user, value: val, validationDate: new Date(), isValidated: true)
                        payment.save()
                        debt.confirm()

                        // Save action and notification
                        Expense debtExpense = debt.getExpense()
                        User debtPayer = user
                        Circle debtCircle = debtExpense.getCircle()
                        RegisteredUser expenseResponsible = debtExpense.getResponsible()
                        RegisteredUser debtPayerRegisteredUser = debtPayer.getRegisteredUser()

                        def paymentAction = new Action(actionType: ActionType.findWhere(type: 'addPaymentExpense'), actor: debtPayerRegisteredUser, user: expenseResponsible.getUser(), payment: payment, circle: debtCircle, expense: debtExpense)
                        paymentAction.save()

                        def paymentNotification = new SystemNotification(action: paymentAction, registeredUser: debtPayerRegisteredUser)
                        paymentNotification.secureSave()

                        // Save action and notification
                        paymentAction = new Action(actionType: ActionType.findWhere(type: 'receivedPaymentExpense'), actor: expenseResponsible, user: debtPayer, payment: payment, circle: debtCircle, expense: debtExpense)
                        paymentAction.save()

                }catch(Exception ePayAndConfirm){
                    ePayAndConfirm.printStackTrace()
                    status.setRollbackOnly()
                }
            }
        }
    }

    public void confirm(Debt debt){
        if(debt.getExpense().amountInDebt() <= 0){
            setReceptionDate(new Date())
            save()
        }
    }

    public Set<RegisteredUser> getAssignedRegisteredUsers(){
        Set<RegisteredUser> registeredUsers = new HashSet<RegisteredUser>();
        assignedUsers.each{
            if(it.getRegisteredUser()){
                registeredUsers.add(it.getRegisteredUser())
            }
        }
        registeredUsers
    }
}
