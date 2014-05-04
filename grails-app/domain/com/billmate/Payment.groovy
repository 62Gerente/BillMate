package com.billmate

class Payment {

    Double value
    Date date
    Date createdAt
    Date validationDate
    Boolean validated

    static constraints = {
        value min: 0, nullable: false
        date defaultValue: new Date(), nullable: false
        date validationDate, nullable: true
        validated defaultValue: false, nullable: true
        createdAt nullable: false, defaultValue: new Date(), min: new Date()
    }
}
