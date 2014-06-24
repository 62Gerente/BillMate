package com.billmate

import grails.converters.JSON

class CircleController extends RestrictedController  {

    def beforeInterceptor = [action: this.&checkSession]

    def index() {}

    def getCirclesIfContainsStringPassedByURL(){
        Set<Object> circles = new HashSet<>()
        long id = Long.parseLong(params.id)
        String params = params.q
        Set<Circle> circleSet = RegisteredUser.findById(id).getAllCircles()

        circleSet.each { if(it.getName().toUpperCase().contains(params.toUpperCase())){
                                    circles.add([id: it.getId(), icon: it.getCssClass(), name: it.getName()])
                              }}

        def response = [
                'error': false,
                'data': circles,
                'code': message(code: "com.billmate.house.modal.createdSuccessfully"),
                'class': "alert alert-success form-modal-house-success"
        ]

        if (circles.isEmpty()) {
            response.error = true
            response.code = message(code: "com.billmate.house.modal.createdUnsuccessfully")
            response.class = "alert alert-error form-modal-house-error"
        }
        render response as JSON
    }

    def getFriendsOfaCircleExceptId(){
        Set<Object> circleFriends = new HashSet<>()
        Circle.findById(Long.parseLong(params.id_circle)).getUsers().each {
            circleFriends.add([id: it.getId(), photo: it.getPhotoOrDefault(), name: it.getName(), value: 0, selectable: true, absolute: false, percentage: false])
        }
        def response = [
                'error': false,
                'data': circleFriends,
                'code': message(code: "com.billmate.house.modal.createdSuccessfully"),
                'class': "alert alert-success form-modal-house-success"
        ]

        if (circleFriends.isEmpty()) {
            response.error = true
            response.code = message(code: "com.billmate.house.modal.createdUnsuccessfully")
            response.class = "alert alert-error form-modal-house-error"
        }
        render response as JSON
    }

}
