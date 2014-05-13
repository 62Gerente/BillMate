package com.billmate

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

        name blank: true, nullable: true
        email email: true, blank: false, unique: true, nullable: false
        createdAt nullable: false, defaultValue: new Date(), min: new Date()
    }

    String toString() {
        name ? name : email
    }
}
