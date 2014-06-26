package com.billmate

import grails.converters.JSON

class ExpenseController extends RestrictedController {

    def beforeInterceptor = [action: this.&checkSession]

    def create(){

        def response = [
                'error': false,
                'data': null,
                'code': message(code: "com.billmate.expense.modal.createdSuccessfully"),
                'class': "alert alert-success form-modal-house-success"
        ]

        if(Integer.parseInt(params.numberSelected) == 0){
            handleErrorMessages(response,"com.billmate.expense.modal.error.withoutUsersSelecteds")
        }
        else {
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
            String strPaymentDeadline = params.paymentDeadline
            String strReceptionDeadline = params.receptionDeadline
            String strBeginDate = params.beginDate
            String strEndDate = params.endDate
            String strPaymentDate = params.paymentDate
            String strReceptionDate = params.receptionDate
            Date paymentDeadline, receptionDeadline, beginDate, endDate, paymentDate, receptionDate

            if (!strPaymentDeadline.equals("")) paymentDeadline = Date.parse("dd/MM/yyyy", strPaymentDeadline)
            if (!strReceptionDeadline.equals("")) receptionDeadline = Date.parse("dd/MM/yyyy", strReceptionDeadline)
            if (!strBeginDate.equals("")) beginDate = Date.parse("dd/MM/yyyy", strBeginDate) else beginDate = new Date()
            if (!strEndDate.equals("")) endDate = Date.parse("dd/MM/yyyy", strEndDate)
            if (!strPaymentDate.equals("")) paymentDate = Date.parse("dd/MM/yyyy", strPaymentDate)
            if (!strReceptionDate.equals("")) receptionDate = Date.parse("dd/MM/yyyy", strReceptionDate)

            Expense expense = new Expense(title: name, description: description, value: value, circle: circle, expenseType: expenseType,
                    responsible: user.getRegisteredUser(), paymentDeadline: paymentDeadline, receptionDeadline: receptionDeadline,
                    beginDate: beginDate, endDate: endDate, paymentDate: paymentDate, receptionDate: receptionDate)

            if (!expense.create(listOfFriends, listValuesUsers)) {
                handleErrorMessages(response,"com.billmate.expense.modal.createdUnsuccessfully")
            }else{
                response.data = expense;
            }
        }
        render response as JSON
    }

    def convertStringsToDate(String dateString, boolean generateDateIfNotExists){
        Date date
        if (!dateString.equals("")) date = Date.parse("dd/MM/yyyy", dateString)
    }

    def handleErrorMessages(def response, String message){
        response.error = true
        response.code = message(code: message)
        response.class = "alert alert-error form-modal-house-error"
        return response
    }
}
