package com.billmate

import org.apache.shiro.crypto.hash.Sha256Hash

class AuthenticationController extends BaseController {
    static layout = "authentication"
    def beforeIntercept = [action: this.&checkUser, except: ['login']]

    def login() {
        if(session.user) {
            flash.message = message(code: "com.billmate.authentication.sigin.already.authenticated", default: "You are already signed in.")
        }
    }

    def doLogin = {
        def user = User.findWhere(email: params['email'])
        if (user) {
            def registeredUser = RegisteredUser.findWhere(user: user)

            if (registeredUser && registeredUser.password == new Sha256Hash(params['password']).toHex()) {
                session.user = registeredUser
                flash.message = message(code: "com.billmate.authentication.sigin.success", default: "Signed in successfully.")
                redirect(uri: '/')
                return
            }
        }

        flash.error = message(code: "com.billmate.authentication.sigin.failure", default: "Invalid email or password.")
        redirect(controller: 'authentication', action: 'login')
    }

    def checkUser() {
        if(session.user) {
            flash.error = message(code: "com.billmate.authentication.sigin.already.authenticated", default: "You are already signed in.")
            redirect(controller: 'user', action: 'login')
        }
    }
}
