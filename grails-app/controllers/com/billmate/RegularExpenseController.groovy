package com.billmate

import grails.converters.JSON

class RegularExpenseController extends RestrictedController{
    static allowedMethods = [saveExpense: "POST", postpone: ["POST", "GET"]]

    def beforeInterceptor = [action: this.&checkSession]

    def save(){
        def response

        if(Integer.parseInt(params.numberSelected) == 0){
            response = [
                    'error': true,
                    'code': message(code: "com.billmate.expense.create.withoutUsersSelecteds"),
                    'class': "alert alert-error form-modal-house-error"
            ]
        }
        else {
            List<String> listOfFriends = params.getList("listOfFriends[]")
            List<String> listValuesUsers = params.getList("listValuesUsers[]")

            RegularExpense regularExpense = setValuesRegularExpense(params)

            if (!regularExpense.create(listOfFriends, listValuesUsers)) {
                response = [
                        'error': true,
                        'code': message(code: "com.billmate.regularExpense.modal.createdUnsuccessfully"),
                        'class': "alert alert-error form-modal-house-error"
                ]
            }else{
                response = [
                        'error': false,
                        'code': message(code: "com.billmate.regularExpense.modal.createdSuccessfully"),
                        'class': "alert alert-success form-modal-house-error"
                ]
            }
        }

        render response as JSON
    }

    def extractInts( String input ) {
        input.findAll( /\d+/ )*.toInteger()
    }

    private RegularExpense setValuesRegularExpense(params){
        RegularExpense regularExpense = new RegularExpense()

        regularExpense.setTitle(params.name)
        regularExpense.setDescription(params.description)
        regularExpense.setValue(Double.parseDouble(params.value))
        regularExpense.setCircle(Circle.findById(Long.parseLong(params.idCircle)))
        regularExpense.setExpenseType(ExpenseType.findById(Long.parseLong(params.idExpenseType)))
        regularExpense.setResponsible(User.findById(Long.parseLong(params.idUser)).getRegisteredUser())
        regularExpense.setPaymentDeadline(BMDate.convertStringsToDate(params.paymentDeadline,false))
        regularExpense.setReceptionDeadline(BMDate.convertStringsToDate(params.receptionDeadline,false))
        regularExpense.setBeginDate(BMDate.convertStringsToDate(params.beginDate,true))
        regularExpense.setEndDate(BMDate.convertStringsToDate(params.endDate,false))
        regularExpense.setReceptionEndDate(BMDate.convertStringsToDate(params.receptionEndDate,false))
        regularExpense.setPaymentEndDate(BMDate.convertStringsToDate(params.paymentEndDate,false))


        int year = extractInts(params.periodicity)[2]
        int month = extractInts(params.periodicity)[1]
        int day = extractInts(params.periodicity)[0]

        regularExpense.setIntervalYears(year)
        regularExpense.setIntervalMonths(month)
        regularExpense.setIntervalDays(day)

        return regularExpense
    }

    def saveExpense(Long id) {
        def regularExpense = RegularExpense.get(id)
        Double value
        if(params.value.isNumber()) value = Double.parseDouble(params.value)

        def responseData = [
                'error'  : false,
                'message': message(code: "com.billmate.expense.success")
        ]

        Expense expense = new Expense(regularExpense, authenticatedUser(), value: value)

        if (!expense.secureSave()) {
            responseData.error = true;
            if(expense.getErrors().getErrorCount()){
                responseData.message = message(error: expense.getErrors().getAllErrors().first())
            }else{
                responseData.message = message(code: "com.billmate.generic.error.message")
            }

        }

        render responseData as JSON
    }

    def postpone(Long id) {
        def regularExpense = RegularExpense.get(id)

        def responseData = [
                'error'  : false,
                'message': message(code: "com.billmate.regularExpense.cancel.success")
        ]

        regularExpense.postpone()

        if (!regularExpense.save()) {
            responseData.error = true;
            if(regularExpense.getErrors().getErrorCount()){
                responseData.message = message(error: regularExpense.getErrors().getAllErrors().first())
            }else{
                responseData.message = message(code: "com.billmate.generic.error.message")
            }

        }

        render responseData as JSON
    }
}
