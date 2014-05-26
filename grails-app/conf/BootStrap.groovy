import com.billmate.User
import com.billmate.RegisteredUser

class BootStrap {
    def init = { servletContext ->
        def user = new RegisteredUser(name: "Bill Mates", email: 'bill@mate.com', password: 'billmate')
        user.secureSave()

        assert User.count() == 1
        assert RegisteredUser.count() == 1
    }

    def destroy = {
    }
}
