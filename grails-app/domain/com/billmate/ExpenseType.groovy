package com.billmate

import com.lucastex.grails.fileuploader.UFile

class ExpenseType {
    static belongsTo = [Circle, Expense]
    static hasMany = [circles: Circle, expenses: Expense]
    static hasOne = [customExpenseType: CustomExpenseType, defaultExpenseType: DefaultExpenseType]

    String name
    UFile icon

    static constraints = {
        customExpenseType nullable: true
        defaultExpenseType nullable: true

        name nullable: false
        icon nullable: true
    }

    String toString(){
        name
    }
}
