package com.billmate

class AuthenticationController extends BaseController {
    static layout = "authentication"
    def beforeIntercept = [action: this.&checkUser, except: ['login']]

    def login() {
        if(session.user) {
            flash.message = message(code: "com.billmate.authentication.sigin.already.authenticated", default: "You are already signed in.")
        }
    }

    def doLogin = {
        def user = User.findWhere(email: params['email'], password: params['password'])

        if (user) {
            session.user = user
            flash.message = message(code: "com.billmate.authentication.sigin.success", default: "Signed in successfully")
            redirect(uri:'/')
        } else {
            flash.error = message(code: "com.billmate.authentication.sigin.failure", default: "Invalid e-mail or password.")
            redirect(controller: 'authentication', action: 'login')
        }
    }

    def checkUser() {
        if(session.user) {
            flash.error = message(code: "com.billmate.authentication.sigin.already.authenticated", default: "You are already signed in.")
            redirect(controller: 'user', action: 'login')
        }
    }
}
