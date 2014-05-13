package com.billmate

abstract class User {
    static belongsTo = Circle
    static hasMany = [circles: Circle, payments: Payment, customizedDebts: CustomizedDebt]

    String name
    String email
    Date createdAt

    static constraints = {
        name blank: true, nullable: true
        email email: true, blank: false, unique: true, nullable: false
        createdAt nullable: false, defaultValue: new Date(), min: new Date()
    }

    String toString() {
        name ? name : email
    }
}
