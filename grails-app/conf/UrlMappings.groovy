class UrlMappings {

	static mappings = {


        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }


        "/"(controller: "page", action: "landing")
        "500"(view:'/error')

	}
}
