package com.billmate

class Circle {
    static hasMany = [users: User, expenseTypes: ExpenseType,
                      expenses: Expense, actions: Action]
    static hasOne = [collective: Collective, house: House]

    String name
    String description
    Date createdAt = new Date()

    static constraints = {
        collective nullable: true
        house nullable: true

        name nullable: false
        description nullable: true
        createdAt nullable: false
    }

    public String toString(){
        name
    }

    public boolean isType(String type){
        if(type.equals('House')){
            return house
        }else if(type.equals('Collective')){
            return collective
        }
        return false
    }

    public Set<Expense> unresolvedExpenses(){
        return this.expenses.findAll{ !it.isResolved() }
    }

    public Set<Expense> resolvedExpenses(){
        return this.expenses.findAll{ !it.isResolved() }
    }
}
