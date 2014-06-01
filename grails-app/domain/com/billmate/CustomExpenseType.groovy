package com.billmate

class CustomExpenseType{
    static belongsTo = ExpenseType

    ExpenseType expenseType

    static constraints = {
        expenseType nullable: false
    }
}
