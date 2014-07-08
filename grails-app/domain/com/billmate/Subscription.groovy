package com.billmate

import com.nanlabs.grails.plugin.logicaldelete.LogicalDelete

@LogicalDelete
class Subscription {

    String email
    Date createdAt = new Date()

    static constraints = {
        email email: true, unique: true, nullable: false
        createdAt nullable: false
    }
}
