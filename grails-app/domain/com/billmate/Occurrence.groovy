package com.billmate

class Occurrence {
    Date beginDate
    Date endDate

    static constraints = {
        beginDate defaultValue: new Date(), nullable: false
        endDate nullable: true
    }
}
