package com.billmate

import org.codehaus.groovy.grails.web.mapping.LinkGenerator

class User {
    static belongsTo = Circle
    static hasMany = [circles: Circle, payments: Payment, customizedDebts: CustomDebt, referencedActions: Action]
    static hasOne = [referredUser: ReferredUser, registeredUser: RegisteredUser]

    String name
    String email
    Date createdAt

    static constraints = {
        referredUser nullable: true
        registeredUser nullable: true

        name blank: false, nullable: false
        email email: true, blank: false, unique: true, nullable: false
        createdAt nullable: true, defaultValue: new Date()
    }

    public String toString() {
        name ? name : email
    }
}
