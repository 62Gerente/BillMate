package com.billmate

import com.lucastex.grails.fileuploader.UFile
import org.springframework.validation.ObjectError

class Expense {
    static belongsTo = [Circle, RegisteredUser, ExpenseType]
    static hasMany = [payments: Payment,
                      customDebts: CustomDebt, actions: Action, assignedUsers: User]

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
        if(customDebts.isEmpty()) customDebts.addAll(rExpense.getCustomDebts())
        if(assignedUsers.isEmpty()) assignedUsers.addAll(rExpense.getAssignedUsers())
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
        receptionDate || totalDebt() == 0
    }

    public Double valueAssignedTo(Long userId){
        Double totalDebt
        CustomDebt customDebt = customDebts.find{ it.getUserId() == userId && it.getExpenseId() == this.id }

        if(customDebt){
            totalDebt = value * customDebt.getPercentageInDecimal()
        }else{
            totalDebt = value * percentageAssignedToUsersWithoutCustomDebtsInDecimal()
        }

        totalDebt
    }

    public Double totalDebt(){
        value - totalAmountPaid()
    }

    public Double percentageOfCustomDebts(){
        Double percentage = customDebts.sum{ it.getPercentage() }
        percentage ? percentage : 0D
    }

    public Double percentageOfEquallyDividedDebts(){
        100 - percentageOfCustomDebts()
    }

    public Integer numberOfCustomDebts(){
        customDebts.size()
    }

    public Integer numberOfAssignedUsers(){
        assignedUsers.size() + 1
    }

    public Integer numberOfAssignedUsersWithoutCustomDebts(){
        numberOfAssignedUsers() - numberOfCustomDebts()
    }

    public Double percentageAssignedToUsersWithoutCustomDebts(){
        percentageOfEquallyDividedDebts() / numberOfAssignedUsersWithoutCustomDebts()
    }

    public Double percentageAssignedToUsersWithoutCustomDebtsInDecimal(){
        percentageAssignedToUsersWithoutCustomDebts() / 100
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
}
