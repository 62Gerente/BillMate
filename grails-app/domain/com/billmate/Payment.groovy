package com.billmate

import com.nanlabs.grails.plugin.logicaldelete.LogicalDelete
import org.springframework.format.number.CurrencyFormatter

import java.text.NumberFormat

@LogicalDelete
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
        NumberFormat formatter = NumberFormat.currencyInstance
        formatter.setCurrency(Currency.getInstance("EUR"))
        formatter.format(value)
    }

    public Expense getExpense(){
        debt.getExpense()
    }
}
