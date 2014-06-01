package com.billmate

class DashboardController extends RestrictedController {
    static layout = "application"
    static allowedMethods = [index: "GET"]

    def beforeInterceptor = [action: this.&checkSession]

    def index() {
        RegisteredUser user = authenticatedUser()
        Set<Action> action = user.getUser().getReferencedActions()
        int contador = user.countNonReadNotifications()
        def userDashboard = new RegisteredUserDashboard(user: user, actions: action, numero_notificacoes: contador)
        return [user: user, dashboard: userDashboard]
    }
}