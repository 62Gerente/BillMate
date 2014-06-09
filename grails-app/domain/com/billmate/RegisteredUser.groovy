package com.billmate

import com.lucastex.grails.fileuploader.UFile
import org.apache.shiro.crypto.hash.Sha256Hash
import org.springframework.validation.ObjectError
import org.codehaus.groovy.grails.web.mapping.LinkGenerator

class RegisteredUser {
    static belongsTo = User
    static hasMany = [responsibleExpenses: Expense, realizedActions: Action, systemNotifications: SystemNotification]

    User user
    UFile photo

    String phoneNumber
    String password

    static constraints = {
        user nullable: false
        photo nullable: true

        phoneNumber matches: '\\d{9}', unique: true, nullable: true
        password password: true, nullable: false
    }

    public RegisteredUser() {
        super()
        user = new User()
    }

    public RegisteredUser(Map map) {
        super(map)
        user = new User(map)
    }

    def beforeInsert() {
        encodePassword()
    }

    def beforeUpdate() {
        if (isDirty('password')) {
            encodePassword()
        }
    }

    def beforeValidate() {
        user.validate()
        user.errors.getAllErrors().each {
            ObjectError objectError = (ObjectError) it
            this.errors.reject(objectError.getCode(), objectError.toString())
        }
    }

    protected void encodePassword() {
        password = new Sha256Hash(password).toHex()
    }

    public void setName(String name){
        user.setName(name)
    }

    public void setEmail(String email){
        user.setEmail(email)
    }

    public String getName(){
        user.getName()
    }

    public String getEmail(){
        user.getEmail()
    }

    public void addToCircles(Circle circle){
        user.addToCircles(circle)
    }

    public String toString() {
        return user.toString();
    }

    public boolean secureSave(){
        withTransaction { status ->
            try {
                user.save(flush: true, failOnError: true)
                save(flush: true, failOnError: true)
                return true
            }catch(Exception ignored){
                status.setRollbackOnly()
                return false
            }
        }
    }

    public String getPhotoOrDefault(){
        def linkGenerator = this.domainClass.grailsApplication.mainContext.grailsLinkGenerator
        if(photo){
            return photo.getPath()
        }else{
            return linkGenerator.resource(dir: 'images',file: 'default-user.png', absolute: true)
        }
    }

    public Set<Circle> getCircles(Map map){
        Set<Circle> result = user.getCircles()

        if(map.containsKey('type')){
            result = result.findAll{ it.isType(map.get('type')) }
        }

        result
    }

    public Set<Circle> getHouses(){
        getCircles(type: 'House')
    }

    public Set<Circle> getCollectives(){
        getCircles(type: 'Collective')
    }

    public Set<Expense> unresolvedResponsibleExpenses(){
        responsibleExpenses.findAll{ !it.isResolved() }
    }

    public Double totalAsset(){
        Double total = unresolvedResponsibleExpenses().sum{ it.totalDebt() }
        total ? total : 0D
    }

    public Double totalAssetOf(Double user_id){
        unresolvedResponsibleExpensesBy(user_id).sum{ it.debtOf(user_id) }
    }

    public Set<Expense> unresolvedResponsibleExpensesBy(Long user_id){
        unresolvedResponsibleExpenses().findAll{ !it.isResolvedBy(user_id) }
    }

    public Set<User> whoOweMe(){
        Set<User> users = new HashSet<>()
        unresolvedResponsibleExpenses().each { users.addAll( it.assignedUsersWithDebts() ) }
        users
    }

    public Set<Payment> unconfirmedPaymentsOnResponsibleExpenses(){
        Set<Payment> unconfirmedPayments = new HashSet<>()
        unresolvedResponsibleExpenses().each{ unconfirmedPayments.addAll(it.unconfirmedPayments()) }
        unconfirmedPayments
    }

    public Integer getNumberOfUnreadNotifications(){
        int count = systemNotifications.count{ !it.getIsRead() }
        count? count : 0
    }

    public boolean moreThanOneExpense(long user_id){
        (unresolvedResponsibleExpensesBy(user_id).size() > 1)
    }

    public boolean existsExpenses(){
        (getNumberOfUnreadNotifications() > 0)
    }
}
