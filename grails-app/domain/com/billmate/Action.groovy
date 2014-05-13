package com.billmate

class Action {
    static belongsTo = [ActionType, Expense]

    ActionType actionType
    Expense expense

    Date actionDate

    static constraints = {
        actionType nullable: false
        expense nullable: true

        actionDate nullable: false, defaultValue: new Date(), min: new Date()
    }

}
