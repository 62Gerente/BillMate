class UrlMappings {

	static mappings = {

        "/$controller/$id/$action(.$format)?"{

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
