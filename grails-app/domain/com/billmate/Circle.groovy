package com.billmate

class Circle {
    static hasMany = [users: User, expenseTypes: ExpenseType,
                      expenses: Expense, actions: Action]
    static hasOne = [collective: Collective, house: House]

    String name
    String description
    Date createdAt = new Date()

    static constraints = {
        collective nullable: true
        house nullable: true

        name nullable: false
        description nullable: true
        createdAt nullable: false
    }

    public String toString(){
        name
    }

    public boolean isType(String type){
        if(type.equals('House')){
            return house
        }else if(type.equals('Collective')){
            return collective
        }
        return false
    }

    public Set<Expense> unresolvedExpenses(){
        return this.expenses.findAll{ !it.isResolved() }
    }

    public Set<Expense> resolvedExpenses(){
        return this.expenses.findAll{ !it.isResolved() }
    }

    public boolean createCircle(String expenseType, String friendsHouse, Circle circle){
        boolean flag = false
        StringBuilder errorMessage = new StringBuilder()
        String[] expenseSet = expenseType.split(",")
        String[] friendsSet = friendsHouse.split(",")
        withTransaction { status ->
            try {
                for (String expense : expenseSet) {
                    ExpenseType expenseTypeAux = (expense.isLong()) ? ExpenseType.findById(Long.parseLong(expense)) : null
                    if (expenseTypeAux) {
                        expenseTypeAux.addToCircles(circle)
                        expenseTypeAux.save()
                    } else {
                        CustomExpenseType customExpenseType = new CustomExpenseType(name: expense, cssClass: 'fa fa-tag')
                        customExpenseType.addToCircles(circle)
                        customExpenseType.secureSave()
                    }
                }
                for (String friend : friendsSet) {
                    RegisteredUser registeredUser = (friend.isLong()) ? RegisteredUser.findByUser(User.findById(Long.parseLong(friend))) : RegisteredUser.findByEmail(friend)
                    if (registeredUser) {
                        registeredUser.addToCircles(circle)
                        registeredUser.secureSave()
                    } else {
                        String[] friendName = friend.split("###")
                        String[] friendEmail = friend.split("###")
                        ReferredUser referredUserID = (friendEmail.length == 1) ? ReferredUser.findByUser(User.findById(Long.parseLong(friend))) : null
                        ReferredUser referredUserEmail = (friendEmail.length > 1) ? ReferredUser.findByEmail(friendEmail[1]) : null
                        RegisteredUser registeredUserEmail = (friendEmail.length > 1) ? RegisteredUser.findByEmail(friendEmail[1]) : null
                        if (referredUserID) {
                            referredUserID.addToCircles(circle)
                            referredUserID.secureSave()
                        }
                        else if(registeredUserEmail){
                            registeredUserEmail.addToCircles(circle)
                            registeredUserEmail.secureSave()
                        }
                        else if(referredUserEmail){
                            referredUserEmail.addToCircles(circle)
                            referredUserEmail.addToCircles(circle)
                        } else {
                            ReferredUser referredUser = new ReferredUser(name: friendName[0], email: friendEmail[1])
                            referredUser.addToCircles(circle)
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
