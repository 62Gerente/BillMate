package com.billmate

class ActionType {
    static hasMany = [actions: Action]

    String type

    static constraints = {
        type blank: false, nullable: false
    }
}
