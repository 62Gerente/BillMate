package com.billmate

import com.lucastex.grails.fileuploader.UFile
import com.nanlabs.grails.plugin.logicaldelete.LogicalDelete

@LogicalDelete
class ExpenseType {
    static belongsTo = [Circle]
    static hasMany = [circles: Circle, expenses: Expense, regularExpenses: RegularExpense]
    static hasOne = [customExpenseType: CustomExpenseType, defaultExpenseType: DefaultExpenseType]

    String name
    String cssClass

    static constraints = {
        customExpenseType nullable: true
        defaultExpenseType nullable: true

        name nullable: false
        cssClass nullable: true
    }

    String toString(){
        name
    }

    public static Set<ExpenseType> getFromAllCirclesByTerm(String term) {
        Set<ExpenseType> list = new HashSet<ExpenseType>()
        ExpenseType.findAll().each { if(it.getName().toUpperCase().contains(term)) list.add(it) }
        return list
    }

    public static Set<Object> getFromAllCirclesByTermFormated(String term, Long id_circle){
        Boolean result = false
        Set<Object> list = new HashSet<>()
        Circle circle = Circle.findById(id_circle)
        getFromAllCirclesByTerm(term).each {
            ExpenseType expenseType = it
            result = false
            circle.getExpenseTypes().each { if(it.getId() == expenseType.getId()) result = true }
            if(!result){
                list.add([id  : it.getId(), cssClass: it.getCssClass(), name: it.getName()])
            }
        }
        return list
    }
}
