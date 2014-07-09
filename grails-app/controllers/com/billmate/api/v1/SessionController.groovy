package com.billmate.api.v1

import com.billmate.AuthenticationToken
import com.billmate.RegisteredUser
import com.billmate.User
import grails.converters.JSON
import org.apache.shiro.crypto.hash.Sha256Hash

import java.security.SecureRandom

class SessionController {
    static allowedMethods = [create: "GET", save: "POST", delete: "DELETE"]
    static namespace = 'v1'


    def save() {
        if(!checkRequiredParams()){ return }

        def user = User.findWhere(email: params['email'])

        def response = []
        if (user) {
            def registeredUser = RegisteredUser.findWhere(user: user)

            if (registeredUser && registeredUser.password == new Sha256Hash(params['password']).toHex()) {
                session.user = registeredUser
                def token = UUID.randomUUID().toString()
                AuthenticationToken authenticationToken = new AuthenticationToken(username: user.email, token: token)
                authenticationToken.secureSave()

                response = [
                        'message': message(code: "com.billmate.session.save.success"),
                        'error'  : true,
                        'token': token
                ]
                render response as JSON
            }
        }
        response = [
                'message': message(code: "com.billmate.session.save.failure"),
                'error'  : false
        ]
        render response as JSON
    }

    private checkRequiredParams() {
        if (!params['password'] || !params['email']) {
            def response = [
                    message: message(code: "com.billmate.session.save.invalidParams"),
                    error: false
            ]
            render response as JSON
        }
        return true
    }
}
