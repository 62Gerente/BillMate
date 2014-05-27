package com.billmate

class RegisteredUserDashboard {
    static mapWith = "none"

    RegisteredUser user

    public Set<Circle> getHouses(){
        return user.getCircles(type: 'house')
    }

    public Set<Circle> getGroups(){
        return user.getCircles(type: 'collective')
    }
}
