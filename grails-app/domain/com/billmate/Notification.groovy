package com.billmate

class Notification {
    static hasOne = [systemNotification: SystemNotification]

    static constraints = {
        systemNotification nullable: true
    }

    public boolean secureSave(){
        withTransaction { status ->
            try {
                systemNotification.save()
                save(flush: true, failOnError: true)
                return true
            }catch(Exception ignored){
                status.setRollbackOnly()
                return false
            }
        }
    }
}
