package com.billmate

class PageController extends BaseController {
    static layout="landing"
    static allowedMethods = [create: "GET"]

    def landing = {
        if(session.user){
            return redirect(controller: 'dashboard', action: 'user')
        }
    }
}
