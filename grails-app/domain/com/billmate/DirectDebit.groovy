package com.billmate

class DirectDebit {
    RegularExpense regularExpense
    Date debitDate

    static constraints = {
        regularExpense nullable: false

        debitDate nullable: false
    }
}
