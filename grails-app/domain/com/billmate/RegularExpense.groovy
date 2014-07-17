package com.billmate

import groovy.time.TimeCategory
import org.springframework.validation.ObjectError

class RegularExpense {
    static belongsTo = [Circle, RegisteredUser, ExpenseType]
    static hasOne = [directDebit: DirectDebit]
    static hasMany = [debts: Debt, actions: Action, assignedUsers: User, expenses: Expense]

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
    Date receptionEndDate

    Date paymentBeginDate = new Date()
    Date paymentEndDate

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
        receptionEndDate nullable: true
        paymentBeginDate nullable: false
        paymentEndDate nullable: true

        intervalDays nullable: false, min: 0
        intervalMonths nullable: false, min: 0
        intervalYears nullable: false, min: 0

        isActive nullable: false
    }

    public RegularExpense() {
        super()
        directDebit = new DirectDebit()
        directDebit.setRegularExpense(this)
    }

    public RegularExpense(Map map) {
        super(map)
        directDebit = new DirectDebit(map)
        directDebit.setRegularExpense(this)
    }

    public String toString() {
        title
    }

    public boolean inReceptionTime(){
        Date date, dateBefore, dateAfter
        use(TimeCategory) {
            date = beginDate + intervalDays.days + intervalMonths.months + intervalYears.years
            dateBefore = new Date()
            dateAfter = dateBefore + 5.days
        }
        isActive && date >= dateBefore && date <= dateAfter
    }

    public postpone() {
        use(TimeCategory) {
            beginDate = beginDate + intervalDays.days + intervalMonths.months + intervalYears.years
        }
    }

    def toJSON(){
        return [name: getTitle(), description: getDescription(), id : getExpenseType().getId(), expenseTypeName: getExpenseType().getName(),
         expenseTypeCssClass: getExpenseType().getCssClass(), value: getValue(), paymentDeadline: BMDate.convertDateFormat(getPaymentDeadline()),
         receptionDeadline: BMDate.convertDateFormat(getReceptionDeadline()), receptionBeginDate: BMDate.convertDateFormat(getReceptionBeginDate()),
         receptionEndDate: BMDate.convertDateFormat(getReceptionEndDate()), paymentBeginDate: BMDate.convertDateFormat(getPaymentBeginDate()),
         paymentEndDate: BMDate.convertDateFormat(getPaymentEndDate())]
    }

    def create(List<String> idsUsers, List<Double> value){
        boolean result = false;
        int position = 0
        withTransaction {status ->
            try{
                RegularExpense regularExpense = save(flush: true, failOnError: true)
                directDebit.save(flush: true, failOnError: true)
                for(String str : idsUsers){
                    if( Double.parseDouble(value[position]) > 0 ){
                        User user = User.findById(Long.parseLong(str))
                        Debt debt = new Debt(value: value[position], user: user, regularExpense: regularExpense).save()
                        regularExpense.addToAssignedUsers(user)
                        RegisteredUser registeredUser = user.getRegisteredUser()
                        if(registeredUser && registeredUser.getId() == id) {
                            new Payment(user: user, debt: debt, value: Double.parseDouble(value[position]), validationDate: new Date(), isValidated: true).save()
                            debt.setResolvedDate(new Date())
                        }
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
        expense.setReceptionDate(getReceptionEndDate())
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

    public List<Action> latestEvents(){
        actions.sort{ it.getActionDate() }
    }

    public Set<RegisteredUser> getAssignedRegisteredUsers(){
        Set<RegisteredUser> registeredUsers = new HashSet<RegisteredUser>();
        assignedUsers.each{
            if(it.getRegisteredUser()){
                registeredUsers.add(it.getRegisteredUser())
            }
        }
        registeredUsers
    }

    def beforeValidate() {
        directDebit.validate()
        directDebit.errors.getAllErrors().each {
            ObjectError objectError = (ObjectError) it
            this.errors.reject(objectError.getCode(), objectError.toString())
        }
    }

    public boolean secureSave(){
        withTransaction { status ->
            try {
                save(flush: true, failOnError: true)
                directDebit.save(flush: true, failOnError: true)
                return true
            }catch(Exception ignored){
                status.setRollbackOnly()
                return false
            }
        }
    }
}
