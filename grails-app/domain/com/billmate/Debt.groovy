package com.billmate

import com.nanlabs.grails.plugin.logicaldelete.LogicalDelete

@LogicalDelete
class Debt {
    static belongsTo = [Expense, User, RegularExpense]
    static hasMany = [payments: Payment]

    Expense expense
    RegularExpense regularExpense
    User user

    Double value
    Date resolvedDate
    Date createdAt = new Date()
    Boolean deleted = false

    static constraints = {
        expense nullable: true
        regularExpense nullable: true
        user nullable: false

        value min: 0D, nullable: false
        resolvedDate nullable: true
        createdAt nullable: false
    }

    public Double amountPaidWithoutValidation(){
        Double amount = notUnconfirmedWithoutValidationPayments().sum{ it.getValue() }
        amount ? amount : 0D
    }

    public Double amountPaid(){
        Double amount = notUnconfirmedPayments().sum{ it.getValue() }
        amount ? amount : 0D
    }

    public Double remainingAmountPaid(){
        Double amount = unconfirmedPayments().sum{ it.getValue() }
        amount ? amount : 0D
    }

    public debtWaitingValidationPayment(){
        amountInDebt() > 0
    }

    public amountInDebt(){
        value - amountPaid()
    }

    public boolean isResolved(){
        resolvedDate
    }

    public Set<Payment> notUnconfirmedWithoutValidationPayments(){
        payments.findAll{ it.getIsValidated() || it.getValidationDate() }
    }

    public Set<Payment> notUnconfirmedPayments(){
        payments.findAll{ it.getIsValidated() || !it.getValidationDate() }
    }

    public Set<Payment> unconfirmedPayments(){
        payments.findAll{ !it.getValidationDate() && !it.getIsValidated() }
    }

    public Set<Payment> validatedPayments(){
        payments.findAll{ it.getIsValidated() }
    }

    public void confirm() {
        if (!getResolvedDate() && getValue() <= amountPaid()) {
            setResolvedDate(new Date())
            save().getExpense().confirm(this)
        }
    }
}
