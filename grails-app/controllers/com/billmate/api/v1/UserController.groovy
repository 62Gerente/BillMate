package com.billmate.api.v1

import com.billmate.AuthenticationToken
import com.billmate.RegisteredUser
import grails.converters.JSON


class UserController {

    static allowedMethods = [circles: 'GET']
    static namespace = "v1"

    def circles(){

        if (!checkToken() || !checkId() || !checkEmail()) {
            return
        }

        def response = []
        def atoken = AuthenticationToken.findByTokenAndEmail(params.token,params.email)
        if(!atoken){
            response = [
                    'error':[
                            msg: message(code: "com.billmate.authenticationtoken.token.invalid")
                    ]
            ]
            render response as JSON
        }
        def user = RegisteredUser.findByEmail(params.email)
        if(!user){
            response = [
                    'error':[
                            'msg': message(code: "com.billmate.user.email.null")
                    ]
            ]
            render response as JSON
        }

        def id = RegisteredUser.findById(params.id)
        if(!id || id.getId() != user.getId()){
            response = [
                    'error':[
                            'msg': message(code: "com.billmate.session.forbidden")
                    ]
            ]
            render response as JSON
        }

        user.getCircles().each {
            response.add(it.toJSON())
        }
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
        if(!params['id']){
            response = [
                    error: [
                            'msg': message(code: "com.billmate.User.id.nullable")
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
