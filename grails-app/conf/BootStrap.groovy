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
            def signUpUser = new RegisteredUser(name: 'Bill Mates', email: 'bill@mate.com', password: '123456')
            def signUpAction = new Action(actionType: ActionType.findWhere(type: ActionTypeEnum.signUp.toString()), actor: signUpUser)
            signUpUser.secureSave(signUpAction)

            signUpUser = new RegisteredUser(name: 'Pedro Leite', email: 'pmcleite@gmail.com', password: '123456')
            signUpAction = new Action(actionType: ActionType.findWhere(type: ActionTypeEnum.signUp.toString()), actor: signUpUser)
            signUpUser.secureSave(signUpAction)

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
    }

    def destroy = {
    }
}
