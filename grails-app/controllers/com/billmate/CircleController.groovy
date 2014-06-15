package com.billmate

import grails.converters.JSON

class CircleController extends RestrictedController  {

    def beforeInterceptor = [action: this.&checkSession]

    def index() {}

    def createHouse(){
        def response
        if(params.houseName=="" || params.houseDescription=="" || params.expenseType=="" || params.friendsHome==""){
            response = [
                    'error': true,
                    'code': message(code: "com.billmate.house.modal.created_unsuccessfully_missing_fields"),
                    'class': "alert alert-error form-modal-house-error"
            ]
        }
        else {
            RegisteredUser registeredUser = authenticatedUser()
            House house = new House(name: params.houseName, description: params.houseDescription)
            house.secureSave()
            Boolean resultOperation = house.createHouse(params.expenseType, params.friendsHome + "," + authenticatedUser().getId())

            response = [
                    'error': false,
                    'code': message(code: "com.billmate.house.modal.created_successfully"),
                    'class': "alert alert-success form-modal-house-success"
            ]

            if (!resultOperation) {
                response.error = true
                response.code = message(code: "com.billmate.house.modal.created_unsuccessfully")
                response.class = "alert alert-error form-modal-house-error"
            }
        }
        render response as JSON
    }
}
