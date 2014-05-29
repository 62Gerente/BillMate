package com.billmate

import com.lucastex.grails.fileuploader.UFile

class Expense {
    static belongsTo = [Circle, RegisteredUser]
    static hasMany = [expenseTypes: ExpenseType, payments: Payment,
                      customizedDebts: CustomDebt, actions: Action]
    static hasOne = [regularExpense: RegularExpense, occasionalExpense: OccasionalExpense]

    RegisteredUser responsible
    UFile invoice
    UFile receipt

    String title
    String description
    Double value
    Date beginDate = new Date()
    Date endDate
    Date paymentDeadline
    Date receptionDeadline
    Date createdAt = new Date()
    Date paymentDate
    Date receptionDate

    static constraints = {
        responsible nullable: false
        invoice nullable: true
        receipt nullable: true

        regularExpense nullable: true
        occasionalExpense nullable: true

        title nullable: false
        description maxSize: 2000, nullable: true
        value min: 0D, nullable: false
        beginDate nullable: false
        endDate nullable: true
        paymentDeadline nullable: true
        receptionDeadline nullable: true
        createdAt nullable: false
        paymentDate nullable: true
        receptionDate nullable: true
    }

    String toString(){
        title
    }
}
