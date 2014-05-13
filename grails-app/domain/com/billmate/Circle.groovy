package com.billmate

abstract class Circle {
    static hasMany = [users: User, expenseTypes: ExpenseType,
                      expenses: Expense, actions: Action]

    String name
    String description
    Date createdAt

    static constraints = {
        name blank: false, nullable: false
        description nullable: true, blank: true
        createdAt nullable: false, defaultValue: new Date(), min: new Date()
    }

    String toString(){
        name
    }
}
