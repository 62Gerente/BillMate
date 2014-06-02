package com.billmate

import org.h2.engine.Session

class DashboardController extends RestrictedController {
    static layout = "application"
    static allowedMethods = [index: "GET"]

    def beforeInterceptor = [action: this.&checkSession]

    def index() {
        def userDashboard = new RegisteredUserDashboard(registeredUser: authenticatedUser())

        return [user: authenticatedUser(), dashboard: userDashboard]
    }
}
