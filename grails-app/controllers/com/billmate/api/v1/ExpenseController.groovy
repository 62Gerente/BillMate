package com.billmate.api.v1

import com.billmate.AuthenticationToken
import com.billmate.BMDate
import com.billmate.Circle
import com.billmate.Expense
import com.billmate.ExpenseType
import com.billmate.RegisteredUser
import com.billmate.RegularExpense
import com.billmate.User
import grails.converters.JSON

class ExpenseController {





    def save(){

        if (!checkToken() || !checkCircleId() || !checkEmail() || !checkValue()) {
            return
        }


        def response = []
        def atoken = AuthenticationToken.findByTokenAndEmail(params.token,params.email)

        if(!atoken){
            response = [
                    'error':[
                            msg: message(code: "com.billmate.authenticationtoken.token.invalid")
                    ]
            ]
            render response as JSON
        }

        def user = RegisteredUser.findByEmail(params.email)
        if(!user){
            response = [
                    'error':[
                            'msg': message(code: "com.billmate.user.email.null")
                    ]
            ]
            render response as JSON
        }



        def listOfFriends = user.getCircles().get



         = //Buscar elementos
            List<String> listValuesUsers = //gerar array com valor/listoffriends.size
            String regularExpenseID = params.regularExpenseID

            Expense expense = setValuesExpenseType(null)

            response = [
                    'error': false,
                    'code': message(code: "com.billmate.expense.save.success"),
                    'class': "alert alert-success form-modal-house-error"
            ]
            if (!expense.create(listOfFriends, listValuesUsers, session.user.getId())) {
                response.error = true
                response.code = message(code: "com.billmate.expense.save.insuccess")
                response.class = "alert alert-error form-modal-house-error"
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
        expense.setPaymentDeadline(BMDate.convertStringsToDate(params.paymentDeadline, false))
        expense.setReceptionDeadline(BMDate.convertStringsToDate(params.receptionDeadline,false))
        expense.setBeginDate(new Date())
        expense.setRegularExpense(regularExpense)
        return expense
    }



    private checkToken(){
        def response = []
        if(!params['token']){
            response = [
                    error: [
                            'msg': message(code: "com.billmate.authenticationtoken.token.null")
                    ]
            ]
            render response as JSON
        }
        return true
    }

    private checkCircleId(){
        def response = []
        if(!((String)params.circle_id).isLong()){
            response = [
                    error: [
                            'msg': message(code: "com.billmate.circle.id.nullable")
                    ]
            ]
            render response as JSON
        }
        return true
    }

    private checkEmail() {
        def emailPattern = /[_A-Za-z0-9-]+(\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\.[A-Za-z0-9]+)*(\.[A-Za-z]{2,})/
        if (!params['email'] ==~ emailPattern) {
            def response = [
                    'error': [
                            'msg': message(code: "com.billmate.register.save.invalidEmail")
                    ]
            ]
            render response as JSON
        }
        return true
    }

    private checkValue(){
        def response = []
        def value = (String)params.value

        if(!value.isDouble()){
            response = [
                    error: [
                            'msg': message(code: "com.billmate.Expense.value.nullable")
                    ]
            ]
            render response as JSON
        }
        if(value.toDouble() <= 0){
            response = [
                    error: [
                            'msg': message(code: "com.billmate.Expense.value.nullable")
                    ]
            ]
            render response as JSON
        }
        return true
    }
}
