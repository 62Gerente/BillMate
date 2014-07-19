package com.billmate.api.v1

import com.billmate.AuthenticationToken
import com.billmate.Circle
import com.billmate.Expense
import com.billmate.ExpenseType
import com.billmate.RegisteredUser
import grails.converters.JSON

class ExpenseController {
    static allowedMethods = [save: 'POST']
    static namespace = "v1"

    def save(){

        if (!checkToken() || !checkCircleId() || !checkEmail() || !checkValue() || !checkName()) {
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
            return
        }

        def user = RegisteredUser.findByEmail(params.email)
        if(!user){
            response = [
                    'error':[
                            'msg': message(code: "com.billmate.user.email.null")
                    ]
            ]
            render response as JSON
            return
        }
        def user_circles = user.getCircles()*.id
        def contains_circle = user_circles.contains(((String)params.circle_id).toLong())
        if(!contains_circle){
            response = [
                    'error':[
                            msg: message(code: "com.billmate.session.forbidden")
                    ]
            ]
            render response as JSON
            return
        }

        def circle = Circle.findById(params.circle_id)
        def listOfFriends = circle.getUsers()*.id
        List<String> listOfFriends_strings = new LinkedList<>()

        listOfFriends.each {
            listOfFriends_strings.add(it.toString())
        }

        def value = ((String) params.value).toDouble()
        def valueSplit = value/listOfFriends.size()
        double[] listValueSplit = new double[listOfFriends.size()]
        Arrays.fill(listValueSplit, valueSplit)

        Expense expense = setValuesExpenseType(circle,user)
        if(!expense.create(listOfFriends_strings,listValueSplit.toList(),user.getId())){
            response = [
                    error: [
                            msg: message(code: "com.billmate.expense.save.insuccess")
                    ]
            ]
            render response as JSON
            return
        }

        response = [
                name: expense.getTitle()
        ]
        render response as JSON
        return
    }


    private Expense setValuesExpenseType(Circle circle, RegisteredUser user){
        Expense expense = new Expense()
        expense.setTitle(params.name)
        expense.setValue(Double.parseDouble(params.value))
        expense.setCircle(circle)
        expense.setExpenseType(ExpenseType.findByName("Outros"))
        expense.setResponsible(user)
        expense.setBeginDate(new Date())
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

    private checkName(){
        def response = []
        if(!params.token){
            response = [
                    error: [
                            'msg': message(code: "com.billmate.Expense.name.nullable")
                    ]
            ]
            render response as JSON
        }
        return true
    }

    private checkCircleId(){
        def response = []
        if(!params.circle_id || !((String)params.circle_id).isLong()){
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

        if(!params.value || !value.isDouble()){
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
