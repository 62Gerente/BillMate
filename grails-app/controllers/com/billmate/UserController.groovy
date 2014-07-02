package com.billmate

import grails.converters.JSON

class UserController extends RestrictedController {
    static allowedMethods = [updateField: "POST", history: "GET"]

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
}
