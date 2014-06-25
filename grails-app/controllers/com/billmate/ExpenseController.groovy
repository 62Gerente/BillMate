package com.billmate

import grails.converters.JSON

class ExpenseController extends RestrictedController {

    def beforeInterceptor = [action: this.&checkSession]

    def create(){
        String name = params.name
        Long idCircle = Long.parseLong(params.idCircle)
        Long idExpenseType = Long.parseLong(params.idExpenseType)
        Double value = Double.parseDouble(params.value)
        String description = params.description
        Long idUser = Long.parseLong(params.idUser)
        List<String> listOfFriends = params.getList("listOfFriends[]")
        List<String> listValuesUsers = params.getList("listValuesUsers[]")

        Circle circle = Circle.findById(idCircle)
        ExpenseType expenseType = ExpenseType.findById(idExpenseType)
        User user = User.findById(idUser)
        Expense expense = new Expense(title: name, description: description, value: value, circle: circle, expenseType: expenseType, responsible: user.getRegisteredUser())
        Boolean result = expense.create(listOfFriends, listValuesUsers)

        def response = [
                'error': false,
                'data': expense,
                'code': message(code: "com.billmate.house.modal.createdSuccessfully"),
                'class': "alert alert-success form-modal-house-success"
        ]

        if (!result) {
            response.error = true
            response.code = message(code: "com.billmate.house.modal.createdSuccessfully")
            response.class = "alert alert-error form-modal-house-error"
        }
        render response as JSON
    }
}
