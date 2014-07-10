package com.billmate

import com.lucastex.grails.fileuploader.UFile
import grails.converters.JSON

import java.text.DateFormat

class ExpenseController extends RestrictedController {
    static allowedMethods = [show: "GET", delete: "DELETE"]

    def beforeInterceptor = [action: this.&checkSession]

    def show(Long id) {
        def expense = Expense.findById(id)

        return [user: authenticatedUser(), expense: expense]
    }

    def updateProperty(Long id) {
        def expense = Expense.findById(id)
        def property = params.name
        def value = params.value

        def dates = ["beginDate", "endDate", "paymentDeadline", "receptionDeadline", "paymentDate", "receptionDate"]

        if(dates.contains(property)){
            if(value){
                value = new Date().parse("DD-MM-YYYY HH:mm", value)
            }else{
                value = null
            }
        }

        expense.setProperty(property, value)

        def response = [
                'error'  : false,
                'message': message(code: "com.billmate.expense.updateProperty.success")
        ]

        if(!expense.save()) {
            response.error = true
            response.message = message(error: expense.getErrors().getAllErrors().first());
        }

        render response as JSON
    }

    def errorUpload() {
        def error = flash.message

        def response = [
                'error'  : true,
                'message': error
        ]

        render response as JSON
    }

    def successUploadInvoice() {
        def expense = Expense.findById(params.id)
        def ufile = UFile.findById(params.ufileId)

        expense.setInvoice(ufile)

        def response = [
                'error'  : false,
                'message': message(code: "com.billmate.expense.uploadInvoice.success"),
                'invoice_url': createLink(controller: "fileUploader", action: "show", id: ufile.getId())
        ]

        if(!expense.save()) {
            response.error = true
            response.message = message(error: expense.getErrors().getAllErrors().first());
        }

        render response as JSON
    }

    def successUploadReceipt() {
        def expense = Expense.findById(params.id)
        def ufile = UFile.findById(params.ufileId)

        expense.setReceipt(ufile)

        def response = [
                'error'  : false,
                'message': message(code: "com.billmate.expense.uploadReceipt.success"),
                'receipt_url': createLink(controller: "fileUploader", action: "show", id: ufile.getId())
        ]

        if(!expense.save()) {
            response.error = true
            response.message = message(error: expense.getErrors().getAllErrors().first());
        }

        render response as JSON
    }

    def delete(Long id) {
        def expense = Expense.findById(id)

        expense.setIsDeleted(true)

        if(expense.save()){
            flash.message = "com.billmate.expense.delete.success"
            flash.m_default = "Expense deleted with success."

            return redirect(controller: 'dashboard', action: 'circle', id: expense.getCircle().getId())
        }else{
            flash.error = "com.billmate.expense.delete.failure"
            flash.e_default = "Error deleting expense."

            return redirect(controller: 'expense', action: 'show', id: expense.getId())
        }
    }

    def list(Long id){
        def list = []
        User user = User.findById(RegisteredUser.findById(id).getUserId());
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
