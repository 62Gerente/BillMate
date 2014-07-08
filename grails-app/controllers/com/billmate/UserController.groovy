package com.billmate

import grails.converters.JSON
import org.joda.time.DateTime

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

    def events(Long id, Long date){
        def listEvents = []
        User user = RegisteredUser.findById(id)?.getUser()
        def actualMonth = DateTime.now().getMonthOfYear()
        date = date? (date+1) : actualMonth

        user?.getExpenses().each {
            if(actualMonth == date)
                listEvents.add(title: it.getTitle() + " - " + it.getCircle().getName(), start: it.getBeginDate(), end: it.getEndDate(),
                                url: createLink(controller: "expense", action: "show", id: it.getId()))
        }

        render listEvents as JSON
    }
}
