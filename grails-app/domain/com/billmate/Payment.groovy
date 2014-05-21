package com.billmate

class Payment {
    static belongsTo = [Expense, User]

    Expense expense
    User user

    Double value
    Date date
    Date createdAt
    Date validationDate
    Boolean validated

    static constraints = {
        expense nullable: false
        user nullable: false

        value min: 0D, nullable: false
        date defaultValue: new Date(), nullable: false
        validationDate nullable: true
        validated defaultValue: false, nullable: true
        createdAt nullable: false, defaultValue: new Date(), min: new Date()
    }
}
