package com.billmate

abstract class Circle {

    String name
    Date createdAt

    static constraints = {
        name blank: false, nullable: false
        createdAt nullable: false, defaultValue: new Date(), min: new Date()
    }

    String toString(){
        name
    }
}
