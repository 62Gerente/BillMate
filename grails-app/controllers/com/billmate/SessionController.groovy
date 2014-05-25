package com.billmate

import org.apache.shiro.crypto.hash.Sha256Hash

class SessionController extends BaseController {
    static layout = "session"

    def beforeInterceptor = [action: this.&alreadyAuthenticated, except: ['login']]

    def create = {
        if(session.user) {
            flash.message = "com.billmate.session.save.already.authenticated"
            flash.m_args = ["#"]
            flash.m_default = "You are already signed in. <a href='{0}'>Logout?</a>"
        }
    }

    def save = {
        def user = User.findWhere(email: params['email'])
        if (user) {
            def registeredUser = RegisteredUser.findWhere(user: user)

            if (registeredUser && registeredUser.password == new Sha256Hash(params['password']).toHex()) {
                session.user = registeredUser
                flash.message = "com.billmate.session.save.success"
                flash.m_default = "Signed in successfully."
                return redirect(uri: '/')
            }
        }

        flash.error = "com.billmate.session.save.failure"
        flash.e_default = "Invalid email or password."
        return redirect(controller: 'session', action: 'create')
    }
}
