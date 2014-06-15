package com.billmate

import org.springframework.validation.ObjectError

class DefaultExpenseType{
    static belongsTo = [CircleType, ExpenseType]
    static hasMany = [circleTypes: CircleType]

    ExpenseType expenseType

    static constraints = {
        expenseType nullable: false
    }

    public DefaultExpenseType() {
        super()
        expenseType = new ExpenseType()
    }

    public DefaultExpenseType(Map map) {
        super(map)
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

    public boolean secureSave(){
        withTransaction { status ->
            try {
                expenseType.save(flush: true, failOnError: true)
                save(flush: true, failOnError: true)
                return true
            }catch(Exception ignored){
                status.setRollbackOnly()
                return false
            }
        }
    }

    public DefaultExpenseType[] getListOfDefaultExpenseTypeForCircles(int identifier){
        Set<CircleType> circleTypes = CircleType.findAllByIdentifier(identifier)
        Set<DefaultExpenseType> defaultExpenseTypes = DefaultExpenseType.findAllByCircleTypes(circleTypes)
        return defaultExpenseTypes.toArray()
    }
}
