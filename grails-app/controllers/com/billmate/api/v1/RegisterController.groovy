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
                !checkPasswordConfirmation() || !checkEmailUniqueness()) { return }

        def registeredUser = new RegisteredUser(name: params['name'], email: params['email'], password: params['password'])
        def response = []
        if(registeredUser.secureSave()){
            session.user = registeredUser
            def token = UUID.randomUUID().toString()
            AuthenticationToken authenticationToken = new AuthenticationToken(username: registeredUser.email, token: token)
            authenticationToken.secureSave()
            response = [
                    message: message(code: "com.billmate.register.save.success"),
                    error: true,
                    token: token
            ]
        }else{
            response = [
                    message: message(code: "com.billmate.register.save.failure"),
                    error: false
            ]
        }
        render response as JSON
    }

    private checkRequiredParams() {
        if (!params['name'] || !params['email'] || !params['password'] || !params['c_password']) {
            def response = [
                    message: message(code: "com.billmate.register.save.invalidParams"),
                    error: false
            ]
            render response as JSON
        }
        return true
    }

    private checkPasswordConfirmation() {
        if (params['password'] != params['c_password']) {
            def response = [
                    message: message(code: "com.billmate.register.save.passwordMatch"),
                    error: false
            ]
            render response as JSON
        }
        return true
    }

    private checkPasswordSize() {
        if (params['password'].toString().length() < 5) {
            def response = [
                    message: message(code: "com.billmate.register.save.passwordShort"),
                    error: false
            ]
            render response as JSON
        }
        return true
    }

    private checkEmail() {
        def emailPattern = /[_A-Za-z0-9-]+(\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\.[A-Za-z0-9]+)*(\.[A-Za-z]{2,})/
        if (!params['email'] ==~ emailPattern) {
            def response = [
                    message: message(code: "com.billmate.register.save.invalidEmail"),
                    error: false
            ]
            render response as JSON
        }
        return true
    }

    private checkEmailUniqueness() {
        def user = User.findWhere(email: params['email'])
        if (user) {
            def response = [
                    message: message(code: "com.billmate.register.save.emailUniqueness"),
                    error: false
            ]
            render response as JSON
        }
        return true
    }
}
