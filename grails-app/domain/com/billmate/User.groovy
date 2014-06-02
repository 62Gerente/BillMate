package com.billmate

class User {
    static belongsTo = [Circle, Expense]
    static hasMany = [circles: Circle, payments: Payment, customizedDebts: CustomDebt, referencedActions: Action, expenses: Expense]
    static hasOne = [referredUser: ReferredUser, registeredUser: RegisteredUser]

    String name
    String email
    Date createdAt = new Date()

    static constraints = {
        referredUser nullable: true
        registeredUser nullable: true

        name nullable: false
        email email: true, unique: true, nullable: false
        createdAt nullable: false
    }

    static mapping = {
        table '`user`'
    }

    public String toString(){
        name ? name : email
    }

    public Double totalDebt(){
        Double total = unresolvedExpenses().sum{ it.debtOf(this.id) }
        total ? total : 0D
    }

    public Set<Expense> unresolvedExpenses(){
        expenses.findAll{ !it.isResolvedBy(this.id) }
    }

    public Set<RegisteredUser> whoIOwe(){
        Set<RegisteredUser> users = new HashSet<>()
        unresolvedExpenses().each { users.add( it.getResponsible() ) }
        users
    }

    public Set<Expense> unresolvedExpensesWhoResponsibleIs(Long registeredUserId){
        unresolvedExpenses().findAll{ it.getResponsibleId() == registeredUserId }
    }

    public Double totalDebtTo(Long registeredUserId){
        Double total = unresolvedExpensesWhoResponsibleIs(registeredUserId).sum{ it.debtOf(this.id) }
        total ? total : 0D
    }
}
