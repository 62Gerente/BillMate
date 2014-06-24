import com.billmate.Action
import com.billmate.ActionType
import com.billmate.CircleType
import com.billmate.Collective
import com.billmate.CustomExpenseType
import com.billmate.DefaultExpenseType
import com.billmate.ExpenseType
import com.billmate.House
import com.billmate.Expense
import com.billmate.Payment
import com.billmate.SystemNotification
import com.billmate.RegularExpense
import com.billmate.ReferredUser
import com.billmate.User
import com.billmate.RegisteredUser

class BootStrap {
    def init = { servletContext ->

        if(User.count() == 0){
            new RegisteredUser(name: 'Bill Mates', email: 'bill@mate.com', password: 'billmate').secureSave()
            new RegisteredUser(name: 'André Santos', email: 'andreccdr@gmail.com', password: 'asantos').secureSave()
            new RegisteredUser(name: 'Pedro Leite', email: 'pmcleite@gmail.com', password: 'pleite').secureSave()
            new RegisteredUser(name: 'Francisco Neves', email: 'fntneves@gmail.com', password: 'fneves').secureSave()
            new RegisteredUser(name: 'Ricardo Branco', email: '28.ricardobranco@gmail.com', password: 'rbranco').secureSave()
            new ReferredUser(name: 'Sérgio Almeida', email: 'sergio2malmeida@gmail.com').secureSave()
        }

        if(House.count() == 0){
            def house = new House(name: 'BillMate House', description: 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Obcaecati, incidunt, provident, aut, numquam aspernatur ullam inventore perferendis amet quia animi dignissimos laborum nostrum voluptatibus natus deleniti voluptas veritatis perspiciatis quas?')
            house.secureSave()

            User.findWhere(email: 'bill@mate.com').addToCircles(house.circle)
            User.findWhere(email: 'andreccdr@gmail.com').addToCircles(house.circle)
            User.findWhere(email: 'pmcleite@gmail.com').addToCircles(house.circle)
        }

        if(Collective.count() == 0){
            def collective = new Collective(name: 'CeSIUM Soccer Games', description: 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Obcaecati, incidunt, provident, aut, numquam aspernatur ullam inventore perferendis amet quia animi dignissimos laborum nostrum voluptatibus natus deleniti voluptas veritatis perspiciatis quas?')
            collective.secureSave()

            User.findWhere(email: 'bill@mate.com').addToCircles(collective.circle)
            User.findWhere(email: 'andreccdr@gmail.com').addToCircles(collective.circle)
            User.findWhere(email: 'pmcleite@gmail.com').addToCircles(collective.circle)
            User.findWhere(email: 'fntneves@gmail.com').addToCircles(collective.circle)
            User.findWhere(email: '28.ricardobranco@gmail.com').addToCircles(collective.circle)
        }

        if(DefaultExpenseType.count() == 0){
            def shoppingType = new DefaultExpenseType(name: 'Shopping', cssClass: "fa fa-shopping-cart")
            shoppingType.secureSave()

            def mealType = new DefaultExpenseType(name: 'Meal', cssClass: "fa fa-cutlery")
            mealType.secureSave()

            def electricityType = new DefaultExpenseType(name: 'Electricity', cssClass: "fa fa-bolt")
            electricityType.secureSave()

            def waterType = new DefaultExpenseType(name: 'Water', cssClass: "fa fa-tint")
            waterType.secureSave()

            def internetType = new DefaultExpenseType(name: 'Internet', cssClass: "fa fa-globe")
            internetType.secureSave()
        }

        if(CustomExpenseType.count() == 0){
            def maidType = new CustomExpenseType(name: 'Maid')
            maidType.secureSave()

            def fieldRentalType = new CustomExpenseType(name: 'Field Rental')
            fieldRentalType.secureSave()
        }

        if(Expense.count == 0) {
            def friday_dinner = new Expense(title: 'Friday Dinner', value: 25.25, expenseType: ExpenseType.findWhere(name: 'Meal'), circle: House.first().getCircle(), responsible: RegisteredUser.findWhere(user: User.findWhere(email: 'pmcleite@gmail.com')))
            friday_dinner.save()

            friday_dinner.addToAssignedUsers(User.findWhere(email: 'andreccdr@gmail.com'))
            friday_dinner.addToAssignedUsers(User.findWhere(email: 'fntneves@gmail.com'))
            friday_dinner.addToAssignedUsers(User.findWhere(email: 'bill@mate.com'))
            friday_dinner.addToAssignedUsers(User.findWhere(email: '28.ricardobranco@gmail.com'))

            def shopping = new Expense(title: 'Shopping', value: 31.33, expenseType: ExpenseType.findWhere(name: 'Shopping'), circle: House.first().getCircle(), responsible: RegisteredUser.findWhere(user: User.findWhere(email: 'pmcleite@gmail.com')))
            shopping.save()

            shopping.addToAssignedUsers(User.findWhere(email: 'andreccdr@gmail.com'))
            shopping.addToAssignedUsers(User.findWhere(email: 'fntneves@gmail.com'))
            shopping.addToAssignedUsers(User.findWhere(email: '28.ricardobranco@gmail.com'))

            def maid = new Expense(title: 'Maid', value: 15.00, expenseType: ExpenseType.findWhere(name: 'Maid'), circle: House.first().getCircle(), responsible: RegisteredUser.findWhere(user: User.findWhere(email: 'andreccdr@gmail.com')))
            maid.save()

            maid.addToAssignedUsers(User.findWhere(email: 'fntneves@gmail.com'))
            maid.addToAssignedUsers(User.findWhere(email: '28.ricardobranco@gmail.com'))

            def rental = new Expense(title: 'Soccer Field Rental', value: 95.00, expenseType: ExpenseType.findWhere(name: 'Field Rental'), circle: Collective.first().getCircle(), responsible: RegisteredUser.findWhere(user: User.findWhere(email: '28.ricardobranco@gmail.com')))
            rental.save()

            rental.addToAssignedUsers(User.findWhere(email: 'bill@mate.com'))
            rental.addToAssignedUsers(User.findWhere(email: 'andreccdr@gmail.com'))
            rental.addToAssignedUsers(User.findWhere(email: 'fntneves@gmail.com'))
            rental.addToAssignedUsers(User.findWhere(email: 'pmcleite@gmail.com'))
        }

        if(CircleType.count() == 0){
            def circleType = new CircleType(name: 'house')
            circleType.save()
            def defaultExpenseType = DefaultExpenseType.findByExpenseType(ExpenseType.findByName('Shopping'))
            circleType.addToExpenseTypes(defaultExpenseType.getExpenseType())
            defaultExpenseType = DefaultExpenseType.findByExpenseType(ExpenseType.findByName('Meal'))
            circleType.addToExpenseTypes(defaultExpenseType.getExpenseType())
            def customExpenseType = CustomExpenseType.findByExpenseType(ExpenseType.findByName('Maid'))
            circleType.addToExpenseTypes(customExpenseType.getExpenseType())

            circleType = new CircleType(name: 'collective')
            circleType.save()
            defaultExpenseType = DefaultExpenseType.findByExpenseType(ExpenseType.findByName('Meal'))
            circleType.addToExpenseTypes(defaultExpenseType.getExpenseType())
            customExpenseType = CustomExpenseType.findByExpenseType(ExpenseType.findByName('Maid'))
            circleType.addToExpenseTypes(customExpenseType.getExpenseType())
        }

        if(Payment.count() == 0){
            def fnMaidPayment = new Payment(user: User.findWhere(email: 'fntneves@gmail.com'), expense: Expense.findWhere(title: 'Maid'), value: 3.50)
            fnMaidPayment.save()
            def rbMaidPayment = new Payment(user: User.findWhere(email: '28.ricardobranco@gmail.com'), expense: Expense.findWhere(title: 'Maid'), value: 1.00)
            rbMaidPayment.save()
        }

        if(RegularExpense.count() == 0 ){
            def electricity = new RegularExpense(title: 'Electricity', circle: House.first().getCircle(), responsible: RegisteredUser.findWhere(user: User.findWhere(email: 'fntneves@gmail.com')), expenseType: ExpenseType.findWhere(name: 'Electricity'), receptionBeginDate: new Date())
            electricity.save()

            electricity.addToAssignedUsers(User.findWhere(email: 'andreccdr@gmail.com'))
            electricity.addToAssignedUsers(User.findWhere(email: 'pmcleite@gmail.com'))

            def water = new RegularExpense(title: 'Water', circle: House.first().getCircle(), responsible: RegisteredUser.findWhere(user: User.findWhere(email: 'fntneves@gmail.com')), expenseType: ExpenseType.findWhere(name: 'Water'), receptionBeginDate: new Date())
            water.save()

            water.addToAssignedUsers(User.findWhere(email: 'andreccdr@gmail.com'))
            water.addToAssignedUsers(User.findWhere(email: 'pmcleite@gmail.com'))

            def internet = new RegularExpense(title: 'Internet', circle: House.first().getCircle(), responsible: RegisteredUser.findWhere(user: User.findWhere(email: 'fntneves@gmail.com')), expenseType: ExpenseType.findWhere(name: 'Internet'), receptionBeginDate: new Date())
            internet.save()

            internet.addToAssignedUsers(User.findWhere(email: 'andreccdr@gmail.com'))
            internet.addToAssignedUsers(User.findWhere(email: 'pmcleite@gmail.com'))
        }

        if(ActionType.count() == 0){
            def addUserCircle = new ActionType(type: 'addUserCircle')
            addUserCircle.save()

            def addExpenseCircle = new ActionType(type: 'addExpenseCircle')
            addExpenseCircle.save()

            def addRegularExpenseCircle = new ActionType(type: 'addRegularExpenseCircle')
            addRegularExpenseCircle.save()

            def addPaymentExpense = new ActionType(type: 'addPaymentExpense')
            addPaymentExpense.save()

            def addCircle = new ActionType(type: 'addCircle')
            addCircle.save()
        }

        if(Action.count() == 0){
            def addUserCircle = new Action(actionType: ActionType.findWhere(type: 'addUserCircle'), actor: RegisteredUser.findWhere(user: User.findWhere(email: 'pmcleite@gmail.com')), user: User.findWhere(email: 'andreccdr@gmail.com'), circle: House.first().getCircle())
            addUserCircle.save()

            def addExpenseCircle = new Action(actionType: ActionType.findWhere(type: 'addExpenseCircle'), actor: RegisteredUser.findWhere(user: User.findWhere(email: 'andreccdr@gmail.com')), circle: House.first().getCircle(), expense: Expense.findWhere(title: 'Maid'))
            addExpenseCircle.save()

            def addRegularExpenseCircle = new Action(actionType: ActionType.findWhere(type: 'addRegularExpenseCircle'), actor: RegisteredUser.findWhere(user: User.findWhere(email: 'fntneves@gmail.com')), circle: House.first().getCircle(), regularExpense: RegularExpense.findWhere(title: 'Electricity'))
            addRegularExpenseCircle.save()

            def addPaymentExpense = new Action(actionType: ActionType.findWhere(type: 'addPaymentExpense'), actor: RegisteredUser.findWhere(user: User.findWhere(email: '28.ricardobranco@gmail.com')), payment: Payment.first(), circle: House.first().getCircle(), expense: Expense.findWhere(title: 'Maid'))
            addPaymentExpense.save()

            def addCircle = new Action(actionType: ActionType.findWhere(type: 'addCircle'), actor: RegisteredUser.findWhere(user: User.findWhere(email: 'pmcleite@gmail.com')), circle: Collective.first().getCircle())
            addCircle.save()
        }

        if(SystemNotification.count() == 0){
            def systemNotification = new SystemNotification(action: Action.first(), registeredUser: RegisteredUser.findWhere(user: User.findWhere(email: 'andreccdr@gmail.com')))
            systemNotification.secureSave()
            systemNotification = new SystemNotification(action: Action.first(), registeredUser: RegisteredUser.findWhere(user: User.findWhere(email: 'andreccdr@gmail.com')))
            systemNotification.secureSave()
            systemNotification = new SystemNotification(action: Action.first(), registeredUser: RegisteredUser.findWhere(user: User.findWhere(email: 'pmcleite@gmail.com')))
            systemNotification.secureSave()
        }
    }

    def destroy = {
    }
}
