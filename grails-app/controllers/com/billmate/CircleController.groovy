package com.billmate

import grails.converters.JSON

class CircleController extends RestrictedController  {

    static allowedMethods = [assignedUsers: "POST", show: "POST"]

    def beforeInterceptor = [action: this.&checkSession]

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

    def Set<ExpenseType> expenseType(){
        Long id = Long.parseLong(params.idCircle)
        String params = params.q
        Set<ExpenseType> expenseTypes = new HashSet<ExpenseType>()
        Circle.findById(id).getExpenseTypes().each { if(it.getName().toUpperCase().contains(params.toUpperCase())) expenseTypes.add(it) }

        def response = [ 'data': expenseTypes ]

        render response as JSON
    }
}
