package com.billmate

import com.lucastex.grails.fileuploader.UFile
import grails.converters.JSON

class RegisteredUserController extends RestrictedController {
    static allowedMethods = [markNotificationsAsRead: "PUT", edit: "GET"]

    def beforeInterceptor = [action: this.&checkSession]

    def markNotificationsAsRead(Long id) {
        def success = RegisteredUser.findById(id).markNotificationsAsRead()

        def response = [
                'error'  : false,
                'message': message(code: "com.billmate.registeredUser.markNotificationsAsRead.success")
        ]

        if(!success) {
            response.error = true
            response.message = message(code: "com.billmate.registeredUser.markNotificationsAsRead.error")
        }

        render response as JSON
    }

    def edit(Long id) {
        def registeredUser = RegisteredUser.findById(id)

        return [user: authenticatedUser(), circles: authenticatedUser().getCircles()]
    }

    def updateProperty(Long id) {
        def registeredUser = RegisteredUser.findById(id)
        def property = params.name
        def value = params.value

        registeredUser.setProperty(property, value)

        def response = [
                'error'  : false,
                'message': message(code: "com.billmate.registeredUser.updateProperty.success")
        ]

        if(!registeredUser.save()) {
            response.error = true
            response.message = message(error: registeredUser.getErrors().getAllErrors().first());
        }

        render response as JSON
    }

    def errorUploadPhoto() {
        def registeredUser = RegisteredUser.findById(params.id)
        def error = flash.message

        def response = [
                'error'  : true,
                'message': error
        ]

        render response as JSON
    }

    def successUploadPhoto() {
        def registeredUser = RegisteredUser.findById(params.id)
        def ufile = UFile.findById(params.ufileId)

        registeredUser.setPhoto(ufile)

        def response = [
                'error'  : false,
                'message': message(code: "com.billmate.registeredUser.updatePhoto.success"),
                'photo_url': createLink(controller: "fileUploader", action: "show", id: ufile.getId())
        ]

        if(!registeredUser.save()) {
            response.error = true
            response.message = message(error: registeredUser.getErrors().getAllErrors().first());
        }

        render response as JSON
    }
}
