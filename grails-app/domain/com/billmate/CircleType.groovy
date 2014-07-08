package com.billmate

import com.nanlabs.grails.plugin.logicaldelete.LogicalDelete

@LogicalDelete
class CircleType {
    static hasMany = [expenseTypes: ExpenseType, defaultExpenseType: DefaultExpenseType]

    String name

    static constraints = {
        name nullable: false
    }

    public static Set<ExpenseType> getHouseExpenseTypes(){
        CircleType.findByName('house')?.getExpenseTypes()
    }

    public static Set<ExpenseType> getCollectiveExpenseTypes(){
        CircleType.findByName('collective')?.getExpenseTypes()
    }
}
