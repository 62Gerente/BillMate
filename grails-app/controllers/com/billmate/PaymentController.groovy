package com.billmate

import grails.converters.JSON

class PaymentController extends RestrictedController {
    def beforeInterceptor = [action: this.&checkSession]

    def confirm() {
        def response = [
                'error'  : false,
                'message': message(code: "com.billmate.registeredUser.confirmPayments.success")
        ]

        if(params.list('payment[]').isEmpty()){
            response.error = true
            response.message = message(code: "com.billmate.registeredUser.confirmPayments.empty")
        }else if(!authenticatedUser().confirmPayments(params.list('payment[]'))){
            response.error = true
            response.message = message(code: "com.billmate.registeredUser.confirmPayments.error")
        }

        render response as JSON
    }

    def cancel() {
        def response = [
                'error'  : false,
                'message': message(code: "com.billmate.registeredUser.cancelPayments.success")
        ]

        if(params.list('payment[]').isEmpty()){
            response.error = true
            response.message = message(code: "com.billmate.registeredUser.cancelPayments.empty")
        }else if(!authenticatedUser().cancelPayments(params.list('payment[]'), session.user)){
            response.error = true
            response.message = message(code: "com.billmate.registeredUser.cancelPayments.error")
        }

        render response as JSON
    }

    def confirmOne(int id){
        Debt debt = Debt.findById(id)
        debt.getExpense().payAndConfirmExpense(debt.getExpense().amountInDebtOf(debt.getUserId()), debt, true, debt.getUser())
        redirect(controller: "expense", action: "show", id: debt.getExpense().getId())
    }

    def confirmAll(String idsExpense, String values, Long idUser, Boolean flag){
        Boolean result = true
        Integer position = 0
        List<String> listExpenses = idsExpense.substring(1,idsExpense.length()-1).split(",")
        List<Double> listValues = new LinkedList<>()
        values.substring(1,values.length()-1).split(",").each {listValues.add(Double.parseDouble(it))}
        try{
            for(String ids : listExpenses){
                Long id = Long.parseLong(ids)
                User user = User.findById(idUser)
                Expense expense = Expense.findById(id)
                Debt debt = expense.debtOf(user.getId())
                expense.payAndConfirmExpense(listValues[position], debt, flag, user)
                position++
                result = true
            }
        }catch(Exception ePaymentConfirm){
            ePaymentConfirm.printStackTrace()
            result = false
        }

        def response = [
                error: false,
                message: message(code: "com.billmate.payment.confirmAll.success")
        ]

        if(!result){
            response.error = true;
            response.message = message(code: "com.billmate.payment.confirmAll.insuccess")
        }

        render response as JSON
    }
}
