package com.billmate

import org.apache.shiro.crypto.hash.Sha256Hash

class OauthController {
    static layout = "session"
    static allowedMethods = [authorize: "GET", save: "POST"]

    def authorize() {
        def redirect_path = params['redirect']
        return [redirect_path: redirect_path]
    }


    def save = {
        if (!checkRequiredParams()) {
            return
        }

        def user = User.findWhere(email: params.email)
        if (user) {
            def registeredUser = RegisteredUser.findWhere(user: user)

            if (registeredUser && registeredUser.password == new Sha256Hash(params.password).toHex()) {
                flash.message = "com.billmate.session.save.success"
                flash.m_default = "Signed in successfully."

                def token = UUID.randomUUID().toString()

                AuthenticationToken authenticationToken = new AuthenticationToken(email: user.email, token: token)
                authenticationToken.secureSave()
                def response = [
                        token: token,
                        id: registeredUser.getId(),
                        email: registeredUser.getEmail()
                ]

                return redirect(uri: params.redirect+"?token="+response.token+"&user_id="+response.id+"&email="+response.email)
            }
        }

        flash.error = "com.billmate.session.save.failure"
        flash.e_default = "Invalid email or password."
        return redirect(controller: 'oauth', action: 'authorize')

    }

    private checkRequiredParams() {
        if (!params['password'] || !params['email']) {
            flash.error = "com.billmate.session.save.invalidParams"
            flash.e_default = "Please fill out all fields below."
            redirect(controller: 'session', action: 'create')
            return false
        }
        return true
    }
}
