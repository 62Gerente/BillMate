package com.billmate

import grails.converters.JSON

class HouseController extends RestrictedController  {

    def beforeInterceptor = [action: this.&checkSession]

    def save(){
        boolean result
        String friendsList = (params.friendsHouse != "")? params.friendsHouse : ""
        Set<String> expenseSet = params.expenseType.split(",")
        Set<String> friendsSet = friendsList.split(",")
        if(params.identifier != null) friendsSet.add(params.identifier)

        def house = new House(name: params.houseName, description: params.houseDescription)
        def action = new Action(actionType: ActionType.findWhere(type: ActionTypeEnum.addHouse.toString()), actor: authenticatedUser(), circle: house.getCircle())
        result = house.addUsersAndExpenseTypes(friendsSet, expenseSet, action, authenticatedUser())

        def response = [
                'error': false,
                'code': message(code: "com.billmate.house.save.success"),
                'class': "alert alert-success form-modal-house-success"
        ]

        if (!result) {
            response.error = true
            response.code = message(code: "com.billmate.house.modal.createdUnsuccessfully")
            response.class = "alert alert-error form-modal-house-error"
        }
        render response as JSON
    }

}
