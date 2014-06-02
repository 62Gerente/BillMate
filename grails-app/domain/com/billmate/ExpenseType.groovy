package com.billmate

import com.lucastex.grails.fileuploader.UFile

class ExpenseType {
    static belongsTo = [Circle]
    static hasMany = [circles: Circle, expenses: Expense]
    static hasOne = [customExpenseType: CustomExpenseType, defaultExpenseType: DefaultExpenseType]

    String name
    String cssClass

    static constraints = {
        customExpenseType nullable: true
        defaultExpenseType nullable: true

        name nullable: false
        cssClass nullable: true
    }

    String toString(){
        name
    }
}
