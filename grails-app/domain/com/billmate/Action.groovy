package com.billmate

class Action {
    static belongsTo = [ActionType, Expense, RegisteredUser, User, Circle, Payment]
    static hasMany = [systemNotifications: SystemNotification]

    ActionType actionType
    RegisteredUser actor
    User user
    Circle circle
    Expense expense
    RegularExpense regularExpense
    Payment payment

    Date actionDate = new Date()

    static constraints = {
        actionType nullable: false
        actor nullable: true
        user nullable: true
        circle nullable: true
        expense nullable: true
        regularExpense nullable: true
        payment nullable: true

        actionDate nullable: false
    }

    public Map toJSON(){
        // Get all fields of an action, even null
        def actionType = getActionType().getType().toString()
        def actionDate = getActionDate()
        def actionCircle = getCircle()
        def actionExpense = getExpense()
        def actionRegularExpense = getRegularExpense()
        def actionUser = getUser()

        def jsonMap= [
                type: actionType,
                date: actionDate.toString(),
                icon: getActionType().getIcon(),
                cssClass: getActionType().getCssClass()
        ]

        if(actionCircle) {
            jsonMap.circle = actionCircle.getName()
            jsonMap.circleUsers = actionCircle.getUsersPhotos()
            jsonMap.circleCssClass = actionCircle.getCssClass()
        }

        if(actionExpense) {
            jsonMap.expenseTitle = actionExpense.getTitle()
            jsonMap.expenseTotal = actionExpense.getValue()
        }

        if(actionRegularExpense) {
            jsonMap.regularExpense = actionRegularExpense.getTitle()
        }

        if(actionUser) {
            jsonMap.user = actionUser.getName()
        }

        jsonMap
    }

}
