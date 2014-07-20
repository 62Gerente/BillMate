package com.billmate.api.v1

import com.billmate.Action
import com.billmate.ActionType
import com.billmate.ActionTypeEnum
import com.billmate.AuthenticationToken
import com.billmate.Collective
import com.billmate.House
import com.billmate.RegisteredUser
import grails.converters.JSON

class CollectiveController {

    static allowedMethods = [save: 'POST']
    static namespace = "v1"

    def save() {
        if (!checkToken() || !checkEmail()) {
            return
        }


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
        String friendsList = ((params.friendsHome != "") ? params.friendsHome + "," : "") + user.getUser().getId()
        Set<String> expenseSet = params.expenseType.split(",")
        Set<String> friendsSet = friendsList.split(",")

        def collective = new Collective(name: params.collectiveName, description: params.collectiveDescription)
        def action = new Action(actionType: ActionType.findWhere(type: ActionTypeEnum.addCollective.toString()), actor: user, circle: collective.getCircle())
        def result = collective.addUsersAndExpenseTypes(friendsSet, expenseSet, action, user)


        def response = []
        if (result) {
            response = collective
        } else {
            response = [
                    error: [
                            msg: message(code: "com.billmate.collective.modal.createdUnsuccessfully")
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
