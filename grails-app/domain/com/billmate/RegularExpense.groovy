package com.billmate

import com.nanlabs.grails.plugin.logicaldelete.LogicalDelete
import groovy.time.TimeCategory
import org.springframework.validation.ObjectError

@LogicalDelete
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

    public RegularExpense() {
        directDebit = new DirectDebit()
        directDebit.setRegularExpense(this)
    }

    public RegularExpense(Map map) {
        directDebit = new DirectDebit(map)
        directDebit.setRegularExpense(this)
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

    public toJSON(){
        return [name: getTitle(), description: getDescription(), id : getExpenseType().getId(), expenseTypeName: getExpenseType().getName(),
         expenseTypeCssClass: getExpenseType().getCssClass(), value: getValue(), paymentDeadline: BMDate.convertDateFormat(getPaymentDeadline()),
         receptionDeadline: BMDate.convertDateFormat(getReceptionDeadline()), receptionBeginDate: BMDate.convertDateFormat(getReceptionBeginDate()),
         beginDate: BMDate.convertDateFormat(getBeginDate()), endDate: BMDate.convertDateFormat(getEndDate())]
    }

    public create(List<String> idsUsers, List<Double> value){
        boolean result = false;
        int position = 0
        withTransaction {status ->
            try{
                RegularExpense regularExpense = save(flush: true, failOnError: true)
                directDebit.save(flush: true, failOnError: true)
                for(String str : idsUsers){
                    if( value[position] > 0 ){
                        User user = User.findById(Long.parseLong(str))
                        regularExpense.addToAssignedUsers(user)
                        new Debt(value: value[position], user: user, regularExpense: regularExpense).save()
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

    public Expense fromRegularExpenseToExpense(Expense expense, RegisteredUser registeredUser, Double value){
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

    public static Set<User> getFriendsOfRegularExpenseByTerm(String term) {
        Set<User> list = new HashSet<User>()
        User.findAll().each { if(it.getName().toUpperCase().contains(term) || it.getEmail().toUpperCase().contains(term)) list.add(it) }
        return list
    }

    public static Set<Object> getFriendsOfRegularExpenseByTermFormated(String term, Long id_regular_expense){
        Boolean result = false
        Set<Object> list = new HashSet<>()
        RegularExpense regularExpense = RegularExpense.findById(id_regular_expense)
        getFriendsOfRegularExpenseByTerm(term).each {
            User user = it
            result = false
            regularExpense.getAssignedUsers().each { if(!result && it.getId() == user.getId()) result = true }
            if(!result){
                list.add([id  : it.getId(), faicon: it.getPhotoOrDefault(), name: it.getName()])
            }
        }
        return list
    }

    public boolean ajustValues(){
        boolean result = true
        withTransaction {status ->
            try{
                propagateValues()
            }
            catch(Exception e){
                result = false
                status.setRollbackOnly()
            }
        }
        return result
    }

    public void propagateValues(){
        RegularExpense regularExpense = RegularExpense.findById(getId())
        Set<Debt> setOfDebts = regularExpense.getDebts()
        int size = setOfDebts.size()
        Double value = regularExpense.getValue()
        Double partialValue = value / size
        setOfDebts.each {
            it.setValue(partialValue)
        }
    }

    public boolean addUsersAndAjustValues(Long id_user){
        boolean result = true
        withTransaction {status ->
            try{
                RegularExpense regularExpense = RegularExpense.findById(getId())
                User user = User.findById(id_user)
                user.addToRegularExpenses(regularExpense)
                new Debt(regularExpense: regularExpense, resolvedDate: new Date(), user: user, value: 0).save()
                propagateValues()
                save()
            }
            catch(Exception e){
                result = false
                status.setRollbackOnly()
            }
        }
        return result
    }

    public boolean removeUserAndAjustValues(User user){
        boolean result = true
        withTransaction {status ->
            try{
                Debt debt = Debt.findByRegularExpenseAndUser(this,user)
                removeFromDebts(debt)
                removeFromAssignedUsers(user)
                user.removeFromDebts(debt)
                debt.delete(physical: true)
                propagateValues()
            }
            catch(Exception e){
                result = false
                status.setRollbackOnly()
            }
        }
        return result
    }
}
