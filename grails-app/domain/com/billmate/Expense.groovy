package com.billmate

abstract class Expense {

    String title
    String description
    Double value
    Date paymentDeadline
    Date receptionDeadline
    Date createdAt
    Date paymentDate
    Date receptionDate

    static constraints = {
        title nullable: false, blank: false
        description maxSize: 2000, nullable: true, blank: true
        value min: 0, nullable: false
        paymentDeadline nullable: true
        receptionDeadline nullable: true
        createdAt nullable: false, defaultValue: new Date(), min: new Date()
        paymentDate nullable: true
        receptionDate nullable: true
    }

    String toString(){
        title
    }
}
