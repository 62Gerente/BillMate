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

    public void addExpensesByIDSOrName(Set<String> expenseSet){
        for (String expense : expenseSet) {
            ExpenseType expenseType = (expense.isLong()) ? ExpenseType.findById(Long.parseLong(expense)) : null
            if (!expense.equals("") && !expenseType) {
                CustomExpenseType customExpenseType = new CustomExpenseType(name: expense)
                customExpenseType.secureSave()
                expenseType = customExpenseType.getExpenseType()
            }
            if(expenseType) circle.addToExpenseTypes(expenseType)
        }
    }

    public User addRegisteredUserToHouseByEmail(String email){
        RegisteredUser registeredUser = RegisteredUser.findByEmail(email)
        (registeredUser)? registeredUser.getUser():null
    }

    public User addReferredUserToHouseByEmail(String email){
        ReferredUser referredUser = ReferredUser.findByEmail(email)
        (referredUser)? referredUser.getUser():null
    }

    public User addReferredUserToHouseByID(long id){
        ReferredUser referredUser = User.findById(id).getReferredUser()
        (referredUser)? referredUser.getUser():null
    }

    public void addNonRegisteredUsersByEmailAndName(String name, String email){
        try{
            User user = null
            if(!email.equals("")) user = addRegisteredUserToHouseByEmail(email)
            if(!user && name.isLong()) user = addReferredUserToHouseByID(Long.parseLong(name))
            if(!user && !email.equals("")) user = addReferredUserToHouseByEmail(email)
            if(!user){
                ReferredUser referredUser = new ReferredUser(name: name, email: email)
                referredUser.secureSave()
                user = referredUser.getUser()
            }
            circle.addToUsers(user)
        }catch(Exception e){
        }
    }

    public void addUsersByIDSOrEmail(Set<String> friendsSet){
        for (String friend : friendsSet) {
            RegisteredUser registeredUser = (friend.isLong()) ? RegisteredUser.findByUser(User.findById(Long.parseLong(friend))) : RegisteredUser.findByEmail(friend)
            if (registeredUser) {
                circle.addToUsers(registeredUser.getUser())
            } else {
                String[] newReferredUser = friend.split("###")
                String email = (newReferredUser.length>1)? newReferredUser[1]:""
                if(newReferredUser[0] && !newReferredUser[0].equals(""))
                    addNonRegisteredUsersByEmailAndName(newReferredUser[0],email)
            }
        }
    }

    public boolean addUsersAndExpenseTypesToHouse(Set<String> friendsSet, Set<String> expenseTypesSet){
        boolean result = true
        withTransaction { status ->
            try {
                secureSave()
                addExpensesByIDSOrName(expenseTypesSet)
                addUsersByIDSOrEmail(friendsSet)
            }
            catch(Exception e){
                result = false
            }
        }
        return result
    }
}
