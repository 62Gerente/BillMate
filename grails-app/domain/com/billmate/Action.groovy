package com.billmate

class Action {

    Date actionDate

    static constraints = {
        actionDate nullable: false, defaultValue: new Date(), min: new Date()
    }

}
