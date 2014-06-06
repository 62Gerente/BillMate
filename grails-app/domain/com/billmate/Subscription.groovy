package com.billmate

class Subscription {

    String email
    Date createdAt = new Date()

    static constraints = {
        email email: true, unique: true, nullable: false
        createdAt nullable: false
    }

    public boolean secureSave(){
        withTransaction { status ->
            try {
                this.save(flush: true, failOnError: true)
                save(flush: true, failOnError: true)
                return true
            }catch(Exception ignored){
                status.setRollbackOnly()
                return false
            }
        }
    }
}
