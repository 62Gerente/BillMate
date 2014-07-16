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
        //TODO pôr tudo nos domínios e colocar com transação. Mudar o BuildConfig
        Debt debt = Debt.findById(id)
        new Payment(debt: debt, user: debt.getUser(), value: debt.getExpense().amountInDebtOf(debt.getUserId())).save()
        redirect(controller: "expense", action: "show", id: debt.getExpense().getId())
    }

    def confirmAll(String idsExpense, String values, Long idUser, Boolean flag){
        Boolean result = false
        Integer position = 0
        List<String> listExpenses = idsExpense.substring(1,idsExpense.length()-1).split(",")
        List<String> listValues = values.substring(1,values.length()-1).split(",")
        for(String ids : listExpenses){
            Long id = Long.parseLong(ids)
            Long val = Long.parseLong(listValues[position])
            RegisteredUser registeredUser = User.findById(idUser).getRegisteredUser()
            Debt debt = Expense.findById(id).debtOf(registeredUser.getUserId())
            if(flag){
                new Payment(debt: debt, user: registeredUser.getUser(), value: val).save()
            }else{
                new Payment(debt: debt, user: registeredUser.getUser(), value: val, validationDate: new Date(), isValidated: true).save()
                if (!debt.getResolvedDate() && debt.getValue() <= debt.amountPaid()) {
                    debt.setResolvedDate(new Date())
                    debt.save()
                }
            }
            position++
            result = true
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
