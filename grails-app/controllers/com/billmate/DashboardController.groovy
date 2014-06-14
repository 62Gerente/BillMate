package com.billmate

import org.h2.engine.Session

class DashboardController extends RestrictedController {
    static layout = "application"
    static allowedMethods = [user: "GET"]

    def beforeInterceptor = [action: this.&checkSession]

    def user() {
        def userDashboard = new RegisteredUserDashboard(registeredUser: authenticatedUser())

        return [user: authenticatedUser(), dashboard: userDashboard]
    }

    def circle(Long id) {
        def circle = Circle.findById(id)

        def circleDashboard = new CircleDashboard(registeredUser: authenticatedUser(), circle: circle)

        return [user: authenticatedUser(), dashboard: circleDashboard]
    }
}
