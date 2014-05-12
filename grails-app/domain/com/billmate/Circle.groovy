package com.billmate

abstract class Circle {
    static hasMany = [users: User, expenseTypes: ExpenseType, expenses: Expense]

    String name
    Date createdAt

    static constraints = {
        name blank: false, nullable: false
        createdAt nullable: false, defaultValue: new Date(), min: new Date()
    }

    String toString(){
        name
    }
}
