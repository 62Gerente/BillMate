package com.billmate

import com.lucastex.grails.fileuploader.UFile

class RegisteredUser {
    static belongsTo = User
    static hasMany = [responsibleExpenses: Expense, realizedActions: Action, systemNotifications: SystemNotification]

    User user
    UFile photo

    String phoneNumber
    String password

    static constraints = {
        user nullable: false
        photo nullable: true

        phoneNumber matches: '\\d{9}', unique: true, nullable: true
        password password: true, size: 5..20, nullable: false
    }
}
