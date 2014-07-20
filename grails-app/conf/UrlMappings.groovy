
class UrlMappings {

	static mappings = {


        "/oauth/authorize"(controller: "Oauth", action: "authorize")

        "/api/$namespace/login"(controller: "Session", action: "save")
        "/api/$namespace/register"(controller: "Register", action: "save")



        "/api/$namespace/$controller/$id?"{
            action = [GET:"show", PUT:"update", DELETE:"delete", POST:"save"]
        }

        //USER
        "/api/$namespace/user/$id/circles"(controller: "User", action: "circles")
        "/api/$namespace/user/$id/houses"(controller: "User", action: "houses")
        "/api/$namespace/user/$id/collectives"(controller: "User", action: "houses")

        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }


        "/"(view:"/index")
        "500"(view:'/error')

	}
}
