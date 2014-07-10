package com.billmate

import grails.converters.JSON

class CircleController extends RestrictedController  {

    static allowedMethods = [circles: "POST", assignedUsers: "POST", show: "POST"]

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

    def Set<ExpenseType> expenseTypes(Long idCircle){
        String params = params.q
        Set<ExpenseType> expenseTypes = new HashSet<ExpenseType>()
        Circle.findById(idCircle).getExpenseTypes().each { if(it.getName().toUpperCase().contains(params.toUpperCase())) expenseTypes.add(it) }
        def response = [ 'data': expenseTypes ]

        render response as JSON
    }
}
