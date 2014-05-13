package com.billmate

class ReferredUser  {
    static belongsTo = User

    User user

    static constraints = {
        user nullable: false
    }
}
