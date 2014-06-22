package com.billmate

import grails.converters.JSON

class CircleController extends RestrictedController  {

    def beforeInterceptor = [action: this.&checkSession]

    def index() {}

    def  getCirclesIfContainsStringPassedByURL(){
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

}
