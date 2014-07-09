
class UrlMappings {

	static mappings = {

        "/api/$namespace/$controller/$action"{
            constraints {}
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
