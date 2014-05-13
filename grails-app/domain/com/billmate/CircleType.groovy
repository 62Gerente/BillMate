package com.billmate

class CircleType {
    static hasMany = [defaultExpenseTypes: DefaultExpenseType]

    Integer identifier
    String name

    static constraints = {
        identifier nullable: false
        name nullable: false, blank: false
    }
}
