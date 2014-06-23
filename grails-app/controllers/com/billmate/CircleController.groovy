package com.billmate

import grails.converters.JSON

class CircleController extends RestrictedController  {

    def beforeInterceptor = [action: this.&checkSession]

    def index() {}

    def getCirclesIfContainsStringPassedByURL(){
        long id = Long.parseLong(params.id)
        String params = params.q
        Set<Circle> circleSet = RegisteredUser.findById(id).getAllCircles()
        Set<Circle> result = new HashSet<>()

        circleSet.each { if(it.getName().toUpperCase().contains(params.toUpperCase())){
                                    it.setDescription(it.getCssClass())
                                    result.add(it)
                              }}

        def response = [
                'error': false,
                'data': result,
                'code': message(code: "com.billmate.house.modal.createdSuccessfully"),
                'class': "alert alert-success form-modal-house-success"
        ]

        if (result.isEmpty()) {
            response.error = true
            response.code = message(code: "com.billmate.house.modal.createdUnsuccessfully")
            response.class = "alert alert-error form-modal-house-error"
        }
        render response as JSON
    }

    def getFriendsOfaCircleExceptId(){
        Set<Object> things = new HashSet<>()
        Circle.findById(Long.parseLong(params.id_circle)).getUsers().each {
            things.add([id: it.getId(), photo: it.getPhotoOrDefault(), name: it.getName(), value: 0, selectable: true, absolute: false, percentage: false])
        }
        def response = [
                'error': false,
                'data': things,
                'code': message(code: "com.billmate.house.modal.createdSuccessfully"),
                'class': "alert alert-success form-modal-house-success"
        ]

        if (things.isEmpty()) {
            response.error = true
            response.code = message(code: "com.billmate.house.modal.createdUnsuccessfully")
            response.class = "alert alert-error form-modal-house-error"
        }
        render response as JSON
    }

}
