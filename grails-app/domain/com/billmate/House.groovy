package com.billmate

import org.springframework.validation.ObjectError

class House{
    static belongsTo = Circle

    Circle circle

    static constraints = {
        circle nullable: false
    }

    public House() {
        super()
        circle = new Circle()
    }

    public House(Map map) {
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

    public void persist() throws Exception{
        circle.save(flush: true, failOnError: true)
        save(flush: true, failOnError: true)
    }

    public boolean secureSave(){
        withTransaction { status ->
            try {
                persist()
                return true
            }catch(Exception eSave){
                status.setRollbackOnly()
                return false
            }
        }
    }

    public boolean secureSaveWithAction(Action action){
        withTransaction { status ->
            try {
                persist()
                action.save()
                return true
            }catch(Exception eSave){
                status.setRollbackOnly()
                return false
            }
        }
    }

    public String getCssClass(){
        "fa fa-home"
    }

    public String getCssColor(){
        "green"
    }

    public boolean addUsersAndExpenseTypesToHouseAndSave(Set<String> friendsSet, Set<String> expenseTypesSet){
        boolean result = true
        withTransaction { status ->
            try {
                persist()
                circle.addExpensesByIDSOrName(expenseTypesSet)
                circle.addUsersByIDSOrEmail(friendsSet)
            }
            catch(Exception eSave){
                result = false
            }
        }
        return result
    }

    public boolean addUsersAndExpenseTypesToHouseAndSaveWithAction(Set<String> friendsSet, Set<String> expenseTypesSet, Action action, RegisteredUser sessionUser){
        boolean result = true
        withTransaction { status ->
            try {
                persist()
                action.save()
                circle.addExpensesByIDSOrName(expenseTypesSet)
                circle.addUsersByIDSOrEmail(friendsSet, sessionUser)
            }
            catch(Exception eSave){
                eSave.printStackTrace()
                status.setRollbackOnly()
                result = false
            }
        }
        return result
    }
}
