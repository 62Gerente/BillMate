package com.billmate

import grails.converters.JSON

class CircleController extends RestrictedController  {

    static allowedMethods = [circles: "POST", assignedUsers: "POST", show: "POST", history: "GET"]

    def beforeInterceptor = [action: this.&checkSession]

    def circles(){
        Set<Object> circles = new HashSet<>()
        long id = Long.parseLong(params.id)
        String params = params.q
        Set<Circle> circleSet = RegisteredUser.findById(id).getCircles()

        circleSet.each { if(it.getName().toUpperCase().contains(params.toUpperCase())){
            circles.add([id: it.getId(), icon: it.getCssClass(), name: it.getName()])
        }}

        def response = [ 'data': circles ]

        render response as JSON
    }

    def assignedUsers(){
        Set<Object> circleFriends = new HashSet<>()
        Circle.findById(Long.parseLong(params.id_circle)).getUsers().each {
            circleFriends.add([id: it.getId(), photo: it.getPhotoOrDefault(), name: it.getName(), value: 0, selectable: true, absolute: false, percentage: false])
        }
        def response = [ 'data': circleFriends ]

        render response as JSON
    }

    def show(){
        Long id = Long.parseLong(params.id)
        Circle circle = Circle.findById(id)

        def response = [ 'data'   : [circleID: circle.getId(), circleIcon: circle.getCssClass(), circleName: circle.getName()] ]

        render response as JSON
    }

    def expenses(Long idCircle, Long idUser){
        def list = []
        Circle circle = Circle.findById(idCircle)
        User user = User.findById(RegisteredUser.findById(idUser).getUserId())
        Expense.findAllByCircle(circle).each {
            def debtUser = it.debtOf(user.getId())
            if(debtUser){
                list.add([it.getTitle(), it.getResponsible().getName(), it.amountPaidBy(user.getId()), it.getValue(),
                          it.getInvoice()?.getPath(), it.getReceipt()?.getPath(), it.isResolved(), it.getId(),
                          it.getExpenseType().getCssClass(), it.getResponsible().getPhotoOrDefault(), it.getCircle().getCssClass(),
                          debtUser.getValue(), it.amountPaid()
                ]);
            }
        }

        def response = ['data': list]

        render response as JSON
    }

    def Set<ExpenseType> expenseTypes(Long idCircle){
        String params = params.q
        Set<ExpenseType> expenseTypes = new HashSet<ExpenseType>()
        Circle.findById(idCircle).getExpenseTypes().each { if(it.getName().toUpperCase().contains(params.toUpperCase())) expenseTypes.add(it) }
        def response = [ 'data': expenseTypes ]

        render response as JSON
    }

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
