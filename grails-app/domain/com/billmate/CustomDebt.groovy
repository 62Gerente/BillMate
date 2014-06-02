package com.billmate

class CustomDebt {
    static belongsTo = [Expense, User]

    Expense expense
    User user

    Double percentage
    Date createdAt = new Date()

    static constraints = {
        expense nullable: false
        user nullable: false

        percentage min: 0D, nullable: false
        createdAt nullable: false
    }

    public Double getPercentageInDecimal(){
        return percentage/100
    }
}
