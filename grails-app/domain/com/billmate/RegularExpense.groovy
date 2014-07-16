package com.billmate

import groovy.time.TimeCategory

class RegularExpense {
    static belongsTo = [DirectDebit, Circle, RegisteredUser, ExpenseType]
    static hasMany = [debts: Debt, actions: Action, assignedUsers: User, expenses: Expense]

    DirectDebit directDebit
    RegisteredUser responsible
    ExpenseType expenseType
    Circle circle

    String title
    String description
    Double value

    Date beginDate
    Date endDate
    Date paymentDeadline
    Date receptionDeadline

    Date receptionBeginDate = new Date()

    Integer intervalDays = 0
    Integer intervalMonths = 1
    Integer intervalYears = 0

    Boolean isActive = true

    static constraints = {
        responsible nullable: false
        circle nullable: false
        expenseType nullable: false
        directDebit nullable: true

        title nullable: false
        description maxSize: 2000, nullable: true
        value min: 0D, nullable: true

        beginDate nullable: true
        endDate nullable: true
        paymentDeadline nullable: true
        receptionDeadline nullable: true

        receptionBeginDate nullable: false

        intervalDays nullable: false, min: 0
        intervalMonths nullable: false, min: 0
        intervalYears nullable: false, min: 0

        isActive nullable: false
    }

    public String toString() {
        title
    }

    public boolean inReceptionTime(){
        Date date, dateBefore, dateAfter
        use(TimeCategory) {
            date = receptionBeginDate + intervalDays.days + intervalMonths.months + intervalYears.years
            dateBefore = new Date()
            dateAfter = dateBefore + 5.days
        }
        isActive && date >= dateBefore && date <= dateAfter
    }

    public postpone() {
        use(TimeCategory) {
            if(beginDate) beginDate = beginDate + intervalDays.days + intervalMonths.months + intervalYears.years
            if(endDate) endDate = endDate + intervalDays.days + intervalMonths.months + intervalYears.years
            if(paymentDeadline) paymentDeadline = paymentDeadline + intervalDays.days + intervalMonths.months + intervalYears.years
            if(receptionDeadline) receptionDeadline = receptionDeadline + intervalDays.days + intervalMonths.months + intervalYears.years
            if(receptionBeginDate) receptionBeginDate = receptionBeginDate + intervalDays.days + intervalMonths.months + intervalYears.years
        }
    }

    def toJSON(){
        return [name: getTitle(), description: getDescription(), id : getExpenseType().getId(), expenseTypeName: getExpenseType().getName(),
         expenseTypeCssClass: getExpenseType().getCssClass(), value: getValue(), paymentDeadline: BMDate.convertDateFormat(getPaymentDeadline()),
         receptionDeadline: BMDate.convertDateFormat(getReceptionDeadline()), receptionBeginDate: BMDate.convertDateFormat(getReceptionBeginDate()),
         beginDate: BMDate.convertDateFormat(getBeginDate()), endDate: BMDate.convertDateFormat(getEndDate())]
    }

    def create(List<String> idsUsers, List<Double> value){
        boolean result = false;
        int position = 0
        withTransaction {status ->
            try{
                RegularExpense regularExpense = save(flush: true, failOnError: true)
                for(String str : idsUsers){
                    if( Double.parseDouble(value[position]) > 0 ){
                        User user = User.findById(Long.parseLong(str))
                        new Debt(value: value[position], user: user, regularExpense: regularExpense).save()
                        regularExpense.addToAssignedUsers(user)
                    }
                    position++;
                }
                result = true;
            }
            catch(Exception e){
                result = false
                status.setRollbackOnly()
            }
        }
        return result;
    }

    Expense fromRegularExpenseToExpense(Expense expense, RegisteredUser registeredUser, Double value){
        expense.setRegularExpense(this)
        expense.setResponsible(getResponsible())
        expense.setExpenseType(getExpenseType())
        expense.setCircle(getCircle())
        expense.setTitle(getTitle())
        expense.setDescription(getDescription())
        expense.setValue(value)
        expense.setBeginDate(new Date())
        expense.setEndDate(getEndDate())
        expense.setPaymentDeadline(getPaymentDeadline())
        expense.setReceptionDeadline(getReceptionDeadline())
        expense.setCreatedAt(new Date())
        expense.setPaymentDate(getPaymentDeadline())
        expense.setIsDeleted(false)
        expense.secureSave()
        double partialValue = value / getAssignedUsers().size()
        for (User user : getAssignedUsers()){
            Debt debt = new Debt(value: partialValue, user: user, expense: expense).save()
            expense.addToAssignedUsers(user)
            RegisteredUser regUser = user.getRegisteredUser()
            if(regUser && regUser.getId() == registeredUser.getId()) {
                new Payment(user: user, debt: debt, value: partialValue, validationDate: new Date(), isValidated: true).save()
                debt.setResolvedDate(new Date())
            }
        }
        return expense
    }
}
