package com.billmate

import grails.converters.JSON

class NotificationController {

    def index() {}

    def read(Long id){
        SystemNotification notification = SystemNotification.findById(id)
        if(notification) {
            notification.setIsRead(true)
            notification.secureSave()
        }

        def response = ['notification': notification?.getIsRead()]
        render response as JSON
    }

    def readAll(){
        List<SystemNotification> notification = SystemNotification.findAll()
        SystemNotification.withTransaction { status ->
            try{
                notification.each {it.setIsRead(true);it.secureSave();};
            }catch(Exception e){
                status.setRollbackOnly();
                notification = null;
            }
        }
        def response = ['notification':  !notification.isEmpty()]
        render response as JSON
    }

    def getAllNotifications(int numberActualNotifications){
        int numberNotifications = SystemNotification.countByIsRead(false)
        List<Notification> notificationList = null
        if(numberActualNotifications != numberNotifications){
            notificationList = SystemNotification.findAllByIsRead(false)
        }

        def response = ['notification': notificationList]
        render response as JSON
    }
}
