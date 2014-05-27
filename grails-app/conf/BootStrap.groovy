import com.billmate.Circle
import com.billmate.House
import com.billmate.User
import com.billmate.RegisteredUser

class BootStrap {
    def init = { servletContext ->
        def user = new RegisteredUser(name: 'Bill Mates', email: 'bill@mate.com', password: 'billmate')
        if(!user.secureSave()){
            println(user.getErrors().getAllErrors().toString())
        }

        def house = new House(name: 'Casa de Braga')
        if(!house.secureSave()){
            println(house.getErrors().getAllErrors().toString())
        }

        user.addToCircles(house.circle)
        if(!user.secureSave()){
            println(user.getErrors().getAllErrors().toString())
        }

        assert User.count() == 1
        assert RegisteredUser.count() == 1
        assert House.count() == 1
        assert Circle.count() == 1
    }

    def destroy = {
    }
}
