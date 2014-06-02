package com.billmate

import org.springframework.validation.ObjectError

class RegularExpense {
    static belongsTo = [DirectDebit, Expense]

    DirectDebit directDebit
    Expense expense

    Date receptionBeginDate
    Date receptionEndDate
    Date paymentBeginDate
    Date paymentEndDate

    static constraints = {
        directDebit nullable: true
        expense nullable: true

        receptionBeginDate nullable: true
        receptionEndDate nullable: true
        paymentBeginDate nullable: true
        paymentEndDate nullable: true
    }

    public RegularExpense(){
        super()
        expense = new Expense()
    }

    public RegularExpense(Map map){
        super(map)
        expense = new Expense(map)
    }

    def beforeValidate() {
        expense.validate()
        expense.errors.getAllErrors().each {
            ObjectError objectError = (ObjectError) it
            this.errors.reject(objectError.getCode(), objectError.toString())
        }
    }

    public void setTitle(String name){
        expense.setTitle(name)
    }

    public void setDescription(String description){
        expense.setDescription(description)
    }

    public void setValue(Double value){
        expense.setValue(value)
    }

    public void setResponsible(RegisteredUser registeredUser){
        expense.setResponsible(registeredUser)
    }

    public void setExpenseType(ExpenseType expenseType){
        expense.setExpenseType(expenseType)
    }

    public void setCircle(Circle circle){
        expense.setCircle(circle)
    }

    public String toString() {
        expense.toString();
    }

    public boolean secureSave(){
        withTransaction { status ->
            try {
                expense.save(flush: true, failOnError: true)
                save(flush: true, failOnError: true)
                return true
            }catch(Exception ignored){
                status.setRollbackOnly()
                return false
            }
        }
    }

    public void addToAssignedUsers(User user){
        expense.addToAssignedUsers(user)
    }
}
