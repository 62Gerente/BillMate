package com.billmate

import com.lucastex.grails.fileuploader.UFile

class ExpenseType {
    static belongsTo = [Circle, Expense]
    static hasMany = [circles: Circle, expenses: Expense]

    String name
    UFile icon

    static constraints = {
        name blank: false, nullable: false
        icon nullable: false
    }

    String toString(){
        name
    }
}
