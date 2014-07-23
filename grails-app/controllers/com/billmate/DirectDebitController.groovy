package com.billmate

import grails.converters.JSON

class DirectDebitController extends RestrictedController {
    def beforeInterceptor = [action: this.&checkSession]

    def updateProperty(Long id) {
        def directDebit = DirectDebit.findById(id)
        def property = params.name
        def value = params.value

        def dates = ["debitDate"]

        if(dates.contains(property)){
            if(value){
                value = new Date().parse("dd-MM-yyyy HH:mm", value)
            }else{
                value = null
            }
        }

        directDebit.setProperty(property, value)

        def response = [
                'error'  : false,
                'message': message(code: "com.billmate.directDebit.updateProperty.success")
        ]

        if(!directDebit.save()) {
            response.error = true
            response.message = message(error: directDebit.getErrors().getAllErrors().first());
        }

        render response as JSON
    }
}
