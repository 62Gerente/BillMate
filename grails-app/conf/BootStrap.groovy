import com.billmate.Action
import com.billmate.ActionType
import com.billmate.ActionTypeEnum
import com.billmate.CircleType
import com.billmate.Collective
import com.billmate.CustomExpenseType
import com.billmate.Debt
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

        if(ActionType.count() == 0){
            def addUserCircle = new ActionType(type: ActionTypeEnum.addUserCircle.toString(), icon: ActionTypeEnum.addUserCircle.getIcon(), cssClass: ActionTypeEnum.addUserCircle.getCssClass())
            addUserCircle.save()

            def addExpenseCircle = new ActionType(type: ActionTypeEnum.addExpenseCircle.toString(), icon: ActionTypeEnum.addExpenseCircle.getIcon(), cssClass: ActionTypeEnum.addExpenseCircle.getCssClass())
            addExpenseCircle.save()

            def addRegularExpenseCircle = new ActionType(type: ActionTypeEnum.addRegularExpenseCircle.toString(), icon: ActionTypeEnum.addRegularExpenseCircle.getIcon(), cssClass: ActionTypeEnum.addRegularExpenseCircle.getCssClass())
            addRegularExpenseCircle.save()

            def addPaymentExpense = new ActionType(type: ActionTypeEnum.addPaymentExpense.toString(), icon: ActionTypeEnum.addPaymentExpense.getIcon(), cssClass: ActionTypeEnum.addPaymentExpense.getCssClass())
            addPaymentExpense.save()

            def addHouse = new ActionType(type: ActionTypeEnum.addHouse.toString(), icon: ActionTypeEnum.addHouse.getIcon(), cssClass: ActionTypeEnum.addHouse.getCssClass())
            addHouse.save()

            def signUp = new ActionType(type: ActionTypeEnum.signUp.toString(), icon: ActionTypeEnum.signUp.getIcon(), cssClass: ActionTypeEnum.signUp.getCssClass())
            signUp.save()

            def addCollective = new ActionType(type: ActionTypeEnum.addCollective.toString(), icon: ActionTypeEnum.addCollective.getIcon(), cssClass: ActionTypeEnum.addCollective.getCssClass())
            addCollective.save()

            def addedToCircle = new ActionType(type: ActionTypeEnum.addedToCircle.toString(), icon: ActionTypeEnum.addedToCircle.getIcon(), cssClass: ActionTypeEnum.addedToCircle.getCssClass())
            addedToCircle.save()

            def receivedPaymentExpense = new ActionType(type: ActionTypeEnum.receivedPaymentExpense.toString(), icon: ActionTypeEnum.receivedPaymentExpense.getIcon(), cssClass: ActionTypeEnum.receivedPaymentExpense.getCssClass())
            receivedPaymentExpense.save()

            def removedFromCircle = new ActionType(type: ActionTypeEnum.removedFromCircle.toString(), icon: ActionTypeEnum.removedFromCircle.getIcon(), cssClass: ActionTypeEnum.removedFromCircle.getCssClass())
            removedFromCircle.save()

            def removeUserCircle = new ActionType(type: ActionTypeEnum.removedUserCircle.toString(), icon: ActionTypeEnum.removedUserCircle.getIcon(), cssClass: ActionTypeEnum.removedUserCircle.getCssClass())
            removeUserCircle.save()
        }

        if(User.count() == 0){
            def signUpUser = new RegisteredUser(name: 'Bill Mates', email: 'bill@mate.com', password: 'billmate')
            def signUpAction = new Action(actionType: ActionType.findWhere(type: ActionTypeEnum.signUp.toString()), actor: signUpUser)
            signUpUser.secureSave(signUpAction)

            signUpUser = new RegisteredUser(name: 'André Santos', email: 'andreccdr@gmail.com', password: 'asantos')
            signUpAction = new Action(actionType: ActionType.findWhere(type: ActionTypeEnum.signUp.toString()), actor: signUpUser)
            signUpUser.secureSave(signUpAction)

            signUpUser = new RegisteredUser(name: 'Pedro Leite', email: 'pmcleite@gmail.com', password: 'pleite')
            signUpAction = new Action(actionType: ActionType.findWhere(type: ActionTypeEnum.signUp.toString()), actor: signUpUser)
            signUpUser.secureSave(signUpAction)

            signUpUser = new RegisteredUser(name: 'Francisco Neves', email: 'fntneves@gmail.com', password: 'fneves')
            signUpAction = new Action(actionType: ActionType.findWhere(type: ActionTypeEnum.signUp.toString()), actor: signUpUser)
            signUpUser.secureSave(signUpAction)

            signUpUser = new RegisteredUser(name: 'Ricardo Branco', email: '28.ricardobranco@gmail.com', password: 'rbranco')
            signUpAction = new Action(actionType: ActionType.findWhere(type: ActionTypeEnum.signUp.toString()), actor: signUpUser)
            signUpUser.secureSave(signUpAction)

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
            def friday_dinner = new Expense(title: 'Friday Dinner', value: 25.25, expenseType: ExpenseType.findWhere(name: 'Meal'), circle: House.first().getCircle(), responsible: RegisteredUser.findWhere(user: User.findWhere(email: 'pmcleite@gmail.com')), description: 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Obcaecati, incidunt, provident, aut, numquam aspernatur ullam inventore perferendis amet quia animi dignissimos laborum nostrum voluptatibus natus deleniti voluptas veritatis perspiciatis quas?')
            friday_dinner.save()

            def fd_debt = new Debt(user: User.findWhere(email: 'pmcleite@gmail.com'), expense: friday_dinner, value: 5.05, resolvedDate: new Date())
            fd_debt.save()
            new Payment(value: 5.05, debt: fd_debt, user: User.findWhere(email: 'pmcleite@gmail.com'), validationDate: new Date(), isValidated: true).save()

            friday_dinner.addToAssignedUsers(User.findWhere(email: 'andreccdr@gmail.com'))
            new Debt(user: User.findWhere(email: 'andreccdr@gmail.com'), expense: friday_dinner, value: 5.05).save()

            friday_dinner.addToAssignedUsers(User.findWhere(email: 'fntneves@gmail.com'))
            new Debt(user: User.findWhere(email: 'fntneves@gmail.com'), expense: friday_dinner, value: 5.05).save()

            friday_dinner.addToAssignedUsers(User.findWhere(email: 'bill@mate.com'))
            new Debt(user: User.findWhere(email: 'bill@mate.com'), expense: friday_dinner, value: 5.05).save()

            friday_dinner.addToAssignedUsers(User.findWhere(email: '28.ricardobranco@gmail.com'))
            new Debt(user: User.findWhere(email: '28.ricardobranco@gmail.com'), expense: friday_dinner, value: 5.05).save()

            def shopping = new Expense(title: 'Shopping', value: 31.33, expenseType: ExpenseType.findWhere(name: 'Shopping'), circle: House.first().getCircle(), responsible: RegisteredUser.findWhere(user: User.findWhere(email: 'pmcleite@gmail.com')), description: 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Obcaecati, incidunt, provident, aut, numquam aspernatur ullam inventore perferendis amet quia animi dignissimos laborum nostrum voluptatibus natus deleniti voluptas veritatis perspiciatis quas?')
            shopping.save()

            def s_debt = new Debt(user: User.findWhere(email: 'pmcleite@gmail.com'), expense: shopping, value: 7.84, resolvedDate: new Date())
            s_debt.save()
            new Payment(value: 7.84, debt: s_debt, user: User.findWhere(email: 'pmcleite@gmail.com'), validationDate: new Date(), isValidated: true).save()

            shopping.addToAssignedUsers(User.findWhere(email: 'andreccdr@gmail.com'))
            new Debt(user: User.findWhere(email: 'andreccdr@gmail.com'), expense: shopping, value: 7.83).save()

            shopping.addToAssignedUsers(User.findWhere(email: 'fntneves@gmail.com'))
            new Debt(user: User.findWhere(email: 'fntneves@gmail.com'), expense: shopping, value: 7.83).save()

            shopping.addToAssignedUsers(User.findWhere(email: '28.ricardobranco@gmail.com'))
            new Debt(user: User.findWhere(email: '28.ricardobranco@gmail.com'), expense: shopping, value: 7.83).save()

            def maid = new Expense(title: 'Maid', value: 15.00, expenseType: ExpenseType.findWhere(name: 'Maid'), circle: House.first().getCircle(), responsible: RegisteredUser.findWhere(user: User.findWhere(email: 'andreccdr@gmail.com')), description: 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Obcaecati, incidunt, provident, aut, numquam aspernatur ullam inventore perferendis amet quia animi dignissimos laborum nostrum voluptatibus natus deleniti voluptas veritatis perspiciatis quas?')
            maid.save()

            def m_debt = new Debt(user: User.findWhere(email: 'andreccdr@gmail.com'), expense: maid, value: 5, resolvedDate: new Date())
            m_debt.save()
            new Payment(value: 5, debt: m_debt, user: User.findWhere(email: 'andreccdr@gmail.com'), validationDate: new Date(), isValidated: true).save()

            maid.addToAssignedUsers(User.findWhere(email: 'fntneves@gmail.com'))
            def fnDebt = new Debt(user: User.findWhere(email: 'fntneves@gmail.com'), expense: maid, value: 5)
            fnDebt.save()

            maid.addToAssignedUsers(User.findWhere(email: '28.ricardobranco@gmail.com'))
            def rbDebt = new Debt(user: User.findWhere(email: '28.ricardobranco@gmail.com'), expense: maid, value: 5)
            rbDebt.save()

            def rental = new Expense(title: 'Soccer Field Rental', value: 95.00, expenseType: ExpenseType.findWhere(name: 'Field Rental'), circle: Collective.first().getCircle(), responsible: RegisteredUser.findWhere(user: User.findWhere(email: '28.ricardobranco@gmail.com')), description: 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Obcaecati, incidunt, provident, aut, numquam aspernatur ullam inventore perferendis amet quia animi dignissimos laborum nostrum voluptatibus natus deleniti voluptas veritatis perspiciatis quas?')
            rental.save()

            def r_debt = new Debt(user: User.findWhere(email: '28.ricardobranco@gmail.com'), expense: rental, value: 19, resolvedDate: new Date())
            r_debt.save()
            new Payment(value: 19, debt: r_debt, user: User.findWhere(email: '28.ricardobranco@gmail.com'), validationDate: new Date(), isValidated: true).save()

            rental.addToAssignedUsers(User.findWhere(email: 'bill@mate.com'))
            new Debt(user: User.findWhere(email: 'bill@mate.com'), expense: rental, value: 19).save()

            rental.addToAssignedUsers(User.findWhere(email: 'andreccdr@gmail.com'))
            new Debt(user: User.findWhere(email: 'andreccdr@gmail.com'), expense: rental, value: 19).save()

            rental.addToAssignedUsers(User.findWhere(email: 'fntneves@gmail.com'))
            new Debt(user: User.findWhere(email: 'fntneves@gmail.com'), expense: rental, value: 19).save()

            rental.addToAssignedUsers(User.findWhere(email: 'pmcleite@gmail.com'))
            new Debt(user: User.findWhere(email: 'pmcleite@gmail.com'), expense: rental, value: 19).save()

            def fnMaidPayment = new Payment(user: User.findWhere(email: 'fntneves@gmail.com'), debt: fnDebt, value: 3.50)
            fnMaidPayment.save()

            def rbMaidPayment = new Payment(user: User.findWhere(email: '28.ricardobranco@gmail.com'), debt: rbDebt, value: 1.00)
            rbMaidPayment.save()

            def fnMaidPaymentAction = new Action(actionType: ActionType.findWhere(type: ActionTypeEnum.addPaymentExpense.toString()), actor: RegisteredUser.findWhere(user: User.findWhere(email: 'fntneves@gmail.com')), user: User.findWhere(email: 'andreccdr@gmail.com'), expense: maid, circle: House.first().getCircle(), payment: fnMaidPayment)
            fnMaidPaymentAction.save()
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

        if(RegularExpense.count() == 0 ){
            def electricity = new RegularExpense(title: 'Electricity', circle: House.first().getCircle(), responsible: RegisteredUser.findWhere(user: User.findWhere(email: 'fntneves@gmail.com')), expenseType: ExpenseType.findWhere(name: 'Electricity'), receptionBeginDate: new Date(), value: 20, description: 'Descrição da despesa')
            electricity.save()

            electricity.addToAssignedUsers(User.findWhere(email: 'andreccdr@gmail.com'))
            electricity.addToAssignedUsers(User.findWhere(email: 'pmcleite@gmail.com'))

            def water = new RegularExpense(title: 'Water', circle: House.first().getCircle(), responsible: RegisteredUser.findWhere(user: User.findWhere(email: 'fntneves@gmail.com')), expenseType: ExpenseType.findWhere(name: 'Water'), receptionBeginDate: new Date(), value:30)
            water.save()

            water.addToAssignedUsers(User.findWhere(email: 'andreccdr@gmail.com'))
            water.addToAssignedUsers(User.findWhere(email: 'pmcleite@gmail.com'))

            def internet = new RegularExpense(title: 'Internet', circle: House.first().getCircle(), responsible: RegisteredUser.findWhere(user: User.findWhere(email: 'fntneves@gmail.com')), expenseType: ExpenseType.findWhere(name: 'Internet'), receptionBeginDate: new Date(), value:40)
            internet.save()

            internet.addToAssignedUsers(User.findWhere(email: 'andreccdr@gmail.com'))
            internet.addToAssignedUsers(User.findWhere(email: 'pmcleite@gmail.com'))
        }

        if(Action.count() <= RegisteredUser.count()){
            def addUserCircle = new Action(actionType: ActionType.findWhere(type: 'addUserCircle'), actor: RegisteredUser.findWhere(user: User.findWhere(email: 'pmcleite@gmail.com')), user: User.findWhere(email: 'andreccdr@gmail.com'), circle: House.first().getCircle())
            addUserCircle.save()

            def addExpenseCircle = new Action(actionType: ActionType.findWhere(type: 'addExpenseCircle'), actor: RegisteredUser.findWhere(user: User.findWhere(email: 'andreccdr@gmail.com')), circle: House.first().getCircle(), expense: Expense.findWhere(title: 'Maid'))
            addExpenseCircle.save()

            def addRegularExpenseCircle = new Action(actionType: ActionType.findWhere(type: 'addRegularExpenseCircle'), actor: RegisteredUser.findWhere(user: User.findWhere(email: 'fntneves@gmail.com')), circle: House.first().getCircle(), regularExpense: RegularExpense.findWhere(title: 'Electricity'))
            addRegularExpenseCircle.save()

            def addPaymentExpense = new Action(actionType: ActionType.findWhere(type: 'addPaymentExpense'), actor: RegisteredUser.findWhere(user: User.findWhere(email: '28.ricardobranco@gmail.com')), user: Expense.findWhere(title: 'Maid').getResponsible().getUser(), payment: Payment.first(), circle: House.first().getCircle(), expense: Expense.findWhere(title: 'Maid'))
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

        if(Debt.count() == 0){
            def expense1 = Expense.findByTitle("Friday Dinner")
            def expense2 = Expense.findByTitle("Shopping")
            def expense3 = Expense.findByTitle("Maid")
            def user1 = User.findByEmail("andreccdr@gmail.com")
            def user2 = User.findByEmail("pmcleite@gmail.com")

            new Debt(expense: expense1, user: user1, percentage: 20, value: 10).save()
            new Debt(expense: expense2, user: user1, percentage: 20, value: 20).save()
            new Debt(expense: expense3, user: user1, percentage: 20, value: 30).save()
        }
    }

    def destroy = {
    }
}
