package com.billmate

class RegularExpense {
    static belongsTo = [DirectDebit, Circle, RegisteredUser, ExpenseType]
    static hasMany = [customDebts: CustomDebt, actions: Action, assignedUsers: User, expenses: Expense]

    DirectDebit directDebit
    RegisteredUser responsible
    ExpenseType expenseType
    Circle circle

    String title
    String description
    Double value

    Date beginDate
    Date endDate

    Date receptionBeginDate = new Date()
    Date receptionEndDate
    Date paymentBeginDate
    Date paymentEndDate

    Integer intervalDays = 0
    Integer intervalMonths = 1
    Integer intervalYears = 0

    Boolean isActive = true

    static constraints = {
        responsible nullable: false
        circle nullable: false
        expenseType nullable: false
        directDebit nullable: true, unique: true

        title nullable: false
        description maxSize: 2000, nullable: true
        value min: 0D, nullable: true

        beginDate nullable: true
        endDate nullable: true

        receptionBeginDate nullable: false
        receptionEndDate nullable: true
        paymentBeginDate nullable: true
        paymentEndDate nullable: true

        intervalDays nullable: false, min: 0
        intervalMonths nullable: false, min: 0
        intervalYears nullable: false, min: 0

        isActive nullable: false
    }

    public String toString() {
        title
    }

    public boolean inReceptionTime(){
        isActive && receptionBeginDate < new Date()
    }
}