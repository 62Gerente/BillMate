package com.billmate

import com.lucastex.grails.fileuploader.UFile

class RegisteredUser extends User {
    static hasMany = [responsibleExpenses: Expense, realizedActions: Action]
    static mappedBy = [responsibleExpenses: "responsible", realizedActions: "actor"]

    UFile photo

    String phoneNumber
    String password

    static constraints = {
        photo nullable: true

        phoneNumber matches: '\\d{9}', unique: true, nullable: true
        password password: true, size: 5..20, blank: false, nullable: false
    }
}
