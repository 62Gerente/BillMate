package com.billmate

import grails.converters.JSON

class CircleController extends RestrictedController {

    static allowedMethods = []

    def beforeInterceptor = [action: this.&checkSession]

    def expenses(Long idCircle, Long idUser){
        def list = []
        Circle circle = Circle.findById(idCircle)
        User user = User.findById(RegisteredUser.findById(idUser).getUserId())
        Expense.findAllByCircle(circle).each {
            def debtUser = it.debtOf(user.getId())
            if(debtUser){
                list.add([it.getTitle(), it.getResponsible().getName(), it.amountPaidBy(user.getId()), it.getValue(),
                          it.getInvoice()?.getPath(), it.getReceipt()?.getPath(), it.isResolved(), it.getId(),
                          it.getExpenseType().getCssClass(), it.getResponsible().getPhotoOrDefault(), it.getCircle().getCssClass(),
                          debtUser.getValue(), it.amountPaid()
                ]);
            }
        }

        def response = ['data': list]

        render response as JSON
    }

}
