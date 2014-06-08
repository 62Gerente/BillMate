package com.billmate

import groovy.time.TimeCategory

class User {
    static belongsTo = [Circle, Expense]
    static hasMany = [circles: Circle, payments: Payment, customizedDebts: CustomDebt, referencedActions: Action, expenses: Expense]
    static hasOne = [referredUser: ReferredUser, registeredUser: RegisteredUser]

    String name
    String email
    Date createdAt = new Date()

    static constraints = {
        referredUser nullable: true
        registeredUser nullable: true

        name nullable: false
        email email: true, unique: true, nullable: false
        createdAt nullable: false
    }

    static mapping = {
        table '`user`'
    }

    public String toString(){
        name ? name : email
    }

    public Double totalDebt(){
        Double total = unresolvedExpenses().sum{ it.debtOf(this.id) }
        total ? total : 0D
    }

    public Set<Expense> unresolvedExpenses(){
        expenses.findAll{ !it.isResolvedBy(this.id) }
    }

    public Set<RegisteredUser> whoIOwe(){
        Set<RegisteredUser> users = new HashSet<>()
        unresolvedExpenses().each { users.add( it.getResponsible() ) }
        users
    }

    public Set<Expense> unresolvedExpensesWhoResponsibleIs(Long registeredUserId){
        unresolvedExpenses().findAll{ it.getResponsibleId() == registeredUserId }
    }

    public Double totalDebtTo(Long registeredUserId){
        Double total = unresolvedExpensesWhoResponsibleIs(registeredUserId).sum{ it.debtOf(this.id) }
        total ? total : 0D
    }

    public String getPhotoOrDefault(){
        def linkGenerator = this.domainClass.grailsApplication.mainContext.grailsLinkGenerator
        if(registeredUser){
            return registeredUser.getPhotoOrDefault()
        }else{
            return linkGenerator.resource(dir: 'images',file: 'default-user.png', absolute: true)
        }
    }

    public Set<Expense> monthExpenses(Date date){
        Set<Expense> monthExpenses = new HashSet<>()
        monthExpenses.addAll(expenses.findAll{ it.getBeginDate().getAt(Calendar.MONTH) == date.getAt(Calendar.MONTH) && it.getBeginDate().getAt(Calendar.YEAR) == date.getAt(Calendar.YEAR)  })

        if(registeredUser){
            monthExpenses.addAll(registeredUser.monthResponsibleExpenses(date))
        }

        monthExpenses
    }

    public Double monthlySpending(Date date){
        Double monthlySpending = monthExpenses(date).sum {it.valueAssignedTo(this.id)}
        monthlySpending ? monthlySpending : 0
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

        orderList[0..(expenses < orderList.size() ? expenses : orderList.size())-1]
    }

    public Set<Expense> monthExpensesOfExpenseType(Date date, ExpenseType expenseType){
        Set<Expense> expenses = new HashSet<>()
        expenses.addAll(monthExpenses(date).findAll{ it.getExpenseType().getId() == expenseType.getId() })

        if(registeredUser){
            expenses.addAll(registeredUser.monthResponsibleExpensesOfExpenseType(date, expenseType))
        }

        expenses
    }

    public Double monthlySpendingOfExpenseType(Date date, ExpenseType expenseType){
        Double monthlySpending = monthExpensesOfExpenseType(date, expenseType).sum {it.valueAssignedTo(this.id)}
        monthlySpending ? monthlySpending : 0
    }
}
