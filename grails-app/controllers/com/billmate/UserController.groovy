package com.billmate

import grails.converters.JSON

class UserController extends RestrictedController {
    static allowedMethods = [updateField: "POST"]

    def beforeInterceptor = [action: this.&checkSession]

    def updateProperty(Long id) {
        def user = User.findById(id)
        def property = params.name
        def value = params.value

        user.setProperty(property, value)

        def response = [
                'error'  : false,
                'message': message(code: "com.billmate.user.updateProperty.success")
        ]

        if(!user.save()) {
            response.error = true
            response.message = message(error: user.getErrors().getAllErrors().first());
        }

        render response as JSON
    }

    def calendarEvents(Long id){

        RegisteredUser registeredUser = RegisteredUser.findById(id)

        return ['user': registeredUser]
    }

    def teste(Long id){
        def listEvents = []
        RegisteredUser registeredUser = RegisteredUser.findById(id)
        User user = registeredUser?.getUser()
        //Alterar a query toda
        user?.getExpenses().each { listEvents.add(title: it.getTitle(), start: it.getBeginDate(), end: it.getEndDate())
            listEvents.add(title: "Limite de pagamento de "+it.getTitle(), start: it.getPaymentDeadline())
            listEvents.add(title: "Limite de receção de "+it.getTitle(), start: it.getReceptionDeadline())
        }

        /*listEvents = []
        listEvents.add(title: 'All Day Event', start: '2014-07-06')*/

        render listEvents as JSON
    }
}
