package com.billmate

class Circle {
    static hasMany = [users: User, expenseTypes: ExpenseType,
                      expenses: Expense, actions: Action]
    static hasOne = [collective: Collective, house: House]

    String name
    String description
    Date createdAt

    static constraints = {
        collective nullable: true
        house nullable: true

        name blank: false, nullable: false
        description nullable: true, blank: true
        createdAt nullable: true, defaultValue: new Date()
    }

    public String toString(){
        name
    }

    public boolean isType(String type){
        if(this.hasProperty(type)){
            return true
        }
        return false
    }
}
