package com.billmate

class RegisteredUserReports {
    static mapWith = "none"

    RegisteredUser registeredUser
    Long expenseTypeID
    Long circleID
    String dateInterval

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

    public Set<Expense> filteredExpenses(){
        Set<Expense> expenses = getUser().getExpenses()

        expenses.findAll {
            Boolean status = true

            if(expenseTypeID && it.getExpenseTypeId() != expenseTypeID){
                status = false
            }
            if(circleID && it.getCircleId() != circleID){
                status = false
            }
            if(dateInterval){
                Date date = new Date()
                switch (dateInterval) {
                    case "week":
                        date.set(date: date[Calendar.DATE] - date[Calendar.DAY_OF_WEEK]) //FIX THIS
                        break
                    case "month":
                        date.set(date: 1)
                        break
                    case "year":
                        date.set(date: 1, month: 1)
                        break
                }
                if(it.getBeginDate().before(date)){
                    status = false
                }
            }

            status
        }

    }

    public List filteredExpensesJSON(){
        List json = []

        filteredExpenses().each {
            json.add([
                cssClass: it.getExpenseType().getCssClass(),
                title: it.getTitle(),
                total: it.getValue(),
                myQuota: it.amountAssignedTo(registeredUser.getUserId()),
                invoice: it.getInvoice() ? it.getInvoice().getId() : false,
                receipt: it.getReceipt() ? it.getReceipt().getId() : false,
                beginDate: it.getBeginDate().format("dd-MM-yyyy")
            ])
        }

        json
    }
}
