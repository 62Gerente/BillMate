package com.billmate

class Action {
    static belongsTo = [ActionType, Expense, RegisteredUser, User, Circle]
    static hasMany = [systemNotifications: SystemNotification]

    ActionType actionType
    Expense expense
    RegisteredUser actor
    User user
    Circle circle

    Date actionDate

    static constraints = {
        actionType nullable: false
        expense nullable: true
        actor nullable: true
        user nullable: true
        circle nullable: true

        actionDate nullable: false, defaultValue: new Date(), min: new Date()
    }

}
