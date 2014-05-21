package com.billmate

class DefaultExpenseType{
    static belongsTo = [CircleType, ExpenseType]
    static hasMany = [circleTypes: CircleType]

    ExpenseType expenseType

    static constraints = {
        expenseType nullable: false
    }
}
