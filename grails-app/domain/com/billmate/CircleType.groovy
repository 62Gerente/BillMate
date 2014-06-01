package com.billmate

class CircleType {
    static hasMany = [expenseTypes: ExpenseType]

    Integer identifier
    String name

    static constraints = {
        identifier nullable: false
        name nullable: false
    }
}
