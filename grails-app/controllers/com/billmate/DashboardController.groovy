package com.billmate

class DashboardController extends RestrictedController {
    static layout = "application"
    static allowedMethods = [index: "GET"]

    def beforeInterceptor = [action: this.&checkSession]

    def index() {
        RegisteredUser user = authenticatedUser()
        Set<Action> action = user.getUser().getReferencedActions()
        def userDashboard = new RegisteredUserDashboard(user: user, actions: action )
        return [user: user, dashboard: userDashboard]
    }
}