package com.billmate

import grails.converters.JSON

abstract class BaseController {
    private alreadyAuthenticated() {
        if(session.user) {
            redirect(uri: '/')
            return false
        }
    }

    public ajaxMakeReadNotification(){
        int id = params.id
        SystemNotification notification = SystemNotification.ajaxMakeReadNotification(id)
        def response = ['notification': notification?.getIsRead()]
        render response as JSON
    }
}
