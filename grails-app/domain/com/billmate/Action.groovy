package com.billmate

class Action {
    static belongsTo = [ActionType, Expense, RegisteredUser, User, Circle, Payment]
    static hasMany = [systemNotifications: SystemNotification]

    ActionType actionType
    RegisteredUser actor
    User user
    Circle circle
    Expense expense
    RegularExpense regularExpense
    Payment payment

    Date actionDate = new Date()

    static constraints = {
        actionType nullable: false
        actor nullable: true
        user nullable: true
        circle nullable: true
        expense nullable: true
        regularExpense nullable: true
        payment nullable: true

        actionDate nullable: false
    }

}
