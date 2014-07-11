package com.billmate

import com.lucastex.grails.fileuploader.UFile
import grails.converters.JSON

import java.text.DateFormat

class ExpenseController extends RestrictedController {
    static allowedMethods = [show: "GET", delete: "DELETE"]

    def beforeInterceptor = [action: this.&checkSession]

    def show(Long id) {
        def expense = Expense.findById(id)
        def breadcrumb = [
                [name: expense.getTitle()]
        ]

        return [breadcrumb: breadcrumb, user: authenticatedUser(), expense: expense]
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
            if(expense) {
                list.add([expense.getTitle(), expense.getResponsible().getName(), expense.getCircle().getName(), expense.amountPaidBy(user.getId()), expense.getValue(),
                          expense.getInvoice()?.getPath(), expense.getReceipt()?.getPath(), expense.isResolved(), expense.getId(),
                          expense.getExpenseType().getCssClass(), expense.getResponsible().getPhotoOrDefault(), expense.getCircle().getCssClass(),
                          expense.debtOf(user.getId()).getValue(), expense.amountPaid()
                ]);
            }
        }

        def response = ['data': list]

        render response as JSON
    }

    def save(){

        def response

        if(Integer.parseInt(params.numberSelected) == 0){
            response = [
                    'error': true,
                    'code': message(code: "com.billmate.expense.create.withoutUsersSelecteds"),
                    'class': "alert alert-error form-modal-house-error"
            ]
        }
        else {
            List<String> listOfFriends = params.getList("listOfFriends[]")
            List<String> listValuesUsers = params.getList("listValuesUsers[]")
            String regularExpenseID = params.regularExpenseID

            RegularExpense regularExpense
            if(regularExpenseID && regularExpenseID.isLong()){
                regularExpense = RegularExpense.findById(Long.parseLong(regularExpenseID))
            }

            Expense expense = setValuesExpenseType(regularExpense)

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
        expense.setBeginDate(BMDate.convertStringsToDate(params.beginDate,true))
        expense.setEndDate(BMDate.convertStringsToDate(params.endDate,false))
        expense.setPaymentDate(BMDate.convertStringsToDate(params.paymentDate,false))
        expense.setReceptionDate(BMDate.convertStringsToDate(params.receptionDate,false))
        expense.setRegularExpense(regularExpense)
        return expense
    }
}
