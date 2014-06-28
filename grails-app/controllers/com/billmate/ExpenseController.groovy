package com.billmate

import com.lucastex.grails.fileuploader.UFile
import grails.converters.JSON

import java.text.DateFormat

class ExpenseController extends RestrictedController {
    static allowedMethods = [show: "GET"]

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
}