package com.billmate

import com.lucastex.grails.fileuploader.UFile
import com.sun.org.apache.xpath.internal.operations.Bool

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
        withTransaction { status ->
            try {
                regularExpense.postpone()
                regularExpense.save(flush: true, failOnError: true)
                save(flush: true, failOnError: true)
                return true
            }catch(Exception ignored){
                status.setRollbackOnly()
                return false
            }
        }
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
        Double amount = debts.sum{ it.amountPaid() }
        amount ? amount : 0D
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

    public Set<User> assignedUsersInDebt(){
        assignedUsers.findAll{ !isResolvedBy(it.getId()) }
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
        debt ? debt.getPayments() : new HashSet<Payment>()
    }

    public boolean haveValidatedPayments(){
        validatedPayments().size()
    }

    public boolean haveValidatedPaymentsWithoutResponsible(){
        validatedPaymentsWithoutResponsible().size()
    }
}
