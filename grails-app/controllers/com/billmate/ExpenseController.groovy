package com.billmate

import grails.converters.JSON

class ExpenseController extends RestrictedController {

    static allowedMethods = [save: "POST"]

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
            String regularExpenseID = params.regularExpenseID

            RegularExpense regularExpense
            if(regularExpenseID && regularExpenseID.isLong()){
                regularExpense = RegularExpense.findById(Long.parseLong(regularExpenseID))
            }

            Expense expense = setValuesExpenseType(regularExpense)

            if (!expense.create(listOfFriends, listValuesUsers)) {
                response = [
                        'error': true,
                        'data': null,
                        'code': message(code: "com.billmate.expense.save.insuccess"),
                        'class': "alert alert-error form-modal-house-error"
                ]
            }else{
                response = [
                        'error': false,
                        'data': expense,
                        'code': message(code: "com.billmate.expense.save.success"),
                        'class': "alert alert-success form-modal-house-error"
                ]
            }
        }

        render response as JSON
    }

    private Expense setValuesExpenseType(RegularExpense regularExpense){
        Expense expense = new Expense()

        expense.setTitle(params.name)
        expense.setDescription(params.description)
        expense.setValue(Double.parseDouble(params.value))
        expense.setCircle(Circle.findById(Long.parseLong(params.idCircle)))
        expense.setExpenseType(ExpenseType.findById(Long.parseLong(params.idExpenseType)))
        expense.setResponsible(User.findById(Long.parseLong(params.idUser)).getRegisteredUser())
        expense.setPaymentDeadline(convertStringsToDate(params.paymentDeadline, false))
        expense.setReceptionDeadline(convertStringsToDate(params.receptionDeadline,false))
        expense.setBeginDate(convertStringsToDate(params.beginDate,true))
        expense.setEndDate(convertStringsToDate(params.endDate,false))
        expense.setPaymentDate(convertStringsToDate(params.paymentDate,false))
        expense.setReceptionDate(convertStringsToDate(params.receptionDate,false))
        expense.setRegularExpense(regularExpense)
        return expense
    }

    private static def convertStringsToDate(String dateString, boolean generateDateIfNotExists){
        Date date = null
        if (!dateString.equals("")) date = Date.parse("dd/MM/yyyy", dateString)
        if(generateDateIfNotExists) date = new Date()
        return date
    }
}
