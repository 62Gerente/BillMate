
class UrlMappings {

	static mappings = {


        "/oauth/authorize"(controller: "Oauth", action: "authorize")
        "/api/$namespace/login"(controller: "Session", action: "save")
        "/api/$namespace/register"(controller: "Register", action: "save")

        //USER
        "/api/$namespace/user/$id/circles"(controller: "User", action: "circles")
        //CIRCLE
        "/api/$namespace/circle/$id"(controller: "Circle", action: "show")
        //HOUSE
        //EXPENSE
        "/api/$namespace/expense"(controller: "Expense", action: "save"){
            action = [POST:"save"]
        }


        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }


        "/"(view:"/index")
        "500"(view:'/error')

	}
}
