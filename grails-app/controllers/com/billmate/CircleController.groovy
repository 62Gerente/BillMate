package com.billmate

import grails.converters.JSON

class CircleController extends RestrictedController  {

    static allowedMethods = [circles: "POST", assignedUsers: "POST", show: "POST", history: "GET", listBy: "POST"]

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
                          it.getInvoice()?.getId(), it.getReceipt()?.getId(), it.isResolved(), it.getId(),
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

        return [breadcrumb: breadcrumb, user: authenticatedUser(), circle: circle, history: circleHistory, active: 0]
    }

    def edit(Long id) {
        def circle = Circle.findById(id)

        def breadcrumb = [
                [href: createLink(controller: "dashboard", action: "circle", id: id), name: circle.getName()],
                [name: message(code: "com.billmate.circle.edit")]
        ]

        return [breadcrumb: breadcrumb, user: authenticatedUser(), circle: circle, active: 0]
    }

    def updateProperty(Long id) {
        def circle = Circle.findById(id)
        def property = params.name
        def value = params.value

        circle.setProperty(property, value)

        def response = [
                'error'  : false,
                'message': message(code: "com.billmate.circle.updateProperty.success")
        ]

        if(!circle.save()) {
            response.error = true
            response.message = message(error: circle.getErrors().getAllErrors().first());
        }

        render response as JSON
    }

    def listUsersBy(Long id){
        Set<User> userSet = Circle.getFriendsOfAllCirclesByTermFormated(params.q.toUpperCase(), id)
        def response = [ 'data': userSet ]
        render response as JSON
    }

    def listExpensetypeBy(Long id){
        Set<ExpenseType> typeSet = ExpenseType.getFromAllCirclesByTermFormated(params.q.toUpperCase(),id)
        def response = [ 'data': typeSet ]
        render response as JSON
    }

    def adduser(Long id, Long id_circle){
        User user = User.findById(id)
        def response = [
                error: true,
                message: message(code: "com.billmate.circle.user.add.insuccess")
        ]
        if(user){
            User.findById(id).addToCircles(Circle.findById(id_circle)).save()

            response.error = false
            response.message = message(code: "com.billmate.circle.user.add.success")
        }

        render response as JSON
    }

    def deleteUser(Long id, Long id_circle){
        User user = User.findById(id)
        def response = [
                error: true,
                message: message(code: "com.billmate.circle.user.delete.insuccess")
        ]
        if(user && !user.hasMovementsInCircle(id_circle)){
            user.removeFromCircles(Circle.findById(id_circle))

            response.error = false
            response.message = message(code: "com.billmate.circle.user.delete.success")
        }

        render response as JSON
    }

    def addExpensetype(Long id, Long id_circle){
        ExpenseType expenseType = ExpenseType.findById(id)
        def response = [
                error: true,
                message: message(code: "com.billmate.circle.expensetype.add.insuccess")
        ]
        if(expenseType){
            expenseType.addToCircles(Circle.findById(id_circle)).save()

            response.error = false
            response.message = message(code: "com.billmate.circle.expensetype.add.success")
        }

        render response as JSON
    }

    def deleteExpensetype(Long id, Long id_circle){
        ExpenseType expenseType = ExpenseType.findById(id)
        Circle circle = Circle.findById(id_circle)
        def response = [
                error: true,
                message: message(code: "com.billmate.circle.expensetype.delete.insuccess")
        ]
        if(expenseType){
            expenseType.removeFromCircles()

            response.error = false
            response.message = message(code: "com.billmate.circle.expensetype.delete.success")
        }

        render response as JSON
    }

    def close(Long id){
        def response = [
                error: true,
                message: message(code: "com.billmate.circle.remove.insuccess")
        ]
        if(id){
            Circle circle = Circle.findById(id)
            if(circle && circle.delete()){
                response.error = false
                response.message = message(code: "com.billmate.circle.remove.success")
            }
        }

        render response as JSON
    }

    def regularexpense(Long id){
        def list = []
        Circle circle = Circle.findById(id)
        RegularExpense.findAllByCircle(circle).each {
            if(it.isActive)
                list.add([it.getTitle(), it.getResponsible().getName(), it.getValue(), BMDate.convertDateFormat(it.getBeginDate()), it.getId(), it.getExpenseType().getCssClass(), it.getResponsible().getPhotoOrDefault()])
        }

        def response = ['data': list]

        render response as JSON
    }
}
