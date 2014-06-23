package com.billmate

import groovy.time.TimeCategory

class Circle {
    static hasMany = [users: User, expenseTypes: ExpenseType,
                      expenses: Expense, actions: Action, regularExpenses: RegularExpense]
    static hasOne = [collective: Collective, house: House]

    String name
    String description
    Date createdAt = new Date()

    static constraints = {
        collective nullable: true
        house nullable: true

        name nullable: false
        description nullable: true
        createdAt nullable: false
    }

    public String toString(){
        name
    }

    public boolean isType(String type){
        if(type.equals('House')){
            return house
        }else if(type.equals('Collective')){
            return collective
        }
        return false
    }

    public Set<Expense> unresolvedExpenses(){
        this.expenses.findAll{ !it.isResolved() }
    }

    public Set<Expense> resolvedExpenses(){
        this.expenses.findAll{ !it.isResolved() }
    }

    public String getCssClass(){
        if(collective){
            return collective.getCssClass()
        }else if(house){
            return  house.getCssClass()
        }
    }

    public Set<RegularExpense> regularExpensesInReceptionTime(){
        regularExpenses.findAll{ it.inReceptionTime() }
    }

    public String getCssColor(){
        if(collective){
            return collective.getCssColor()
        }else if(house){
            return  house.getCssColor()
        }
    }

    public Set<User> getUsersWithout(Long userId){
        users.findAll { it.getId() != userId }
    }

    public List<Action> latestEvents(){
        Set<Action> latestEvents = new HashSet<>();

        latestEvents.addAll( actions )
        expenses.each{ latestEvents.addAll( it.getActions() ) }
        regularExpenses.each{ latestEvents.addAll( it.getActions() ) }

        latestEvents.sort{ it.getActionDate() }
    }

    public Double monthlySpendingOfExpenseType(Date date, ExpenseType expenseType) {
        Double monthlySpending = monthExpensesOfExpenseType(date, expenseType).sum { it.valueAssignedTo(this.id) }
        monthlySpending ? monthlySpending : 0
    }

    public Set<Expense> monthExpensesOfExpenseType(Date date, ExpenseType expenseType){
        Set<Expense> expenses = new HashSet<>()
        expenses.addAll(monthExpenses(date).findAll{ it.getExpenseType().getId() == expenseType.getId() })

        expenses
    }

    public Set<Expense> monthExpenses(Date date){
        Set<Expense> monthExpenses = new HashSet<>()
        monthExpenses.addAll(expenses.findAll{ it.getBeginDate().getAt(Calendar.MONTH) == date.getAt(Calendar.MONTH) && it.getBeginDate().getAt(Calendar.YEAR) == date.getAt(Calendar.YEAR)  })

        monthExpenses
    }

    public Set<Expense> lastMonthsExpenses(Integer months){
        Set<Expense> lastMonthsExpenses = new HashSet<>()

        while ( months-- >= 0 ) {
            use(TimeCategory) {
                lastMonthsExpenses.addAll(monthExpenses(new Date() - (months-1).months))
            }
        }

        lastMonthsExpenses
    }

    public List<ExpenseType> expenseTypesWithMoreSpendingInLastMonths(Integer months, Integer expenses){
        Map map = lastMonthsExpenses(months).groupBy { expense -> expense.getExpenseType() }
        List<ExpenseType> expenseTypes = new ArrayList<>(map.keySet());

        List<ExpenseType> orderList = expenseTypes.sort { map.get(it).sum{ it.valueAssignedTo(this.id) } }

        orderList.take(expenses)
    }
}
