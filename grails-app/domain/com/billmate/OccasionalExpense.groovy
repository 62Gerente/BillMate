package com.billmate

class OccasionalExpense {
    static belongsTo = Expense

    Expense expense

    static constraints = {
        expense nullable: true
    }
}
