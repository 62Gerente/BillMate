import com.billmate.Collective
import com.billmate.CustomExpenseType
import com.billmate.DefaultExpenseType
import com.billmate.Expense
import com.billmate.ExpenseType
import com.billmate.House
import com.billmate.OccasionalExpense
import com.billmate.Payment
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
        }

        if(House.count() == 0){
            def house = new House(name: 'BillMate House')
            house.secureSave()

            User.findWhere(email: 'bill@mate.com').addToCircles(house.circle)
            User.findWhere(email: 'andreccdr@gmail.com').addToCircles(house.circle)
            User.findWhere(email: 'pmcleite@gmail.com').addToCircles(house.circle)
            User.findWhere(email: 'fntneves@gmail.com').addToCircles(house.circle)
            User.findWhere(email: '28.ricardobranco@gmail.com').addToCircles(house.circle)
        }

        if(Collective.count() == 0){
            def collective = new Collective(name: 'CeSIUM Soccer Games')
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
        }

        if(CustomExpenseType.count() == 0){
            def maidType = new CustomExpenseType(name: 'Maid')
            maidType.secureSave()

            def fieldRentalType = new CustomExpenseType(name: 'Field Rental')
            fieldRentalType.secureSave()
        }

        if(OccasionalExpense.count == 0){
            def friday_dinner = new OccasionalExpense(title: 'Friday Dinner', value: 25.25, expenseType: ExpenseType.findWhere(name: 'Meal'), circle: House.first().getCircle(), responsible: RegisteredUser.findWhere(user: User.findWhere(email: 'pmcleite@gmail.com')))
            friday_dinner.secureSave()

            friday_dinner.addToAssignedUsers(User.findWhere(email: 'andreccdr@gmail.com'))
            friday_dinner.addToAssignedUsers(User.findWhere(email: 'fntneves@gmail.com'))
            friday_dinner.addToAssignedUsers(User.findWhere(email: 'bill@mate.com'))
            friday_dinner.addToAssignedUsers(User.findWhere(email: '28.ricardobranco@gmail.com'))

            def shopping = new OccasionalExpense(title: 'Shopping', value: 31.33, expenseType: ExpenseType.findWhere(name: 'Shopping'), circle: House.first().getCircle(), responsible: RegisteredUser.findWhere(user: User.findWhere(email: 'pmcleite@gmail.com')))
            shopping.secureSave()

            shopping.addToAssignedUsers(User.findWhere(email: 'andreccdr@gmail.com'))
            shopping.addToAssignedUsers(User.findWhere(email: 'fntneves@gmail.com'))
            shopping.addToAssignedUsers(User.findWhere(email: '28.ricardobranco@gmail.com'))

            def maid = new OccasionalExpense(title: 'Maid', value: 15.00, expenseType: ExpenseType.findWhere(name: 'Maid'), circle: House.first().getCircle(), responsible: RegisteredUser.findWhere(user: User.findWhere(email: 'andreccdr@gmail.com')))
            maid.secureSave()

            maid.addToAssignedUsers(User.findWhere(email: 'fntneves@gmail.com'))
            maid.addToAssignedUsers(User.findWhere(email: '28.ricardobranco@gmail.com'))

            def rental = new OccasionalExpense(title: 'Soccer Field Rental', value: 95.00, expenseType: ExpenseType.findWhere(name: 'Field Rental'), circle: Collective.first().getCircle(), responsible: RegisteredUser.findWhere(user: User.findWhere(email: '28.ricardobranco@gmail.com')))
            rental.secureSave()

            rental.addToAssignedUsers(User.findWhere(email: 'bill@mate.com'))
            rental.addToAssignedUsers(User.findWhere(email: 'andreccdr@gmail.com'))
            rental.addToAssignedUsers(User.findWhere(email: 'fntneves@gmail.com'))
            rental.addToAssignedUsers(User.findWhere(email: 'pmcleite@gmail.com'))
        }

        if(Payment.count() == 0){
            def fn_maid_payment = new Payment(user: User.findWhere(email: 'fntneves@gmail.com'), expense: Expense.findWhere(title: 'Maid'), value: 3.50)
            fn_maid_payment.save()
            def rb_maid_payment = new Payment(user: User.findWhere(email: '28.ricardobranco@gmail.com'), expense: Expense.findWhere(title: 'Maid'), value: 1.00)
            rb_maid_payment.save()
        }
    }

    def destroy = {
    }
}
