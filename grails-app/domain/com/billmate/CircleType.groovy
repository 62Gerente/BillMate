package com.billmate

class CircleType {
    static hasMany = [expenseTypes: ExpenseType, defaultExpenseType: DefaultExpenseType]

    String name

    static constraints = {
        name nullable: false
    }

    public static Set<ExpenseType> getExpenseTypeByHouse(){
        CircleType.findByName('house')?.getExpenseTypes()
    }
}
