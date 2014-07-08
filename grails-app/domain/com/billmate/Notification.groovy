package com.billmate

import com.nanlabs.grails.plugin.logicaldelete.LogicalDelete

@LogicalDelete
class Notification {
    static hasOne = [systemNotification: SystemNotification]

    static constraints = {
        systemNotification nullable: true
    }
}
