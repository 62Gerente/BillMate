package com.billmate

class RegisteredUserDashboard {
    static mapWith = "none"

    RegisteredUser registeredUser

    public User getUser(){
        registeredUser.getUser()
    }

    public Double totalDebt(){
        getUser().totalDebt()
    }

    public Set<RegisteredUser> whoIOwe(){
        getUser().whoIOwe()
    }

    public Double totalAsset(){
        registeredUser.totalAsset()
    }

    public Set<User> whoOweMe(){
        registeredUser.whoOweMe()
    }

    public Set<Payment> unconfirmedPayments(){
        registeredUser.unconfirmedPaymentsOnResponsibleExpenses()
    }

    public Set<RegularExpense> regularExpensesInReceptionTime(){
        getUser().regularExpensesInReceptionTime()
    }

    public List<ExpenseType> expenseTypesWithMoreSpendingInLastMonths(){
        getUser()?.expenseTypesWithMoreSpendingInLastMonths(6,5)
    }

    public Long getTotalBalanceExpensesWhoIAmNotResponsible(){
        return registeredUser.getTotalWhoIOwe()
    }

    public Long getTotalBalance(){
        return registeredUser.getTotalBalance()
    }
}
