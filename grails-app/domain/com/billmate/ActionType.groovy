package com.billmate

import com.nanlabs.grails.plugin.logicaldelete.LogicalDelete

@LogicalDelete
class ActionType {
    static hasMany = [actions: Action]

    String type
    String icon
    String cssClass

    static constraints = {
        type nullable: false
    }
}
