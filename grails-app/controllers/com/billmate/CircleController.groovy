package com.billmate

import grails.converters.JSON

class CircleController extends RestrictedController  {

    def beforeInterceptor = [action: this.&checkSession]

    def index() {}

    def  getCirclesIfContainsStringPassedByURL(){
        String params = params.q
        Set<Circle> circleSet = new HashSet<Circle>()
        Circle.findAll().each { if(it.getName().toUpperCase().contains(params.toUpperCase())){
                                    it.setDescription(it.getCssClass())
                                    circleSet.add(it)
                              }}

        def response = [
                'error': false,
                'data': circleSet,
                'code': message(code: "com.billmate.house.modal.createdSuccessfully"),
                'class': "alert alert-success form-modal-house-success"
        ]

        if (circleSet.isEmpty()) {
            response.error = true
            response.code = message(code: "com.billmate.house.modal.createdUnsuccessfully")
            response.class = "alert alert-error form-modal-house-error"
        }
        render response as JSON
    }

}
