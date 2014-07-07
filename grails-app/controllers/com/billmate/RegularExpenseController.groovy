package com.billmate

import grails.converters.JSON

class RegularExpenseController extends RestrictedController{
    static allowedMethods = [saveExpense: "POST", postpone: ["POST", "GET"], show: "POST"]

    def beforeInterceptor = [action: this.&checkSession]

    def saveExpense(Long id) {
        def regularExpense = RegularExpense.get(id)
        Double value
        if(params.value.isNumber()) value = Double.parseDouble(params.value)

        def responseData = [
                'error'  : false,
                'message': message(code: "com.billmate.expense.success")
        ]

        Expense expense = new Expense(regularExpense, authenticatedUser(), value: value)

        if (!expense.saveAndPostponeRegularExpense()) {
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

    def show(Long id){
        def responseData = [
                'error'  : true,
                'message': message(code: "com.billmate.regularExpense.cancel.success")
        ]

        RegularExpense regularExpense = RegularExpense.findById(id)

        if(regularExpense){
            responseData.data = [name: regularExpense.getTitle(), description: regularExpense.getDescription(),
                                 id : regularExpense.getExpenseType().getId(), expenseTypeName: regularExpense.getExpenseType().getName(),
                                 expenseTypeCssClass: regularExpense.getExpenseType().getCssClass(), value: regularExpense.getValue(),
                                 paymentDeadline: convertDateFormat(regularExpense.getPaymentDeadline()), receptionDeadline: convertDateFormat(regularExpense.getReceptionDeadline()),
                                 receptionBeginDate: convertDateFormat(regularExpense.getReceptionBeginDate()), receptionEndDate: convertDateFormat(regularExpense.getReceptionEndDate()),
                                 paymentBeginDate: convertDateFormat(regularExpense.getPaymentBeginDate()), paymentEndDate: convertDateFormat(regularExpense.getPaymentEndDate())]
            responseData.error = false
            responseData.message = message(code: "com.billmate.regularExpense.cancel.success")
        }

        render responseData as JSON
    }

    private String convertDateFormat(Date date){
        return date?.format("dd/MM/yyyy")
    }
}
