package com.billmate

import grails.converters.JSON

class RegisteredUserController extends RestrictedController {
    static allowedMethods = [markNotificationsAsRead: "PUT"]

    def beforeInterceptor = [action: this.&checkSession]

    def markNotificationsAsRead(Long id) {
        def success = RegisteredUser.findById(id).markNotificationsAsRead()

        def response = [
                'error'  : false,
                'message': message(code: "com.billmate.registeredUser.markNotificationsAsRead.success")
        ]

        if(!success) {
            response.error = true
            response.message = message(code: "com.billmate.registeredUser.markNotificationsAsRead.error")
        }

        render response as JSON
    }

    def edit(Long id) {
        def registeredUser = RegisteredUser.findById(id)

        return [user: authenticatedUser(), circles: authenticatedUser().getCircles()]
    }
}
