package com.billmate

class Action {
    static belongsTo = [ActionType, Expense, RegisteredUser, User, Circle]
    static hasMany = [systemNotifications: SystemNotification]

    ActionType actionType
    Expense expense
    RegularExpense regularExpense
    RegisteredUser actor
    User user
    Circle circle

    Date actionDate = new Date()

    static constraints = {
        actionType nullable: false
        expense nullable: true
        regularExpense nullable: true
        actor nullable: true
        user nullable: true
        circle nullable: true

        actionDate nullable: false
    }

}
