package com.billmate

class Payment {
    static hasMany = [actions: Action]
    static belongsTo = [Expense, User]

    Expense expense
    User user

    Double value
    Date date = new Date()
    Date createdAt = new Date()
    Date validationDate
    Boolean isValidated = false

    static constraints = {
        expense nullable: false
        user nullable: false

        value min: 0D, nullable: false
        date nullable: false
        validationDate nullable: true
        isValidated defaultValue: false, nullable: true
        createdAt nullable: false
    }
}
