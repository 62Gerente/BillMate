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

    def makeAllRead(){
        List<SystemNotification> notification = SystemNotification.findAll()
        SystemNotification.withTransaction { status ->
            try{
                notification.each {it.setIsRead(true);it.secureSave();};
            }catch(Exception e){
                status.setRollbackOnly();
                notification = null;
            }
        }
        def response = ['notification':  notification]
        render response as JSON
    }
}
