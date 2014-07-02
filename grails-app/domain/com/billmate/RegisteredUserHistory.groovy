package com.billmate

class RegisteredUserHistory {
    static mapWith = "none"
    private List<Action> actions

    RegisteredUser registeredUser
    String format
    Integer size
    Integer page
    Long circleId
    Long actionTypeId

    public List<ActionType> getActionTypes() {
        ActionType.getAll()
    }

    public List<Action> getRealizedActions() {
        if(this.actions){
            this.actions
        } else {
            def resultSize = Math.min(size > 20 ? size : 20, 100)
            def resultOffset = page < 0 ? 0 : (page - 1) * resultSize
            def circle = Circle.findById(circleId)
            def actionType = ActionType.findById(actionTypeId)
            def actions

            if (circle && actionType) {
                actions = Action.findAllByActorAndCircleAndActionType(registeredUser, circle, actionType, [max: resultSize, offset: resultOffset, sort: "actionDate", order: "desc"])
            } else {
                if (circle) {
                    actions = Action.findAllByActorAndCircle(registeredUser, circle, [max: resultSize, offset: resultOffset, sort: "actionDate", order: "desc"])
                } else if (actionType) {
                    actions = Action.findAllByActorAndActionType(registeredUser, actionType, [max: resultSize, offset: resultOffset, sort: "actionDate", order: "desc"])
                } else {
                    actions = Action.findAllByActor(registeredUser, [max: resultSize, offset: resultOffset, sort: "actionDate", order: "desc"])
                }
            }

            this.actions = actions
        }
        this.actions
    }

    public Set<Circle> getHouses() {
        registeredUser.getHouses()
    }

    public Set<Circle> getCollectives() {
        registeredUser.getCollectives()
    }
}

