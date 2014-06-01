package com.billmate

abstract class RestrictedController extends BaseController {
    private checkSession() {
        if(!session.user) {
            flash.error = "com.billmate.session.required"
            flash.e_default = "​Please sign in to access this page."
            redirect(controller: 'session', action: 'create')
            return false
        }
    }

    private authenticatedUser(){
        RegisteredUser sessionUser = session.user
        return RegisteredUser.findWhere(id: sessionUser.getId())
    }
}
