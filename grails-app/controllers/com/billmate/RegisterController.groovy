package com.billmate

import org.apache.shiro.crypto.hash.Sha256Hash

class RegisterController {
    static layout = "session"

    def beforeInterceptor = [action: this.&alreadyAuthenticated, except: ['create']]

    def create = {
        if(session.user) {
            flash.message = "com.billmate.session.save.already.authenticated"
            flash.m_args = ["#"]
            flash.m_default = "You are already signed in. <a href='{0}'>Logout?</a>"
        }
    }

    def save = {
    }
}
