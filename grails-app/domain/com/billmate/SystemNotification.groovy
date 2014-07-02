package com.billmate

import org.springframework.validation.ObjectError

class SystemNotification{
    static belongsTo = [Action, RegisteredUser, Notification]

    Action action
    RegisteredUser registeredUser
    Notification notification

    Boolean isRead = false

    static constraints = {
        action nullable: false
        registeredUser nullable: false
        notification nullable: false

        isRead nullable: false, defaultValue: false
    }

    public SystemNotification() {
        super()
        notification = new Notification()
    }

    public SystemNotification(Map map) {
        super(map)
        notification = new Notification(map)
    }

    def beforeValidate() {
        notification.validate()
        notification.errors.getAllErrors().each {
            ObjectError objectError = (ObjectError) it
            this.errors.reject(objectError.getCode(), objectError.toString())
        }
    }

    public ActionType getActionType(){
        action.getActionType()
    }

    public RegisteredUser getActor(){
        action.getActor()
    }

    public User getUser(){
        action.getUser()
    }

    public Circle getCircle(){
        action.getCircle()
    }

    public RegularExpense getRegularExpense(){
        action.getRegularExpense()
    }

    public Expense getExpense(){
        action.getExpense()
    }

    public Expense getPayment(){
        action.getPayment()
    }

    public void persist() throws Exception{
        notification.save(flush: true, failOnError: true)
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

    public boolean markAsRead(){
        isRead = true;
        secureSave();
    }
}
