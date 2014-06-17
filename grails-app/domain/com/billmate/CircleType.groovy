package com.billmate

class CircleType {
    static hasMany = [expenseTypes: ExpenseType, defaultExpenseType: DefaultExpenseType]

    String name

    static constraints = {
        name nullable: false
    }

    public Set<ExpenseType> getExpenseTypeByHouse(){
        getExpenseTypeByCircle('house')
    }

    public Set<ExpenseType> getExpenseTypeByCircle(String type){
        Set<DefaultExpenseType> defaultExpenseTypeList = new HashSet<DefaultExpenseType>()
        Set<ExpenseType> expenseTypeSet = new HashSet<ExpenseType>()
        CircleType.findAllByName(type).each { defaultExpenseTypeList.addAll(it.getDefaultExpenseType()) }
        defaultExpenseTypeList.each { expenseTypeSet.add(ExpenseType.findById(it.getExpenseTypeId())) }
        return expenseTypeSet
    }
}
