package com.billmate

class CustomDebt {
    static belongsTo = [Expense, User]

    Expense expense
    User user

    Integer percentage
    Date createdAt

    static constraints = {
        expense nullable: false
        user nullable: false

        percentage min: 0, nullable: false
        createdAt nullable: false, defaultValue: new Date(), min: new Date()
    }
}
