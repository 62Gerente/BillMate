package com.billmate

class RegisteredUser extends User {

    String phone;
    String password;
    byte[] photo;

    static constraints = {
        phone matches: '\\d{9}', nullable: true
        photo maxSize: 1024 * 1024 * 2, nullable: true
        password password: true, size: 5..20, blank: false, nullable: false
    }
}
