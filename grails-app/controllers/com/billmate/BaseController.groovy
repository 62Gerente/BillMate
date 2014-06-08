package com.billmate

import grails.converters.JSON

abstract class BaseController {
    private alreadyAuthenticated() {
        if(session.user) {
            redirect(uri: '/')
            return false
        }
    }
}
