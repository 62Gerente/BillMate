package com.billmate

class AuthenticationToken {

    String email
    String token

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
