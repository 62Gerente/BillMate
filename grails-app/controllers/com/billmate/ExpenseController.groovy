package com.billmate

class ExpenseController extends RestrictedController {
    static allowedMethods = [show: "GET"]

    def beforeInterceptor = [action: this.&checkSession]

    def show(Long id) {
        def expense = Expense.findById(id)

        return [user: authenticatedUser(), expense: expense]
    }
}