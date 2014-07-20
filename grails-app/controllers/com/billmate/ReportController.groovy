package com.billmate

import grails.converters.JSON

class ReportController extends RestrictedController {

    def beforeInterceptor = [action: this.&checkSession]

    def expenses() {
        def circleID = params.circle && ((String) params.circle).isLong() ? Long.parseLong(params.circle) : 0
        def expenseTypeID = params.expenseType && ((String) params.expenseType).isLong() ? Long.parseLong(params.expenseType) : 0
        def registeredUserID = 16
        def dateInterval = (String) params.dateInterval
        def response

        def registeredUser = RegisteredUser.findById(registeredUserID)

        def userReports = new RegisteredUserReports(registeredUser: registeredUser, circleID: circleID, expenseTypeID: expenseTypeID, dateInterval: dateInterval)
        def expenses = userReports.filteredExpensesJSON()

        if(expenses.isEmpty()){
            response = ['error': g.message(code: "com.billmate.user.expense.report.empty", default: "No expenses found for defined filters.")]
        }else{
            response = ['expenses': expenses]
        }

        render response as JSON
    }
}
