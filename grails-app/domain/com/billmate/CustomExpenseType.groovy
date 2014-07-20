package com.billmate

import com.nanlabs.grails.plugin.logicaldelete.LogicalDelete
import org.springframework.validation.ObjectError

@LogicalDelete
class CustomExpenseType{
    static belongsTo = ExpenseType

    ExpenseType expenseType

    static constraints = {
        expenseType nullable: false
    }

    public CustomExpenseType() {
        expenseType = new ExpenseType()
        setCssClass()
    }

    public CustomExpenseType(Map map) {
        expenseType = new ExpenseType(map)
        setCssClass()
    }

    def beforeValidate() {
        expenseType.validate()
        expenseType.errors.getAllErrors().each {
            ObjectError objectError = (ObjectError) it
            this.errors.reject(objectError.getCode(), objectError.toString())
        }
    }

    public String toString() {
        return expenseType.toString();
    }

    public void setName(String name){
        expenseType.setName(name)
    }

    public void setCssClass(){
        expenseType.setCssClass("fa fa-tag")
    }

    public void addToCircles(Circle circle){
        expenseType.addToCircles(circle)
    }

    public void persist() throws Exception{
        expenseType.save(flush: true, failOnError: true)
        save(flush: true, failOnError: true)
    }

    public boolean secureSave(){
        withTransaction { status ->
            try {
                persist()
                return true
            }catch(Exception eSave){
                status.setRollbackOnly()
                return false
            }
        }
    }
}
