package com.billmate

import grails.converters.JSON

class PaymentController extends RestrictedController {
    def beforeInterceptor = [action: this.&checkSession]

    def confirm() {
        def response = [
                'error'  : false,
                'message': message(code: "com.billmate.registeredUser.confirmPayments.success")
        ]

        if(params.list('payment[]').empty()){
            response.error = true
            response.message = message(code: "com.billmate.registeredUser.confirmPayments.empty")
        }else(!authenticatedUser().confirmPayments(params.list('payment[]'))){
            response.error = true
            response.message = message(code: "com.billmate.registeredUser.confirmPayments.error")
        }

        render response as JSON
    }
}
