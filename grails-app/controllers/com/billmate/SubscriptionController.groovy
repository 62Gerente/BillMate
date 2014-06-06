package com.billmate

import grails.converters.JSON

class SubscriptionController {
    static allowedMethods = [save: "POST"]

    def save() {
        println params['email']
        def responseData = [
                'error'  : false,
                'message': message(code: "com.billmate.Subscription.success")
        ]

        Subscription subscription = new Subscription(email: params['email'])
        if (subscription.secureSave() == false) {
            responseData.error = true;
            responseData.message = message(error: subscription.errors.getFieldError("email"));
        }

        render responseData as JSON
    }
}
