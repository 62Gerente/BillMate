package com.billmate

class CustomizedDebt {

    Integer percentage
    Date createdAt

    static constraints = {
        percentage min: 0, nullable: false
        createdAt nullable: false, defaultValue: new Date(), min: new Date()
    }
}
