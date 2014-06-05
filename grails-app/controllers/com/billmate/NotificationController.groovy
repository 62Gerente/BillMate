package com.billmate

import grails.converters.JSON

class NotificationController {

    def index() {}

    def makeRead(Long id){
        SystemNotification notification = SystemNotification.findById(id)
        if(notification) {
            notification.setIsRead(true)
            notification.secureSave()
        }
        def response = ['notification': notification?.getIsRead()]
        render response as JSON
    }
}
