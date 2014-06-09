package com.billmate

import grails.converters.JSON

class SystemNotificationController extends RestrictedController {
    static allowedMethods = [markAsRead: "PUT"]

    def beforeInterceptor = [action: this.&checkSession]

    def markAsRead(Long id){
        def success = SystemNotification.findById(id).markAsRead()

        def response = [
                'error'  : false,
                'message': message(code: "com.billmate.systemNotification.markAsRead.success")
        ]

        if(!success) {
            response.error = true
            response.message = message(code: "com.billmate.systemNotification.markAsRead.error")
        }

        render response as JSON
    }
}
