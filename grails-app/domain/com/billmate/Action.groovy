package com.billmate

class Action {

    String action
    Date actionDate

    static constraints = {
        action blank: false, nullable: false
        actionDate nullable: false, defaultValue: new Date(), min: new Date()
    }

    String toString(){
        action
    }
}
