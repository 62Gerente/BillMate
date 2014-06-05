class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        readnotification:"/readnotification" {
            controller = "notification"
            action = "makeRead"
        }

        "/"(view:"/index")
        "500"(view:'/error')
	}
}
