package com.billmate

class ActionType {
    static hasMany = [actions: Action]

    String type

    static constraints = {
        type nullable: false
    }

    public boolean secureSave(){
        withTransaction { status ->
            try {
                save(flush: true, failOnError: true)
                return true
            }catch(Exception ignored){
                status.setRollbackOnly()
                return false
            }
        }
    }

}
