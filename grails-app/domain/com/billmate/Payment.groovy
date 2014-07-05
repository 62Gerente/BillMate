package com.billmate

import org.springframework.format.number.CurrencyFormatter

import java.text.NumberFormat

class Payment {
    static hasMany = [actions: Action]
    static belongsTo = [Debt, User]

    Debt debt
    User user

    Double value
    Date date = new Date()
    Date createdAt = new Date()
    Date validationDate
    Boolean isValidated = false

    static constraints = {
        debt nullable: false
        user nullable: false

        value min: 0D, nullable: false
        date nullable: false
        validationDate nullable: true
        isValidated defaultValue: false, nullable: true
        createdAt nullable: false
    }

    public String toString(){
        NumberFormat formater = NumberFormat.currencyInstance
        formater.setCurrency(Currency.getInstance("EUR"))
        formater.format(value)
    }

    public Expense getExpense(){
        debt.getExpense()
    }
}
