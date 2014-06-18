package com.billmate

import grails.converters.JSON

class HouseController extends RestrictedController  {

    def beforeInterceptor = [action: this.&checkSession]

    def index() {}

    def createHouse(){
        def response
        Circle circleInstance = new Circle()
        RegisteredUser registeredUser = authenticatedUser()
        House house = new House(name: params.houseName, description: params.houseDescription)
        house.secureSave()
        Boolean resultOperation = circleInstance.createCircle(params.expenseType, params.friendsHome + "," + params.identifier, house.getCircle())

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
        render response as JSON
    }
}
