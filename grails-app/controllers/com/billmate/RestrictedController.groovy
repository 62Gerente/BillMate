package com.billmate

abstract class RestrictedController extends BaseController {
    private checkSession() {

        // TESTING
        // session.user = RegisteredUser.findWhere(user: User.findWhere(email: 'andreccdr@gmail.com'))

        if(!session.user) {
            flash.error = "com.billmate.session.required"
            flash.e_default = "​Please sign in to access this page."
            redirect(controller: 'session', action: 'create')
            return false
        }
    }

    private withoutPermitions(){
        flash.error = "com.billmate.session.permissions"
        flash.e_default = "​You don't have permissions to access this page."
        return redirect(controller: 'session', action: 'create')
    }

    private authenticatedUser(){
        return RegisteredUser.findWhere(id: session.user)
    }
}
