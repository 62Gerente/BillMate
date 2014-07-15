package com.billmate

import grails.converters.JSON

class DebtController extends RestrictedController {
    def beforeInterceptor = [action: this.&checkSession]

    def updateProperty(Long id) {
        def debt = Debt.findById(id)
        def property = params.name
        def value = params.value

        def doubles = ["value"]

        if(doubles.contains(property)) {
            if (value) {
                value = Double.parseDouble(value)
            } else {
                value = null
            }
        }

        debt.setProperty(property, value)

        def response = [
                'error'  : false,
                'message': message(code: "com.billmate.debt.updateProperty.success")
        ]

        if(!debt.save()) {
            response.error = true
            response.message = message(error: debt.getErrors().getAllErrors().first());
        }

        render response as JSON
    }
}
