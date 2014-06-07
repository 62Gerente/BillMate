package com.billmate

class Subscription {

    String email
    Date createdAt = new Date()

    static constraints = {
        email email: true, unique: true, nullable: false
        createdAt nullable: false
    }
}
