package com.billmate

import com.lucastex.grails.fileuploader.UFile
import grails.converters.JSON
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

        def breadcrumb = [
                [name: message(code: "com.billmate.user.profile")]
        ]
        def registeredUser = RegisteredUser.findById(id)
        def circles = registeredUser.getCircles()

        return [breadcrumb: breadcrumb, user: authenticatedUser(), registeredUser: registeredUser, circles: circles, active: 0]
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

        def breadcrumb = [
                [name: message(code: "com.billmate.sidebar.history")]
        ]
        def registeredUser = RegisteredUser.findById(id)
        def page = params.page && ((String) params.page).isInteger() ? Integer.parseInt(params.page) : 0
        def size = params.size && ((String) params.size).isInteger() ? Integer.parseInt(params.size) : 0
        def circleID = params.circle && ((String) params.circle).isLong() ? Long.parseLong(params.circle) : 0
        def typeID = params.type && ((String) params.type).isLong() ? Long.parseLong(params.type) : 0
        def userHistory = new RegisteredUserHistory(registeredUser: registeredUser, format: params.alt, size: size, page: page, circleId: circleID, actionTypeId: typeID)

        if (params.alt) {
            def response = [
                    error: false
            ]

            response.actions = new ArrayList<Object>()

            userHistory.getRealizedActions().each {
                def jsonMap = it.toJSON()
                def textArgs = [it.getActor(), it.getUser(), it.getCircle(), it.getExpense(), it.getRegularExpense(), it.getPayment()]

                jsonMap.date = g.render(template: "/shared/dateFormat", model: [time: it.getActionDate()])
                jsonMap.text = message(code: 'com.billmate.history.' + jsonMap.type, args: textArgs)
                if (jsonMap.expenseTitle) {
                    jsonMap.expenseText = message(code: 'com.billmate.expense.action.total')
                }

                if (it.getActionType().getType().toString().equals(ActionTypeEnum.signUp.toString())) {
                    jsonMap.icon = assetPath([src: "/"]) + jsonMap.icon
                }
                response.actions.add(jsonMap)
            }

            if (response.actions.size() == 0) {
                response.error = true
                response.message = message(code: 'com.billmate.history.search.empty')
            }

            render response as JSON
            return
        }

        return [breadcrumb: breadcrumb, user: registeredUser, history: userHistory, active: 3]
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

    def unresolvedExpensesUser(Long idUser, Long idRegisteredUser){
        RegisteredUser registeredUser = RegisteredUser.findById(idRegisteredUser)
        def list = []

        registeredUser.unresolvedResponsibleExpensesBy(idUser).each {
            Circle circle = it.getCircle()
            if(it.amountInDebtOf(idUser) != 0)
                list.add([
                        it.getTitle(), circle.getName(), it.amountPaidBy(idUser), it.debtOf(idUser).getValue(), it.getExpenseType().getCssClass(), circle.getCssClass(), it.getId()
                ])
        }

        def response = [data: list]

        render response as JSON
    }

    def unresolvedExpensesToMe(Long idUser, Long idRegisteredUser){
        def list = []
        User user = User.findById(idUser)
        user.unresolvedExpensesWhichResponsibleIs(idRegisteredUser).each {
            Circle circle = it.getCircle()
            if(it.amountInDebtOf(idUser) != 0)
                list.add([
                        it.getTitle(), circle.getName(), it.amountPaidBy(idUser), it.debtOf(idUser).getValue(), it.getExpenseType().getCssClass(), circle.getCssClass(), it.getId()
                ])
        }

        def response = [data: list]

        render response as JSON
    }

    def reports(Long id){
        if (id != authenticatedUser().getId()) {
            return withoutPermitions()
        }

        def breadcrumb = [
                [name: message(code: "com.billmate.sidebar.report")]
        ]

        def registeredUser = RegisteredUser.findById(id)
        def userReports = new RegisteredUserReports(registeredUser: registeredUser)

        return [breadcrumb: breadcrumb, user: registeredUser, userReports: userReports, active: 4]
    }
}
