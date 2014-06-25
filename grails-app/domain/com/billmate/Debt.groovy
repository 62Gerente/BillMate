package com.billmate

class Debt {
    static belongsTo = [Expense, User, RegularExpense]

    Expense expense
    RegularExpense regularExpense
    User user

    Double percentage
    double value
    Date createdAt = new Date()

    static constraints = {
        expense nullable: true
        regularExpense nullable: true
        user nullable: false

        percentage min: 0D, nullable: true
        value min: 0D, nullable: false
        createdAt nullable: false
    }

    public Double getPercentageInDecimal(){
        return percentage/100
    }
}
