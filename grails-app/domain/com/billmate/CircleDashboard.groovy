package com.billmate

class CircleDashboard {
    static mapWith = "none"

    RegisteredUser registeredUser
    Circle circle

    public User getUser(){
        registeredUser.getUser()
    }

    public Set<User> getMembers(){
        circle.getUsers()
    }

    public Set<RegularExpense> regularExpensesInReceptionTime(){
        circle.regularExpensesInReceptionTime()
    }

    public Set<Expense> unresolvedExpenses(){
        circle.unresolvedExpenses()
    }
}
