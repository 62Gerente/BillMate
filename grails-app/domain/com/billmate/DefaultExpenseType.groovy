package com.billmate

class DefaultExpenseType extends ExpenseType{
    static belongsTo = CircleType
    static hasMany = [circleTypes: CircleType]

    static constraints = {
    }
}
