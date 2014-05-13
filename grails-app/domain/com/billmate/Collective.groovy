package com.billmate

class Collective {
    static belongsTo = Circle

    Circle circle

    static constraints = {
        circle nullable: false
    }
}
