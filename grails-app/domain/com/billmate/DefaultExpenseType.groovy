package com.billmate

import com.nanlabs.grails.plugin.logicaldelete.LogicalDelete
import org.springframework.validation.ObjectError

@LogicalDelete
class DefaultExpenseType{
    static belongsTo = [CircleType, ExpenseType]
    static hasMany = [circleTypes: CircleType]

    ExpenseType expenseType

    static constraints = {
        expenseType nullable: false
    }

    public DefaultExpenseType() {
        expenseType = new ExpenseType()
    }

    public DefaultExpenseType(Map map) {
        expenseType = new ExpenseType(map)
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

    public void setCssClass(String cssClass){
        expenseType.setCssClass(cssClass)
    }

    public String getName(){
        expenseType.getName()
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
