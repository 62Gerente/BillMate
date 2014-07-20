package com.billmate

import com.lucastex.grails.fileuploader.UFile
import com.nanlabs.grails.plugin.logicaldelete.LogicalDelete
import org.apache.shiro.crypto.hash.Sha256Hash

@LogicalDelete
class RegisteredUser {
    static belongsTo = User
    static hasMany = [responsibleExpenses: Expense, realizedActions: Action, systemNotifications: SystemNotification, responsibleRegularExpenses: RegularExpense]

    User user
    UFile photo

    String phoneNumber
    String password
    static constraints = {
        user nullable: false
        photo nullable: true

        phoneNumber matches: '\\d{9}', unique: true, nullable: true
        password password: true, nullable: false, blank: false, minSize: 5
    }

    static mapping = {
        realizedActions sort: "actionDate", order: "desc"
        systemNotifications sort: "action", order: "desc"
    }

    public RegisteredUser() {
        user = new User()
    }

    public RegisteredUser(Map map) {
        user = new User(map)
    }

    def beforeInsert() {
        encodePassword()
    }

    def beforeUpdate() {
        if (isDirty('password')) {
            encodePassword()
        }
    }

    protected void encodePassword() {
        password = new Sha256Hash(password).toHex()
    }

    public void setName(String name) {
        user.setName(name)
    }

    public void setEmail(String email) {
        user.setEmail(email)
    }

    public String getName() {
        user.getName()
    }

    public String getEmail() {
        user.getEmail()
    }

    public void addToCircles(Circle circle) {
        user.addToCircles(circle)
    }

    public String toString() {
        return user.toString();
    }

    public void persist() throws Exception {
        user.save(flush: true, failOnError: true)
        save(flush: true, failOnError: true)
    }

    public boolean secureSave() {
        withTransaction { status ->
            try {
                persist()
                return true
            } catch (Exception eSave) {
                eSave.printStackTrace()
                status.setRollbackOnly()
                return false
            }
        }
    }

    public boolean secureSave(Action action) {
        withTransaction { status ->
            try {
                persist()
                action.save()
                return true
            } catch (Exception eSave) {
                eSave.printStackTrace()
                status.setRollbackOnly()
                return false
            }
        }
    }

    public String getPhotoOrDefault() {
        if (photo) {
            def linkGenerator = this.domainClass.grailsApplication.mainContext.grailsLinkGenerator
            return linkGenerator.link(controller: "fileUploader", action: "show", id: photo.getId())
        } else {
            return getPathToDefaultPhoto()
        }
    }

    public String getPathToDefaultPhoto() {
        def linkGenerator = this.domainClass.grailsApplication.mainContext.grailsLinkGenerator
        return linkGenerator.resource(dir: 'images', file: 'default-user.png', absolute: true)
    }

    public Set<Circle> getCircles(Map map) {
        Set<Circle> result = user.getActiveCircles()

        if (map && map.containsKey('type')) {
            result = result.findAll { it.isType(map.get('type')) }
        }

        result
    }

    public Set<Circle> getHouses() {
        getCircles(type: 'House')
    }

    public Set<Circle> getCollectives() {
        getCircles(type: 'Collective')
    }

    public Set<Expense> unresolvedResponsibleExpenses() {
        responsibleExpenses.findAll { !it.isResolved() && !it.getIsDeleted() }
    }

    // totalAsset
    public Double amountInAsset() {
        Double total = unresolvedResponsibleExpenses().sum { it.amountInDebt() }
        total ? total : 0D
    }

    // totalAssetOf
    public Double amountInAssetOf(Long userID) {
        unresolvedResponsibleExpensesBy(userID).sum { it.amountInDebtOf(userID) }
    }

    public Set<Expense> unresolvedResponsibleExpensesBy(Long user_id) {
        unresolvedResponsibleExpenses().findAll { !it.isResolvedBy(user_id) && !it.getIsDeleted() && it.amountInDebtOf(user_id)!=0 }
    }

    public Set<User> whoOweMe() {
        Set<User> users = new HashSet<>()
        unresolvedResponsibleExpenses().each { users.addAll(it.assignedUsersInDebtOrWaitingValidation()) }
        users
    }

    public Set<Payment> unconfirmedPaymentsOnResponsibleExpenses() {
        Set<Payment> unconfirmedPayments = new HashSet<>()
        unresolvedResponsibleExpenses().each { unconfirmedPayments.addAll(it.unconfirmedPayments()) }
        unconfirmedPayments
    }

    public Set<User> whoHaveUnconfirmedPayments() {
        Set<User> users = new HashSet<>()
        unconfirmedPaymentsOnResponsibleExpenses().each { users.add(it.getUser()) }
        users
    }

    public Set<Payment> unconfirmedPaymentsOnResponsibleExpensesOf(Long user_id) {
        unconfirmedPaymentsOnResponsibleExpenses().findAll { it.getUserId() == user_id }
    }

    public Integer getNumberOfUnreadNotifications() {
        int count = systemNotifications.count { !it.getIsRead() }
        count ? count : 0
    }

    public boolean markNotificationsAsRead() {
        Set<SystemNotification> notification = systemNotifications.findAll { !it.getIsRead() }

        withTransaction { status ->
            try {
                notification.each {
                    it.setIsRead(true)
                    it.persist()
                }
            } catch (Exception eMarkNotificationAsRead) {
                eMarkNotificationAsRead.printStackTrace()
                status.setRollbackOnly();
                return false
            }
        }
        return true
    }

    public Set<RegularExpense> regularResponsibleExpensesInReceptionTime() {
        responsibleRegularExpenses.findAll { it.inReceptionTime() }
    }

    public Set<Expense> monthResponsibleExpenses(Date date) {
        responsibleExpenses.findAll {
            it.getBeginDate().getMonth() == date.getMonth() && it.getBeginDate().getYear() == date.getYear()
        }
    }

    public Set<Expense> monthResponsibleExpensesOfExpenseType(Date date, ExpenseType expenseType) {
        monthResponsibleExpenses(date).findAll { it.getExpenseType().getId() == expenseType.getId() }
    }

    public boolean confirmPayments(List<String> paymentIds) {
        Set<Payment> payments = unconfirmedPaymentsOnResponsibleExpenses().findAll {
            paymentIds.contains(it.getId().toString())
        }

        withTransaction { status ->
            try {
                payments.each {
                    it.setIsValidated(true)
                    it.setValidationDate(new Date())
                    it.save()

                    def expense = it.getExpense()
                    def responsible = expense.getResponsible()
                    def circle = expense.getCircle()

                    Debt debt = expense.debtOf(it.getUserId())
                    if (!debt.getResolvedDate() && debt.getValue() <= debt.amountPaid()) {
                        debt.setResolvedDate(new Date())
                        debt.save()
                    }

                    // Save action and notification
                    def paymentAction = new Action(actionType: ActionType.findWhere(type: 'addPaymentExpense'), actor: it.getUser().getRegisteredUser(), user: responsible.getUser(), payment: it, circle: circle, expense: expense)
                    paymentAction.save()

                    //def paymentNotification = new SystemNotification(action: paymentAction, registeredUser: expense.getResponsible())
                    //paymentNotification.secureSave()

                    def paymentNotification = new SystemNotification(action: paymentAction, registeredUser: it.getUser().getRegisteredUser())
                    paymentNotification.secureSave()

                    // Save action and notification
                    paymentAction = new Action(actionType: ActionType.findWhere(type: 'receivedPaymentExpense'), actor: responsible, user: it.getUser(), payment: it, circle: circle, expense: expense)
                    paymentAction.save()
                }
            } catch (Exception eConfirmPayment) {
                eConfirmPayment.printStackTrace()
                status.setRollbackOnly();
                return false
            }
        }
        return true
    }

    public boolean cancelPayments(List<String> paymentIds, RegisteredUser sessionUser) {
        Set<Payment> payments = unconfirmedPaymentsOnResponsibleExpenses().findAll {
            paymentIds.contains(it.getId().toString())
        }

        withTransaction { status ->
            try {
                payments.each {
                    Debt debt = it.getDebt()
                    debt.setResolvedDate(null)
                    debt.removeFromPayments(it)
                    debt.save()
                    it.delete(physical: true)
                }
            } catch (Exception eCancelPayment) {
                eCancelPayment.printStackTrace()
                status.setRollbackOnly();
                return false
            }
        }
        return true
    }

    public List<Action> latestResponsibleEvents() {
        Set<Action> latestEvents = new HashSet<>()

        responsibleExpenses.each { latestEvents.addAll(it.getActions()) }
        responsibleRegularExpenses.each { latestEvents.addAll(it.getActions()) }

        latestEvents.toList()
    }

    public User[] getFriendsOfAllCircles() {
        Set<User> list = new HashSet<User>()
        User.findAll().each { if (it.getRegisteredUser() && it.getRegisteredUserId() != getId()) list.add(it) }
        return list.toArray()
    }

    public long getTotalWhoIOwe() {
        long valueWhoHaveToPay = 0, amountAlreadyPaid = 0, totalAmountPaid = 0, totalValueToPay = 0
        Set<Expense> expenseSet = user.getExpenses()
        Set<Payment> paymentSet
        Debt debt
        for (Expense expense in expenseSet) {
            if (expense.getResponsible().getId() != getId()) {
                debt = Debt.findByExpenseAndUser(expense, this.getUser())
                paymentSet = Payment.findAllByExpenseAndUser(expense, user)
                valueWhoHaveToPay = (debt) ? debt.getValue() : 0
                for (Payment paymentAux : paymentSet) {
                    amountAlreadyPaid += paymentAux.getValue()
                }
                totalValueToPay += valueWhoHaveToPay
                totalAmountPaid += valueWhoHaveToPay - amountAlreadyPaid
            }
        }
        totalValueToPay - totalAmountPaid
    }

    public long getTotalWhoOweMe() {
        long valueAlreadyReceivedByEachExpense = 0
        long totalAmountRemaining = 0
        Set<Expense> expenseSet = this.getResponsibleExpenses()
        Set<Payment> paymentSet
        for (Expense expense in expenseSet) {
            paymentSet = Payment.findAllByExpenseAndUser(expense, this.getUser())
            for (Payment payment : paymentSet) {
                valueAlreadyReceivedByEachExpense += payment.value
            }
            totalAmountRemaining += expense.getValue() - valueAlreadyReceivedByEachExpense
        }
        totalAmountRemaining
    }

    public long getTotalBalance() {
        getTotalWhoOweMe() - getTotalWhoIOwe();
    }

    public static Set<ExpenseType> getExpenseTypeByHouse() {
        CircleType.getHouseExpenseTypes()
    }

    public static Set<ExpenseType> getExpenseTypeByCollective() {
        CircleType.getCollectiveExpenseTypes()
    }

    public List<Action> latestEvents(){
        user.latestEvents();
    }

    public Set<Circle> getCircles(){
        return user.getActiveCircles()
    }

    public boolean hasMovementsInCircle(Long id_circle){
        getUser().hasMovementsInCircle(id_circle)
    }
}
