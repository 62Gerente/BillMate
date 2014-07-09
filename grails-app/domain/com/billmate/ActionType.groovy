package com.billmate

class ActionType {
    static hasMany = [actions: Action]

    String type
    String icon
    String cssClass

    static constraints = {
        type nullable: false
    }
}
