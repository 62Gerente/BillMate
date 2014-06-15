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

    public boolean createHouse(String expenseType, String friendsHouse){
        boolean flag = false
        StringBuilder errorMessage = new StringBuilder()
        String[] expenseSet = expenseType.split(",")
        String[] friendsSet = friendsHouse.split(",")
        withTransaction { status ->
            try {
                for (String expense : expenseSet) {
                    ExpenseType expenseTypeAux = (expense.isLong()) ? ExpenseType.findById(Long.parseLong(expense)) : null
                    if (expenseTypeAux) {
                        expenseTypeAux.addToCircles(getCircle())
                        expenseTypeAux.save()
                    } else {
                        CustomExpenseType customExpenseType = new CustomExpenseType(name: expense, cssClass: 'fa fa-tag')
                        customExpenseType.addToCircles(getCircle())
                        customExpenseType.secureSave()
                    }
                }
                for (String friend : friendsSet) {
                    RegisteredUser registeredUser = (friend.isLong()) ? RegisteredUser.findById(Long.parseLong(friend)) : RegisteredUser.findByEmail(friend)
                    if (registeredUser) {
                        registeredUser.addToCircles(getCircle())
                        registeredUser.secureSave()
                    } else {
                        ReferredUser referredUser = ReferredUser.findByEmail(friend)
                        if (referredUser) {
                            referredUser.addToCircles(getCircle())
                            referredUser.secureSave()
                        } else {
                            referredUser = new ReferredUser(name: 'ND', email: friend)
                            referredUser.addToCircles(getCircle())
                            referredUser.secureSave()
                        }
                    }
                }
                flag= true
            }catch(Exception ignored){
                status.setRollbackOnly()
            }
        }
        return flag
    }
}
