package com.billmate

import grails.converters.JSON
import org.apache.shiro.crypto.hash.Sha256Hash

class RegisterController extends BaseController {
    static layout = "session"
    static allowedMethods = [create: "GET", save: "POST"]

    def beforeInterceptor = [action: this.&alreadyAuthenticated]

    def create = {
    }

    def save = {
        if (!checkRequiredParams() || !checkEmail() || !checkPasswordSize() ||
            !checkPasswordConfirmation() || !checkEmailUniqueness()) { return }

        def registeredUser = new RegisteredUser(name: params['name'], email: params['email'], password: params['password'])
        if(registeredUser.secureSave()){
            session.user = registeredUser
            flash.message = "com.billmate.register.save.success"
            flash.m_default = "You have been successfully registered and logged in."
            return redirect(controller: 'dashboard', action: 'user')
        }else{
            flash.error = "com.billmate.register.save.failure"
            flash.e_default = "Error creating your account, please try again."
            return redirect(controller: 'register', action: 'create')
        }
    }

    private checkRequiredParams() {
        if (!params['name'] || !params['email'] || !params['password'] || !params['c_password']) {
            flash.error = "com.billmate.register.save.invalidParams"
            flash.e_default = "Please fill out all fields below."
            redirect(controller: 'register', action: 'create')
            return false
        }
        return true
    }

    private checkPasswordConfirmation() {
        if (params['password'] != params['c_password']) {
            flash.error = "com.billmate.register.save.passwordMatch"
            flash.e_default = "Given passwords do not match."
            redirect(controller: 'register', action: 'create')
            return false
        }
        return true
    }

    private checkPasswordSize() {
        if (params['password'].toString().length() < 5) {
            flash.error = "com.billmate.register.save.passwordShort"
            flash.e_default = "The password is too short, minimum length is 5 characters."
            redirect(controller: 'register', action: 'create')
            return false
        }
        return true
    }

    private checkEmail() {
        def emailPattern = /[_A-Za-z0-9-]+(\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\.[A-Za-z0-9]+)*(\.[A-Za-z]{2,})/
        if (!params['email'] ==~ emailPattern) {
            flash.error = "com.billmate.register.save.invalidEmail"
            flash.e_default = "Invalid email address."
            redirect(controller: 'register', action: 'create')
            return false
        }
        return true
    }

    private checkEmailUniqueness() {
        def user = User.findWhere(email: params['email'])
        if (user) {
            flash.error = "com.billmate.register.save.emailUniqueness"
            flash.e_default = "Email address already in use."
            redirect(controller: 'register', action: 'create')
            return false
        }
        return true
    }
}
