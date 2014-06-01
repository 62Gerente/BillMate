package com.billmate

class Payment {
    static belongsTo = [Expense, User]

    Expense expense
    User user

    Double value
    Date date = new Date()
    Date createdAt = new Date()
    Date validationDate
    Boolean validated

    static constraints = {
        expense nullable: false
        user nullable: false

        value min: 0D, nullable: false
        date nullable: false
        validationDate nullable: true
        validated defaultValue: false, nullable: true
        createdAt nullable: false
    }
}
