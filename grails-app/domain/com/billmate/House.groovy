package com.billmate

class House{
    static belongsTo = Circle

    Circle circle

    static constraints = {
        circle nullable: false
    }
}
