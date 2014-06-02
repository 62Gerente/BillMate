package com.billmate

class Action {
    static belongsTo = [ActionType, Expense, RegisteredUser, User, Circle]
    static hasMany = [systemNotifications: SystemNotification]

    ActionType actionType
    Expense expense
    RegisteredUser actor
    User user
    Circle circle

    Date actionDate = new Date()

    static constraints = {
        actionType nullable: false
        expense nullable: true
        actor nullable: true
        user nullable: true
        circle nullable: true

        actionDate nullable: false
    }

    public boolean secureSave(){
        withTransaction { status ->
            try {
                save(flush: true, failOnError: true)
                return true
            }catch(Exception ignored){
                status.setRollbackOnly()
                return false
            }
        }
    }

    /*
    * Se a lista não for vazia é porque há notificações por ler.
    * */
    public boolean isRead(Set<SystemNotification> listNotifications){
        int idD = getUserId()
        List<SystemNotification> lista = SystemNotification.findAll()
        Set<Notification> resultList = listNotifications.findAll({!((SystemNotification)it).getIs_read() && ((SystemNotification)it).getRegisteredUser().getUserId() == getUserId()})
        return resultList.isEmpty()
    }
}
