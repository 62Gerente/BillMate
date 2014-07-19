package com.billmate.api.v1

import com.billmate.AuthenticationToken
import com.billmate.RegisteredUser
import com.billmate.User
import grails.converters.JSON
import org.apache.shiro.crypto.hash.Sha256Hash

class SessionController {
    static allowedMethods = [create: "GET", save: "POST", delete: "DELETE"]
    static namespace = 'v1'


    def save() {
        if (!checkRequiredParams()) {
            return
        }

        def user = User.findWhere(email: params.email)

        def response = []
        if (user) {
            def registeredUser = RegisteredUser.findWhere(user: user)

            if (registeredUser && registeredUser.password == new Sha256Hash(params.password).toHex()) {
                def token = UUID.randomUUID().toString()
                AuthenticationToken authenticationToken = new AuthenticationToken(email: user.email, token: token)
                authenticationToken.secureSave()

                response = [
                        'id'   : registeredUser.getId(),
                        'token': token,
                        'email': registeredUser.getEmail()
                ]
                render response as JSON
            }
        }
        response = [
                'error': [
                        'msg': message(code: "com.billmate.session.save.failure")
                ]
        ]
        render response as JSON
    }

    private checkRequiredParams() {
        if (!params.password || !params.email) {
            def response = [
                    'error': [
                            'msg': message(code: "com.billmate.session.save.invalidParams")
                    ]
            ]
            render response as JSON
        }
        return true
    }
}
