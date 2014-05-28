package com.billmate

class SystemNotification {
    static belongsTo = [Action, RegisteredUser, Notification]

    Action action
    RegisteredUser registeredUser
    Notification notification

    Boolean isRead

    static constraints = {
        action nullable: false
        registeredUser nullable: false
        notification nullable: false

        isRead nullable: false, defaultValue: false
    }
}
