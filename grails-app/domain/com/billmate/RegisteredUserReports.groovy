package com.billmate

class RegisteredUserReports {
    static mapWith = "none"

    RegisteredUser registeredUser

    public User getUser(){
        registeredUser.getUser()
    }

    public Set<Expense> expensesOfMonth(){
        Date beginOfMonth = new Date()
        beginOfMonth.set(date: 1)

        getUser().getExpenses().findAll{ it.getBeginDate().after(beginOfMonth) }
    }

    public Double totalValueOfExpensesOfMonth(){
        Double value = expensesOfMonth().sum{ it.getValue() }
        value ? value : 0D
    }

    public Double myQuotaOfTotalValueOfMonth(){
        Double value = expensesOfMonth().sum{ it.amountAssignedTo(registeredUser.getUserId()) }
        value ? value : 0D
    }

    public Set<House> houses(){
        registeredUser.getHouses()
    }

    public Set<Collective> collectives(){
        registeredUser.getCollectives()
    }

    public Set<ExpenseType> expenseTypes(){
        Set<ExpenseType> expenseTypes = new HashSet<ExpenseType>()

        registeredUser.getCircles().each {
            expenseTypes.addAll(it.getExpenseTypes())
        }

        expenseTypes
    }
}
