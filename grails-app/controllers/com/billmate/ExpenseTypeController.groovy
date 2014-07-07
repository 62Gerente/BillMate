package com.billmate

import grails.converters.JSON

class ExpenseTypeController extends RestrictedController  {

    def beforeInterceptor = [action: this.&checkSession]

}
