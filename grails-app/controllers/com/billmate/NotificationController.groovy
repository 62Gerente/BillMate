package com.billmate

import grails.converters.JSON

class NotificationController extends RestrictedController {

    def beforeInterceptor = [action: this.&checkSession]

    def index() {}

    def read(Long id){
        boolean success = false
        SystemNotification notification = SystemNotification.findById(id)
        success = notification?.markReadNotification()

        def response = [
                'error'  : !success,
                'message': message(code: "com.billmate.notification.error_mark_as_read")
        ]
        render response as JSON
    }

    def readAll(){
        boolean success = false
        RegisteredUser registeredUser = RegisteredUser.findById(authenticatedUser().getId())
        success = registeredUser?.markAllReadNotification()
        def response = [
                'error'  : !success,
                'message': message(code: "com.billmate.notification.error_mark_all_as_read")
        ]
        render response as JSON
    }
}
