package com.billmate

import com.lucastex.grails.fileuploader.UFile

abstract class Expense {
    static belongsTo = [Circle, RegisteredUser]
    static hasMany = [expenseTypes: ExpenseType, payments: Payment,
                      customizedDebts: CustomDebt, actions: Action]

    RegisteredUser responsible
    UFile invoice
    UFile receipt

    String title
    String description
    Double value
    Date beginDate
    Date endDate
    Date paymentDeadline
    Date receptionDeadline
    Date createdAt
    Date paymentDate
    Date receptionDate

    static constraints = {
        responsible nullable: false
        invoice nullable: true
        receipt nullable: true

        title nullable: false, blank: false
        description maxSize: 2000, nullable: true, blank: true
        value min: 0D, nullable: false
        beginDate nullable: false, defaultValue: new Date()
        endDate nullable: true, min: beginDate
        paymentDeadline nullable: true
        receptionDeadline nullable: true
        createdAt nullable: false, defaultValue: new Date()
        paymentDate nullable: true
        receptionDate nullable: true
    }

    String toString(){
        title
    }
}
