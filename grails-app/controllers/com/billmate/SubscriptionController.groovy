package com.billmate

import grails.converters.JSON

class SubscriptionController {
    static allowedMethods = [save: "POST"]

    def notifierService

    def save() {
        def responseData = [
                'error'  : false,
                'message': message(code: "com.billmate.Subscription.success")
        ]

        Subscription subscription = new Subscription(email: params.email)
        if (!subscription.save()) {
            responseData.error = true;
            responseData.message = message(error: subscription.errors.getFieldError("email"));
        } else {
            notifierService.send(
                    params.email,
                    message(code: "com.billmate.Subscription.notify.subject"),
                    message(code: "com.billmate.Subscription.notify.body")
            )
        }

        render responseData as JSON
    }
}
