package com.billmate

class RegularExpense {
    static belongsTo = [DirectDebit, Expense]

    DirectDebit directDebit
    Expense expense

    Date receptionBeginDate
    Date receptionEndDate
    Date paymentBeginDate
    Date paymentEndDate

    static constraints = {
        directDebit nullable: true
        expense nullable: true

        receptionBeginDate nullable: true
        receptionEndDate nullable: true
        paymentBeginDate nullable: true
        paymentEndDate nullable: true
    }
}
