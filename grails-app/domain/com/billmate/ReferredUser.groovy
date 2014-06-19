package com.billmate

class ReferredUser  {
    static belongsTo = User

    User user

    static constraints = {
        user nullable: false
    }

    public ReferredUser() {
        super()
        user = new User()
    }

    public ReferredUser(Map map) {
        super(map)
        user = new User(map)
    }

    public void setName(String name){
        user.setName(name)
    }

    public void setEmail(String email){
        user.setEmail(email)
    }

    public String getName(){
        user.getName()
    }

    public String getEmail(){
        user.getEmail()
    }

    public void addToCircles(Circle circle){
        user.addToCircles(circle)
    }

    public String toString() {
        return user.toString();
    }

    public void secureSave(){
        withTransaction { status ->
            try {
                user.save(flush: true, failOnError: true)
                save(flush: true, failOnError: true)
            }catch(Exception ignored){
                status.setRollbackOnly()
            }
        }
    }
}
