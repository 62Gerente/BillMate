package com.billmate

abstract class Notification {
    String title
    String body
    Date createdAt

    static constraints = {
        title blank: false, nullable: false
        body blank: false, nullable: false
        createdAt nullable: false, defaultValue: new Date(), min: new Date()
    }

    String toString(){
        title
    }
}
