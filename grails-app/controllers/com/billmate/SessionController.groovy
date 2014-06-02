package com.billmate

import org.apache.shiro.crypto.hash.Sha256Hash

class SessionController extends BaseController {
    static layout = "session"
    static allowedMethods = [create: "GET", save: "POST", delete: "DELETE"]

    def beforeInterceptor = [action: this.&alreadyAuthenticated, except:['delete']]

    def create = {
    }

    def save = {
        if(!checkRequiredParams()){ return }

        def user = User.findWhere(email: params['email'])
        if (user) {
            def registeredUser = RegisteredUser.findWhere(user: user)

            if (registeredUser && registeredUser.password == new Sha256Hash(params['password']).toHex()) {
                session.user = registeredUser
                flash.message = "com.billmate.session.save.success"
                flash.m_default = "Signed in successfully."
                return redirect(controller: 'dashboard', action: 'index')
            }
        }

        flash.error = "com.billmate.session.save.failure"
        flash.e_default = "Invalid email or password."
        return redirect(controller: 'session', action: 'create')
    }

    def delete = {
        session.user = null
        return redirect(controller: 'session', action: 'create')
    }

    private checkRequiredParams() {
        if (!params['password'] || !params['email']) {
            flash.error = "com.billmate.session.save.invalid.params"
            flash.e_default = "Please fill out all fields below."
            redirect(controller: 'session', action: 'create')
            return false
        }
        return true
    }
}
