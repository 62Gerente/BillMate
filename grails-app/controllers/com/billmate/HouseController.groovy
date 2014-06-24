package com.billmate

import grails.converters.JSON

class HouseController extends RestrictedController  {

    def beforeInterceptor = [action: this.&checkSession]

    def save(){
        boolean result
        String friendsList = ((params.friendsHome != "")? params.friendsHome + "," : "") + params.identifier
        Set<String> expenseSet = params.expenseType.split(",")
        Set<String> friendsSet = friendsList.split(",")

        House house = new House(name: params.houseName, description: params.houseDescription)
        result = house.addUsersAndExpenseTypesToHouseAndSave(friendsSet,expenseSet)

        def response = [
                'error': false,
                'code': message(code: "com.billmate.house.modal.createdSuccessfully"),
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
