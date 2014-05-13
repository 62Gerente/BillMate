package com.billmate

class SystemNotification extends Notification{
    static belongsTo = [Action, RegisteredUser]

    Action action
    RegisteredUser registeredUser

    Boolean read

    static constraints = {
        action nullable: false
        registeredUser nullable: false

        read nullable: false, defaultValue: false
    }
}
