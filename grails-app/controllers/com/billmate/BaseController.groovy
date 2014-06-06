package com.billmate

abstract class BaseController {
    private alreadyAuthenticated() {
        if(session.user) {
            redirect(uri: '/')
            return false
        }
    }
}
