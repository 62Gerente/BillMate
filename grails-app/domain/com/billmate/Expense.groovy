package com.billmate

import com.lucastex.grails.fileuploader.UFile

class Expense {
    static belongsTo = [Circle, RegisteredUser, ExpenseType]
    static hasMany = [payments: Payment,
                      debt: Debt, actions: Action, assignedUsers: User]

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

    static constraints = {
        regularExpense nullable: true
        responsible nullable: false
        invoice nullable: true
        receipt nullable: true
        circle nullable: false
        expenseType nullable: false

        title nullable: false
        description maxSize: 2000, nullable: true
        value min: 0D, nullable: false
        beginDate nullable: false
        endDate nullable: true
        paymentDeadline nullable: true
        receptionDeadline nullable: true
        createdAt nullable: false
        paymentDate nullable: true
        receptionDate nullable: true
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
        if(!debt || debt.isEmpty()){
            rExpense.getCustomDebts().each { this.addToDebt(it) }
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
                    Debt debt = new Debt(value: listValues[position], percentage: 20, user: user, expense: expense).save()
                    position++;
                }

                return true
            }catch(Exception ignored){
                status.setRollbackOnly()
                return false
            }
        }
    }

    public boolean isResolved(){
        receptionDate || totalDebt() == 0
    }

    public Double valueAssignedTo(Long userId){
        Double totalDebt
        Debt debt = debt.find{ it.getUserId() == userId && it.getExpenseId() == this.id }

        if(debt){
            totalDebt = value * debt.getPercentageInDecimal()
        }else{
            totalDebt = value * percentageAssignedToUsersWithoutDebtsInDecimal()
        }

        totalDebt
    }

    public Double totalDebt(){
        value - totalAmountPaid()
    }

    public Double percentageOfDebts(){
        Double percentage = debt.sum{ it.getPercentage() }
        percentage ? percentage : 0D
    }

    public Double percentageOfEquallyDividedDebts(){
        100 - percentageOfDebts()
    }

    public Integer numberOfDebts(){
        debt.size()
    }

    public Integer numberOfAssignedUsers(){
        assignedUsers.size() + 1
    }

    public Integer numberOfAssignedUsersWithoutDebts(){
        numberOfAssignedUsers() - numberOfDebts()
    }

    public Double percentageAssignedToUsersWithoutDebts(){
        percentageOfEquallyDividedDebts() / numberOfAssignedUsersWithoutDebts()
    }

    public Double percentageAssignedToUsersWithoutDebtsInDecimal(){
        percentageAssignedToUsersWithoutDebts() / 100
    }

    public Double totalAmountPaid(){
        Double amount = payments.findAll{ it.getIsValidated() || !it.getValidationDate() }.sum{ it.getValue() }

        if(!amount){ amount = 0D }

        amount += valueAssignedTo(responsible.getUserId())
        amount
    }

    public Double amountPaidBy(Long userId){
        Double amount = payments.findAll{ it.getUserId() == userId && (it.getIsValidated() || !it.getValidationDate()) }.sum{ it.getValue() }
        amount ? amount : 0D
    }

    public Double debtOf(Long userId){
        valueAssignedTo(userId) - amountPaidBy(userId)
    }

    public boolean isAssignedTo(Long userId){
        assignedUsers.find{ it.getId() == userId }
    }

    public boolean isResolvedBy(Long userId){
        !isResolved() && isAssignedTo(userId) && debtOf(userId) == 0
    }

    public Set<User> assignedUsersWithDebts(){
        assignedUsers.findAll{ !isResolvedBy(it.getId()) }
    }

    public Set<Payment> unconfirmedPayments(){
        payments.findAll{ !it.getValidationDate() && !it.getIsValidated() }
    }

    public boolean create(List<String> idsUsers, List<Double> value){
        boolean result = false;
        int position = 0
        withTransaction {status ->
            try{
                Expense expense = save();
                if(regularExpense){
                    regularExpense.postpone()
                    regularExpense.save(flush: true, failOnError: true)
                }
                for(String str : idsUsers){
                    User user = User.findById(Long.parseLong(str))
                    Debt debt = new Debt(value: value[position], percentage: 20, user: user, expense: expense).save()
                    this.addToAssignedUsers(user)
                    position++;
                }
            }
            catch(Exception e){
                result = false
                status.setRollbackOnly()
            }
            result = true;
        }
        return result;
    }
}
