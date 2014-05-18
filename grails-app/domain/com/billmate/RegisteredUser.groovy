package com.billmate

import com.lucastex.grails.fileuploader.UFile
import org.apache.shiro.crypto.hash.Sha256Hash
import org.springframework.validation.ObjectError

class RegisteredUser {
    static belongsTo = User
    static hasMany = [responsibleExpenses: Expense, realizedActions: Action, systemNotifications: SystemNotification]

    User user
    UFile photo

    String phoneNumber
    String password

    static constraints = {
        user nullable: false
        photo nullable: true

        phoneNumber matches: '\\d{9}', unique: true, nullable: true
        password password: true, blank: false, nullable: false
    }

    public RegisteredUser() {
        super()
        user = new User()
    }

    public RegisteredUser(Map map) {
        super(map)
        user = new User(map)
    }

    def beforeInsert() {
        encodePassword()
        user.save()
    }

    def beforeUpdate() {
        if (isDirty('password')) {
            encodePassword()
        }
        user.save()
    }

    def beforeValidate() {
        user.validate()
        user.errors.getAllErrors().each {
            ObjectError objectError = (ObjectError) it
            this.errors.reject(objectError.getCode(), objectError.toString())
        }
    }

    protected void encodePassword() {
        password = new Sha256Hash(password).toHex()
    }

    public void setName(String name){
        user.setName(name)
    }

    public void setEmail(String email){
        user.setEmail(email)
    }
}
