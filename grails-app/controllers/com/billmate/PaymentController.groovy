package com.billmate

import grails.converters.JSON

class PaymentController extends RestrictedController {
    def beforeInterceptor = [action: this.&checkSession]

    def confirm() {
        def response = [
                'error'  : false,
                'message': message(code: "com.billmate.registeredUser.confirmPayments.success")
        ]

        if(params.list('payment[]').isEmpty()){
            response.error = true
            response.message = message(code: "com.billmate.registeredUser.confirmPayments.empty")
        }else if(!authenticatedUser().confirmPayments(params.list('payment[]'), session.user)){
            response.error = true
            response.message = message(code: "com.billmate.registeredUser.confirmPayments.error")
        }

        render response as JSON
    }

    def cancel() {
        def response = [
                'error'  : false,
                'message': message(code: "com.billmate.registeredUser.cancelPayments.success")
        ]

        if(params.list('payment[]').isEmpty()){
            response.error = true
            response.message = message(code: "com.billmate.registeredUser.cancelPayments.empty")
        }else if(!authenticatedUser().cancelPayments(params.list('payment[]'), session.user)){
            response.error = true
            response.message = message(code: "com.billmate.registeredUser.cancelPayments.error")
        }

        render response as JSON
    }
}
