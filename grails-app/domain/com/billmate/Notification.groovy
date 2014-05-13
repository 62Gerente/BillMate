package com.billmate

class Notification {
    static hasOne = [systemNotification: SystemNotification]

    static constraints = {
        systemNotification nullable: true
    }
}
