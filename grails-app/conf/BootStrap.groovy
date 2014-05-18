import com.billmate.User
import com.billmate.RegisteredUser

class BootStrap {
    def init = { servletContext ->
        def user = new RegisteredUser(name: "Bill Mates", email: 'bill@mate.com', password: 'billmate')

        if (!user.save(flush: true)) {
            user.errors.allErrors.each {
                println it
            }
        }

        assert User.count() == 1
        assert RegisteredUser.count() == 1
    }

    def destroy = {
    }
}
