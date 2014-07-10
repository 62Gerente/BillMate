package com.billmate

import com.lucastex.grails.fileuploader.UFile
import grails.converters.JSON
import pl.burningice.plugins.image.BurningImageService

class RegisteredUserController extends RestrictedController {
    static allowedMethods = [markNotificationsAsRead: "PUT", edit: "GET", circles: "POST"]

    def beforeInterceptor = [action: this.&checkSession]
    def burningImageService

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
        if(id != authenticatedUser().getId()) {
            return withoutPermitions()
        }

        def registeredUser = RegisteredUser.findById(id)
        def circles = registeredUser.getCircles()

        return [user: authenticatedUser(), registeredUser: registeredUser, circles: circles]
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

        squarePhoto(ufile)
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

    def squarePhoto(UFile uFile) {
        def path = uFile.getPath()

        burningImageService.doWith(path, path.substring(0, path.lastIndexOf("/"))).execute{
            it.scaleAccurate(150, 150)
        }

        def newFile = new File(path)
        uFile.setSize(newFile.size())
        uFile.save()
    }

    def circles(Long id){
        Set<Object> circles = new HashSet<>()
        String params = params.q
        Set<Circle> circleSet = RegisteredUser.findById(id).getCircles()

        circleSet.each { if(it.getName().toUpperCase().contains(params.toUpperCase())){
            circles.add([id: it.getId(), icon: it.getCssClass(), name: it.getName()])
        }}

        def response = [ 'data': circles ]

        render response as JSON
    }
}
