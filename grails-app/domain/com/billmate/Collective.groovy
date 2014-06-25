package com.billmate

import org.springframework.validation.ObjectError

class Collective {
    static belongsTo = Circle

    Circle circle

    static constraints = {
        circle nullable: false
    }

    public Collective() {
        super()
        circle = new Circle()
    }

    public Collective(Map map) {
        super(map)
        circle = new Circle(map)
    }

    def beforeValidate() {
        circle.validate()
        circle.errors.getAllErrors().each {
            ObjectError objectError = (ObjectError) it
            this.errors.reject(objectError.getCode(), objectError.toString())
        }
    }

    public void setName(String name){
        circle.setName(name)
    }

    public void setDescription(String description){
        circle.setDescription(description)
    }

    public String toString() {
        return circle.toString();
    }

    public boolean secureSave(){
        withTransaction { status ->
            try {
                circle.save(flush: true, failOnError: true)
                save(flush: true, failOnError: true)
                return true
            }catch(Exception ignored){
                status.setRollbackOnly()
                return false
            }
        }
    }

    public String getCssClass(){
        "fa fa-circle-o"
    }

    public String getCssColor(){
        "blue"
    }

    public boolean addUsersAndExpenseTypesToCollectiveAndSave(Set<String> friendsSet, Set<String> expenseTypesSet){
        boolean result = true
        withTransaction { status ->
            try {
                secureSave()
                circle.addExpensesByIDSOrName(expenseTypesSet)
                circle.addUsersByIDSOrEmail(friendsSet)
            }
            catch(Exception e){
                result = false
                status.setRollbackOnly()
            }
        }
        return result
    }
}
