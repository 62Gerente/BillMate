package com.billmate

class CircleType {
    static hasMany = [expenseTypes: ExpenseType, defaultExpenseType: DefaultExpenseType]

    String name

    static constraints = {
        name nullable: false
    }

    public Set<DefaultExpenseType> getExpensesByHouse(String type){
        getExpensesByCircle('house')
    }

    public Set<DefaultExpenseType> getExpensesByCircle(String type){
        Set<DefaultExpenseType> defaultExpenseTypeList = new HashSet<DefaultExpenseType>()
        CircleType.findAllByName(type).each { defaultExpenseTypeList.addAll(it.getDefaultExpenseType()) }
        return defaultExpenseTypeList
    }
}
