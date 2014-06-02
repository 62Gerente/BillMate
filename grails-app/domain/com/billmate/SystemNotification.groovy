package com.billmate

class SystemNotification extends Notification{
    static belongsTo = [Action, RegisteredUser, Notification]

    Action action
    RegisteredUser registeredUser
    Notification notification

    Boolean is_read

    static constraints = {
        action nullable: false
        registeredUser nullable: false
        notification nullable: false

        is_read nullable: false, defaultValue: false
    }
}
