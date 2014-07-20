package com.billmate

class DashboardController extends RestrictedController {
    static layout = "application"
    static allowedMethods = [user: "GET"]

    def beforeInterceptor = [action: this.&checkSession]

    def user() {
        def breadcrumb = [
                [name: message(code: "com.billmate.sidebar.dashboard")]
        ]
        def userDashboard = new RegisteredUserDashboard(registeredUser: authenticatedUser())

        return [breadcrumb: breadcrumb, user: authenticatedUser(), dashboard: userDashboard, active: 0]
    }

    def circle(Long id) {
        def circle = Circle.findById(id)
        def breadcrumb = [
                [name: circle.getName()]
        ]

        def circleDashboard = new CircleDashboard(registeredUser: authenticatedUser(), circle: circle)

        return [breadcrumb: breadcrumb, user: authenticatedUser(), dashboard: circleDashboard, active: 0]
    }
}
