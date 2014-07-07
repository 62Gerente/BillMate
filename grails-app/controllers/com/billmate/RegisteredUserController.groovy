package com.billmate

import com.lucastex.grails.fileuploader.UFile
import grails.converters.JSON
import grails.web.JSONBuilder
import groovy.json.*
import pl.burningice.plugins.image.BurningImageService

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
        if (id != authenticatedUser().getId()) {
            return withoutPermitions()
        }

        Long offsetResultNumber = params.size ? Long.parseLong(params.size) : 20
        Long offsetResult = Math.min(offsetResultNumber > 0 ? offsetResultNumber : 20, 50)

        Long pageNumber = params.page ? Long.parseLong(params.page) : null
        Long page = pageNumber > 0 ? pageNumber - 1 : 0

        def user = authenticatedUser()
        def registeredUser = RegisteredUser.findById(id)

        if (params.alt.equals("json")) {
            def response = [
                    'error': false
            ]

            def circle = null
            def type = null

            def circleID = Long.parseLong(params.circle)
            def typeID = Long.parseLong(params.type)

            try {
                def actions = null

                if(circleID < 0 && typeID < 0)
                    actions = registeredUser.getRealizedActions(page, offsetResult, offsetResult * page, "desc")
                else {

                    if (circleID > 0)
                        circle = Circle.findById(circleID)

                    if (typeID > 0)
                        type = ActionType.findById(typeID)

                    if (typeID > 0 && circleID > 0)
                        actions = registeredUser.getRealizedActionsByCircleAndType(circle, type, page, offsetResult, offsetResult * page, "desc")
                    else {
                        if (circleID < 0)
                            actions = registeredUser.getRealizedActionsByType(type, page, offsetResult, offsetResult * page, "desc")

                        if (typeID < 0)
                            actions = registeredUser.getRealizedActionsByCircle(circle, page, offsetResult, offsetResult * page, "desc")
                    }
                }

                // Check if list is empty and throws Exception
                if(actions.size() == 0)
                    throw new Exception("List is empty")

                // Get specified actions
                response.actions = new ArrayList<Object>()

                actions.each {
                    def jsonMap = it.getJsonMap()
                    def textArgs = [it.getActor(), it.getUser(), it.getCircle(), it.getExpense(), it.getRegularExpense(), it.getPayment()]

                    jsonMap.date = g.render(template:"/shared/dateFormat", model:[time: it.getActionDate()])
                    jsonMap.text = message(code: 'com.billmate.history.' + jsonMap.type, args: textArgs)

                    if(it.getActionType().getType().toString().equals(ActionTypeEnum.signUp.toString())){
                        jsonMap.icon = assetPath([src: "/"]) + jsonMap.icon
                    }
                    response.actions.add(jsonMap)
                }

            } catch (Exception eActionFilter) {
                response.error = true
                response.message = message(code: 'com.billmate.history.search.empty')

                eActionFilter.printStackTrace()
            }

            switch (params.alt) {
                default:
                    render response as JSON
            }
            return
        }

        def actions = registeredUser.getRealizedActions(page, offsetResult, offsetResult * page, "desc")

        return [
                user          : user,
                registeredUser: registeredUser,
                actions       : actions,
                actionsType   : ActionType.getAll(),
                houses        : registeredUser.getHouses(),
                collectives   : registeredUser.getCollectives(),
                display       : actions.size() < offsetResult ? "none" : ""
        ]
    }
}
