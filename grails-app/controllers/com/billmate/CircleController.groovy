package com.billmate

class CircleController extends RestrictedController  {

    def beforeInterceptor = [action: this.&checkSession]

    def index() {}

    def createHouse(){
        String houseName = params.houseName
        def expenseType = params.expenseType
        def friendsHome = params.friendsHome
        def houseDescription = params.houseDescription
        RegisteredUser registeredUser = authenticatedUser()
        House house = new House(name: houseName, description: houseDescription)
        house.secureSave()
        house.createHouse(expenseType, friendsHome)
    }
}
