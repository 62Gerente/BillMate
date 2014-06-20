package com.billmate

class Circle {
    static hasMany = [users: User, expenseTypes: ExpenseType,
                      expenses: Expense, actions: Action, regularExpenses: RegularExpense]
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
        this.expenses.findAll{ !it.isResolved() }
    }

    public Set<Expense> resolvedExpenses(){
        this.expenses.findAll{ !it.isResolved() }
    }

    public String getCssClass(){
        if(collective){
            return collective.getCssClass()
        }else if(house){
            return  house.getCssClass()
        }
    }

    public Set<RegularExpense> regularExpensesInReceptionTime(){
        regularExpenses.findAll{ it.inReceptionTime() }
    }

    public String getCssColor(){
        if(collective){
            return collective.getCssColor()
        }else if(house){
            return  house.getCssColor()
        }
    }

    public Set<User> getUsersWithout(Long userId){
        users.findAll { it.getId() != userId }
    }

    public void addExpensesByIDSOrName(Set<String> expenseSet){
        for (String expense : expenseSet) {
            ExpenseType expenseType = (expense.isLong()) ? ExpenseType.findById(Long.parseLong(expense)) : null
            if (!expense.equals("") && !expenseType) {
                CustomExpenseType customExpenseType = new CustomExpenseType(name: expense)
                customExpenseType.secureSave()
                expenseType = customExpenseType.getExpenseType()
            }
            if(expenseType) addToExpenseTypes(expenseType)
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
            addToUsers(user)
        }catch(Exception e){
        }
    }

    public void addUsersByIDSOrEmail(Set<String> friendsSet){
        for (String friend : friendsSet) {
            RegisteredUser registeredUser = (friend.isLong()) ? RegisteredUser.findByUser(User.findById(Long.parseLong(friend))) : RegisteredUser.findByEmail(friend)
            if (registeredUser) {
                addToUsers(registeredUser.getUser())
            } else {
                String[] newReferredUser = friend.split("###")
                String email = (newReferredUser.length>1)? newReferredUser[1]:""
                if(newReferredUser[0] && !newReferredUser[0].equals(""))
                    addNonRegisteredUsersByEmailAndName(newReferredUser[0],email)
            }
        }
    }
}
