package com.billmate

import grails.converters.JSON

class CircleController extends RestrictedController {

    def history(Long id) {

        def circle = Circle.findById(id)

        def breadcrumb = [
                [href: createLink(controller: "dashboard", action: "circle", id: id), name: circle.getName()],
                [name: message(code: "com.billmate.sidebar.history")]
        ]

        def page = params.page && ((String) params.page).isInteger() ? Integer.parseInt(params.page) : 0
        def size = params.size && ((String) params.size).isInteger() ? Integer.parseInt(params.size) : 0
        def userID = params.user && ((String) params.user).isLong() ? Long.parseLong(params.user) : 0
        def typeID = params.type && ((String) params.type).isLong() ? Long.parseLong(params.type) : 0
        def circleHistory = new CircleHistory(circle: circle, format: params.alt, size: size, page: page, userId: userID, actionTypeId: typeID)

        if (params.alt) {
            def response = [
                    error: false
            ]

            response.actions = new ArrayList<Object>()

            circleHistory.getRealizedActions().each {
                def jsonMap = it.toJSON()
                def textArgs = [it.getActor(), it.getUser(), it.getCircle(), it.getExpense(), it.getRegularExpense(), it.getPayment()]

                jsonMap.date = g.render(template: "/shared/dateFormat", model: [time: it.getActionDate()])
                jsonMap.text = message(code: 'com.billmate.action.' + jsonMap.type + '.long', args: textArgs)
                if (jsonMap.expenseTitle) {
                    jsonMap.expenseText = message(code: 'com.billmate.expense.action.total')
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

        return [breadcrumb: breadcrumb, user: session.user, circle: circle, history: circleHistory]
    }
}
