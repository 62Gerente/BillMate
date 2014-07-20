package com.billmate.api.v1

import com.billmate.Action
import com.billmate.ActionType
import com.billmate.ActionTypeEnum
import com.billmate.AuthenticationToken
import com.billmate.House
import com.billmate.RegisteredUser
import grails.converters.JSON

class HouseController {

    static allowedMethods = [save: 'POST']
    static namespace = "v1"

    def save() {
        if (!checkToken() || !checkEmail()) {
            return
        }
        String friendsList = ((params.friendsHome != "") ? params.friendsHome + "," : "") + params.identifier
        Set<String> expenseSet = params.expenseType.split(",")
        Set<String> friendsSet = friendsList.split(",")

        def token = AuthenticationToken.findByTokenAndEmail(params.token, params.email)
        if (!token) {
            response = [
                    'error': [
                            msg: message(code: "com.billmate.authenticationtoken.token.invalid")
                    ]
            ]
            render response as JSON
            return
        }
        def user = RegisteredUser.findByEmail(token.getEmail())


        def house = new House(name: params.houseName, description: params.houseDescription)
        def action = new Action(actionType: ActionType.findWhere(type: ActionTypeEnum.addHouse.toString()), actor: user, circle: house.getCircle())
        def result = house.addUsersAndExpenseTypes(friendsSet, expenseSet, action, user)

        def response = []
        if (result) {
            response = house
        } else {
            response = [
                    error: [
                            msg: message(code: "com.billmate.house.modal.createdUnsuccessfully")
                    ]
            ]
        }
        render response as JSON
    }

    private checkToken() {
        def response = []
        if (!params.token) {
            response = [
                    error: [
                            'msg': message(code: "com.billmate.authenticationtoken.token.null")
                    ]
            ]
            render response as JSON
        }
        return true
    }

    private checkEmail() {
        def emailPattern = /[_A-Za-z0-9-]+(\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\.[A-Za-z0-9]+)*(\.[A-Za-z]{2,})/
        if (!params.email ==~ emailPattern) {
            def response = [
                    'error': [
                            'msg': message(code: "com.billmate.register.save.invalidEmail")
                    ]
            ]
            render response as JSON
        }
        return true
    }


}
