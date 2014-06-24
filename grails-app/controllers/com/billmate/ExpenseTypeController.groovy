package com.billmate

import grails.converters.JSON

class ExpenseTypeController extends RestrictedController  {

    def beforeInterceptor = [action: this.&checkSession]

    def index() {}

    def Set<ExpenseType> getExpensesIfContainsStringPassedByURL(){
        String params = params.q
        Set<ExpenseType> expenseTypes = new HashSet<ExpenseType>()
        ExpenseType.findAll().each { if(it.getName().toUpperCase().contains(params.toUpperCase())) expenseTypes.add(it) }

        def response = [
                'error': false,
                'data': expenseTypes,
                'code': message(code: "com.billmate.house.modal.createdSuccessfully"),
                'class': "alert alert-success form-modal-house-success"
        ]

        if (expenseTypes.isEmpty()) {
            response.error = true
            response.code = message(code: "com.billmate.house.modal.createdUnsuccessfully")
            response.class = "alert alert-error form-modal-house-error"
        }
        render response as JSON
    }
}
