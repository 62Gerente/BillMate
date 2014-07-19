package com.billmate.api.v1

import com.billmate.AuthenticationToken
import com.billmate.RegisteredUser
import com.billmate.User
import grails.converters.JSON

class RegisterController {

    static allowedMethods = [save: "POST"]
    static namespace = 'v1'
    def save = {
        if (!checkRequiredParams() || !checkEmail() || !checkPasswordSize() ||
                !checkPasswordConfirmation() || !checkEmailUniqueness()) {
            return
        }

        def registeredUser = new RegisteredUser(name: params['name'], email: params['email'], password: params['password'])
        def response = []
        if (registeredUser.secureSave()) {
            def token = UUID.randomUUID().toString()
            AuthenticationToken authenticationToken = new AuthenticationToken(email: registeredUser.email, token: token)
            authenticationToken.secureSave()
            response = [
                    'id'   : registeredUser.getId(),
                    'token': token,
                    'email': registeredUser.getEmail()
            ]
        } else {
            response = [
                    'error': [
                            'msg': message(code: "com.billmate.register.save.failure")
                    ]
            ]
        }
        render response as JSON
    }

    private checkRequiredParams() {
        if (!params['name'] || !params['email'] || !params['password'] || !params['c_password']) {


            def response = [
                    'error': [
                            'msg': message(code: "com.billmate.register.save.failure")
                    ]
            ]
            render response as JSON
        }
        return true
    }

    private checkPasswordConfirmation() {
        if (params['password'] != params['c_password']) {
            def response = [
                    'error': [
                            'msg': message(code: "com.billmate.register.save.passwordMatch")
                    ]
            ]
            render response as JSON
        }
        return true
    }

    private checkPasswordSize() {
        if (params['password'].toString().length() < 5) {
            def response = [
                    'error': [
                            'msg': message(code: "com.billmate.register.save.passwordShort")
                    ]
            ]
            render response as JSON
        }
        return true
    }

    private checkEmail() {
        def emailPattern = /[_A-Za-z0-9-]+(\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\.[A-Za-z0-9]+)*(\.[A-Za-z]{2,})/
        if (!params['email'] ==~ emailPattern) {
            def response = [
                    'error': [
                            'msg': message(code: "com.billmate.register.save.invalidEmail")
                    ]
            ]
            render response as JSON
        }
        return true
    }

    private checkEmailUniqueness() {
        def user = User.findWhere(email: params['email'])
        if (user) {
            def response = [
                    'error': [
                            'msg': message(code: "com.billmate.register.save.emailUniqueness")
                    ]
            ]
            render response as JSON
        }
        return true
    }
}
