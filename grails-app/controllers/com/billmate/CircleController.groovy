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

    def getFriendsOfaCircle(){
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

    def getIDAndNameCircle(){
        Long id = Long.parseLong(params.id)
        Circle circle = Circle.findById(id)

        def response = [
                'error'  : false,
                'data'   : [circleID: circle.getId(), circleIcon: circle.getCssClass(), circleName: circle.getName()],
                'message': message(code: "com.billmate.registeredUser.updatePhoto.success")
        ]

        if(!circle) {
            response.error = true
            response.message = message(code: "com.billmate.registeredUser.updatePhoto.success")
        }

        render response as JSON
    }

    def getUsersByCircle(){
        Set<User> users = Circle.findById(Long.parseLong(params.id)).getUsers()
        Set<LinkedHashMap> hashMapSet = new HashSet<LinkedHashMap>()
        users.each { def obj = new LinkedHashMap()
            obj.id = it.getId()
            obj.name = it.getName()
            obj.photo = it.getPhotoOrDefault()
            hashMapSet.add(obj)}

        def response = [
                'error': false,
                'data': hashMapSet,
                'code': message(code: "com.billmate.expense.modal.createdSuccessfully"),
                'class': "alert alert-success form-modal-house-success"
        ]

        if (!users) {
            response = [
                    'error': true,
                    'data': null,
                    'code': message(code: "com.billmate.expense.modal.createdUnsuccessfully"),
                    'class': "alert alert-error form-modal-house-error"
            ]
        }

        render response as JSON
    }
}
