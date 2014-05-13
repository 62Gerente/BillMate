package com.billmate

class RegularExpense extends Expense{
    static belongsTo = DirectDebit

    DirectDebit directDebit

    Date receptionBeginDate
    Date receptionEndDate
    Date paymentBeginDate
    Date paymentEndDate

    static constraints = {
        directDebit nullable: true

        receptionBeginDate nullable: true
        receptionEndDate nullable: true
        paymentBeginDate nullable: true
        paymentEndDate nullable: true
    }
}
