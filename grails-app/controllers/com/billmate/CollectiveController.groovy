package com.billmate

import grails.converters.JSON

class CollectiveController extends RestrictedController {

    def beforeInterceptor = [action: this.&checkSession]

    def index() {}

    def save(){
        boolean result
        String friendsList = ((params.friendsCollective != "")? params.friendsCollective + "," : "") + params.identifier
        Set<String> expenseSet = params.expenseType.split(",")
        Set<String> friendsSet = friendsList.split(",")

        def collective = new Collective(name: params.collectiveName, description: params.collectiveDescription)
        def action = new Action(actionType: ActionType.findWhere(type: ActionTypeEnum.addCollective.toString()), actor: session.user, circle: collective.getCircle())
        result = collective.addUsersAndExpenseTypesToCollectiveAndSaveWithAction(friendsSet, expenseSet, action, session.user)

        def response = [
                'error': false,
                'code': message(code: "com.billmate.collective.modal.createdSuccessfully"),
                'class': "alert alert-success form-modal-house-success"
        ]

        if (!result) {
            response.error = true
            response.code = message(code: "com.billmate.collective.modal.createdUnsuccessfully")
            response.class = "alert alert-error form-modal-house-error"
        }
        render response as JSON
    }

}
