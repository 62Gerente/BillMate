package com.billmate

abstract class BaseController {
    private alreadyAuthenticated() {
        if(session.user) {
            flash.error = "com.billmate.session.save.already.authenticated"
            flash.e_args = ["#"]
            flash.e_default = "You are already signed in. <a href='{0}'>Logout?</a>"
            redirect(controller: 'session', action: 'create')
            return false
        }
    }
}
