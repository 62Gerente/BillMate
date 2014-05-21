package com.billmate

import org.apache.shiro.crypto.hash.Sha256Hash

class AuthenticationController extends BaseController {
    static layout = "authentication"

    def beforeInterceptor = [action: this.&alreadyAuthenticated, except: ['login']]

    def login() {
        if(session.user) {
            flash.message = "com.billmate.authentication.signin.already.authenticated"
            flash.m_args = ["#"]
            flash.m_default = "You are already signed in. <a href='{0}'>Logout?</a>"
        }
    }

    def doLogin = {
        def user = User.findWhere(email: params['email'])
        if (user) {
            def registeredUser = RegisteredUser.findWhere(user: user)

            if (registeredUser && registeredUser.password == new Sha256Hash(params['password']).toHex()) {
                session.user = registeredUser
                flash.message = "com.billmate.authentication.signin.success"
                flash.m_default = "Signed in successfully."
                return redirect(uri: '/')
            }
        }

        flash.error = "com.billmate.authentication.signin.failure"
        flash.e_default = "Invalid email or password."
        return redirect(controller: 'authentication', action: 'login')
    }
}
