package com.billmate.api.v1

import com.billmate.AuthenticationToken
import com.billmate.Circle
import com.billmate.RegisteredUser
import grails.converters.JSON

class CircleController {

    static allowedMethods = [show: 'GET']
    static namespace = "v1"

    def show(){
        if (!checkToken() || !checkEmail() || !checkId()) {
            return
        }
        def response = []
        def token  = AuthenticationToken.findByTokenAndEmail(params.token,params.email)
        if(!token){
            response = [
                    'error':[
                            msg: message(code: "com.billmate.authenticationtoken.token.invalid")
                    ]
            ]
            render response as JSON
        }
        def user = RegisteredUser.findByEmail(token.email())
        def user_circles = user.getCircles()*.id
        def contains_circle = user_circles.contains(((String)params.id).toLong())
        if(!contains_circle){
            response = [
                    'error':[
                            msg: message(code: "com.billmate.session.forbidden")
                    ]
            ]
            render response as JSON
        }

        def circle = Circle.findById(params["id"])
        response = [
                id: circle.getId(),
                name: circle.getName()
        ]
        render response as JSON
    }

    private checkToken(){
        def response = []
        if(!params['token']){
            response = [
                    error: [
                            'msg': message(code: "com.billmate.authenticationtoken.token.null")
                    ]
            ]
            render response as JSON
        }
        return true
    }

    private checkId(){
        def response = []
        if(!((String)params.id).isLong()){
            response = [
                    error: [
                            'msg': message(code: "com.billmate.circle.id.invalid")
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

}
