package com.billmate

import com.lucastex.grails.fileuploader.UFile

abstract class ExpenseType {
    static belongsTo = [Circle, Expense, CircleType]
    static hasMany = [circles: Circle, expenses: Expense, circleTypes: CircleType]

    String name
    UFile icon

    static constraints = {
        name blank: false, nullable: false
        icon nullable: true
    }

    String toString(){
        name
    }
}
