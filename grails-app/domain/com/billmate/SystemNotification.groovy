package com.billmate

class SystemNotification extends Notification{
    static belongsTo = [Action, RegisteredUser, Notification]

    Action action
    RegisteredUser registeredUser
    Notification notification

    Boolean read

    static constraints = {
        action nullable: false
        registeredUser nullable: false
        notification nullable: false

        read nullable: false, defaultValue: false
    }
}
