package com.billmate

import grails.converters.JSON
import org.joda.time.DateTime

import javax.servlet.http.HttpServletRequest
import java.text.DecimalFormat

class UserController extends RestrictedController {
    static allowedMethods = [updateField: "POST", history: "GET"]

    def beforeInterceptor = [action: this.&checkSession]

    def updateProperty(Long id) {
        def user = User.findById(id)
        def property = params.name
        def value = params.value

        user.setProperty(property, value)

        def response = [
                'error'  : false,
                'message': message(code: "com.billmate.user.updateProperty.success")
        ]

        if(!user.save()) {
            response.error = true
            response.message = message(error: user.getErrors().getAllErrors().first());
        }

        render response as JSON
    }

    def expenses(Long id){
        return [user: authenticatedUser()]
    }

    //Alterar Query para ir buscar apenas as que ainda estão por pagar
    def teste(){
        def list = []
        List<Expense> expenseList = new LinkedList<Expense>()
        User user = User.findById(3);
        Debt.findAllByUser(user).each {
            Expense expense = it.getExpense()
            list.add([expense.getTitle(), expense.getResponsible().getName(), expense.getCircle().getName(), expense.amountPaidBy(user.getId()), expense.getValue(),
                      expense.getInvoice()?.getPath(), expense.getReceipt()?.getPath(), expense.isResolved(), expense.getId(),
                      expense.getExpenseType().getCssClass(), expense.getResponsible().getPhotoOrDefault(), expense.getCircle().getCssClass(),
                      expense.debtOf(user.getId()).getValue(), expense.amountPaid()
                     ]);
        }

        def response = ['data': list]

        render response as JSON
    }
}
