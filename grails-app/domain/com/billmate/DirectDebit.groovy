package com.billmate

import com.nanlabs.grails.plugin.logicaldelete.LogicalDelete

@LogicalDelete
class DirectDebit {
    RegularExpense regularExpense
    Date debitDate

    static constraints = {
        regularExpense nullable: false

        debitDate nullable: false
    }
}
