package com.billmate

import com.lucastex.grails.fileuploader.UFile

class RegisteredUser extends User {

    String phoneNumber
    String password
    UFile photo

    static constraints = {
        phoneNumber matches: '\\d{9}', unique: true, nullable: true
        photo nullable: true
        password password: true, size: 5..20, blank: false, nullable: false
    }
}
