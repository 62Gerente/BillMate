import com.billmate.Circle
import com.billmate.Collective
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

        def collective = new Collective(name: 'Futeboladas CeSIUM')
        if(!collective.secureSave()){
            println(collective.getErrors().getAllErrors().toString())
        }

        user.addToCircles(house.circle)
        user.addToCircles(collective.circle)
        if(!user.secureSave()){
            println(user.getErrors().getAllErrors().toString())
        }
    }

    def destroy = {
    }
}
