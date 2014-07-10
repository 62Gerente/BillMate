package com.billmate

class CircleHistory {
    static mapWith = "none"
    private List<Action> actions

    Circle circle
    String format
    Integer size
    Integer page
    Long userId
    Long actionTypeId

    public List<ActionType> getActionTypes() {
        ActionType.findAllByTypeNotEqual(ActionTypeEnum.signUp.toString())
    }

    public List<Action> getRealizedActions() {
        if(this.actions){
            this.actions
        } else {
            def resultSize = Math.min(size > 20 ? size : 20, 100)
            def resultOffset = page < 0 ? 0 : (page - 1) * resultSize
            def registeredUser = RegisteredUser.findById(userId)
            def actionType = ActionType.findById(actionTypeId)
            def actions

            if (registeredUser && actionType) {
                actions = Action.findAllByCircleAndActorAndActionType(circle, registeredUser, actionType, [max: resultSize, offset: resultOffset, sort: "actionDate", order: "desc"])
            } else {
                if (registeredUser) {
                    actions = Action.findAllByCircleAndActor(circle, registeredUser, [max: resultSize, offset: resultOffset, sort: "actionDate", order: "desc"])
                } else if (actionType) {
                    actions = Action.findAllByCircleAndActionType(circle, actionType, [max: resultSize, offset: resultOffset, sort: "actionDate", order: "desc"])
                } else {
                    actions = Action.findAllByCircle(circle, [max: resultSize, offset: resultOffset, sort: "actionDate", order: "desc"])
                }
            }

            this.actions = actions
        }
        this.actions
    }

    public Set<RegisteredUser> getRegisteredUsers() {
        circle.getRegisteredUsers()
    }
}

