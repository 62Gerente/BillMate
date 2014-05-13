package com.billmate

class DirectDebit {
    static hasOne = [regularExpense: RegularExpense]

    Date debitDate

    static constraints = {
        debitDate nullable: false
    }
}
