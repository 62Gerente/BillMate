package com.billmate

import com.lucastex.grails.fileuploader.UFile
import grails.converters.JSON

class RegisteredUserController extends RestrictedController {
    static allowedMethods = [markNotificationsAsRead: "PUT", edit: "GET", history: "GET"]

    def beforeInterceptor = [action: this.&checkSession]
    def burningImageService

    def markNotificationsAsRead(Long id) {
        def success = RegisteredUser.findById(id).markNotificationsAsRead()

        def response = [
                'error'  : false,
                'message': message(code: "com.billmate.registeredUser.markNotificationsAsRead.success")
        ]

        if (!success) {
            response.error = true
            response.message = message(code: "com.billmate.registeredUser.markNotificationsAsRead.error")
        }

        render response as JSON
    }

    def edit(Long id) {
        if (id != authenticatedUser().getId()) {
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

        if (!registeredUser.save()) {
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
                'error'    : false,
                'message'  : message(code: "com.billmate.registeredUser.updatePhoto.success"),
                'photo_url': createLink(controller: "fileUploader", action: "show", id: ufile.getId())
        ]

        if (!registeredUser.save()) {
            response.error = true
            response.message = message(error: registeredUser.getErrors().getAllErrors().first());
        }

        render response as JSON
    }

    def squarePhoto(UFile uFile) {
        def path = uFile.getPath()

        burningImageService.doWith(path, path.substring(0, path.lastIndexOf("/"))).execute {
            it.scaleAccurate(150, 150)
        }

        def newFile = new File(path)
        uFile.setSize(newFile.size())
        uFile.save()
    }

    def history(Long id) {

        def registeredUser =  RegisteredUser.findById(id)
        def page = params.page && ((String) params.page).isInteger() ? Integer.parseInt(params.page) : 0
        def size = params.size && ((String) params.size).isInteger() ? Integer.parseInt(params.size) : 0
        def circleID = params.circle && ((String) params.circle).isLong() ? Long.parseLong(params.circle) : 0
        def typeID = params.type && ((String) params.type).isLong() ? Long.parseLong(params.type) : 0
        def userHistory = new RegisteredUserHistory(registeredUser: registeredUser, format: params.format, size: size, page: page, circleId: circleID, actionTypeId: typeID)

        def response = [
                error: false
        ]

        if(params.format){
            response.actions = new ArrayList<Object>()

            userHistory.getRealizedActions().each {
                def jsonMap = it.toJSON()
                def textArgs = [it.getActor(), it.getUser(), it.getCircle(), it.getExpense(), it.getRegularExpense(), it.getPayment()]

                jsonMap.date = g.render(template:"/shared/dateFormat", model:[time: it.getActionDate()])
                jsonMap.text = message(code: 'com.billmate.history.' + jsonMap.type, args: textArgs)

                if(it.getActionType().getType().toString().equals(ActionTypeEnum.signUp.toString())){
                    jsonMap.icon = assetPath([src: "/"]) + jsonMap.icon
                }
                response.actions.add(jsonMap)
            }

            if(response.actions.size() == 0){
                response.error = true
                response.message = message(code: 'com.billmate.history.search.empty')
            }

            render response as JSON
        }

        return [user: registeredUser, history: userHistory]
    }
}
