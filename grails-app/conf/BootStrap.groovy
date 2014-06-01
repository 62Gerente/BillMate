import com.billmate.Action
import com.billmate.ActionType
import com.billmate.Collective
import com.billmate.House
import com.billmate.Notification
import com.billmate.SystemNotification
import com.billmate.User
import com.billmate.RegisteredUser

class BootStrap {
    def init = { servletContext ->

        def user1
        def user2
        if(User.count() == 0){
            user1 = new RegisteredUser(name: 'Bill Mates', email: 'bill@mate.com', password: 'billmate')
            user1.secureSave()
            user2 = new RegisteredUser(name: 'Pedro Leite', email: 'pedro@mate.com', password: 'billmate')
            user2.secureSave()
        }

        def house
        if(House.count() == 0){
            house = new House(name: 'Casa de Braga wow')
            house.secureSave()

            User.findWhere(email: 'bill@mate.com').addToCircles(house.circle)
        }

        if(Collective.count() == 0){
            def collective = new Collective(name: 'Futeboladas CeSIUM wow')
            collective.secureSave()

            User.findWhere(email: 'bill@mate.com').addToCircles(collective.circle)
        }

        def actionType
        if(ActionType.count() == 0){
            actionType = new ActionType(type: 'com.billmate.notification.add_user_house')
            actionType.secureSave()
        }

        def action
        if(Action.count() == 0){
            action = new Action(actionType: actionType, actionDate: new Date(), actor: user2, user: user1.getUser(), circle: house.circle)
            action.secureSave()
        }

        def notification
        if(Notification.count() == 0){
            //notification.save(flush: true, failOnError: true)
            //def systemNotification = new SystemNotification(action: action, registeredUser: user1, notification: notification, is_read: false)
            //systemNotification.save(flush: true, failOnError: true)
            //notification.setSystemNotification(systemNotification)
            //notification.save(flush: true, failOnError: true)

            def systemNotification = new SystemNotification(action: action, registeredUser: user1, is_read: false)
            notification = new Notification(systemNotification: systemNotification)
            systemNotification.setNotification(notification)
            notification.setSystemNotification(systemNotification)
            notification.secureSave()
        }
    }

    def destroy = {
    }
}