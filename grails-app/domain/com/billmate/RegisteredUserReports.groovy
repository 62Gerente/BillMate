package com.billmate

class RegisteredUserReports {
    static mapWith = "none"

    RegisteredUser registeredUser

    public User getUser(){
        registeredUser.getUser()
    }

    public Set<Expense> expenses(){
        getUser().getExpenses()
    }

    public Double totalValueOfExpenses(){
        Double value = expenses().sum{ it.getValue() }
        value ? value : 0D
    }


    public Double myQuotaOfTotalValue(){
        Double value = expenses().sum{ it.amountAssignedTo(registeredUser.getUserId()) }
        value ? value : 0D
    }
}
