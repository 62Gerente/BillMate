package com.billmate

import org.springframework.validation.ObjectError

class House{
    static belongsTo = Circle

    Circle circle

    static constraints = {
        circle nullable: false
    }

    public House() {
        super()
        circle = new Circle()
    }

    public House(Map map) {
        super(map)
        circle = new Circle(map)
    }

    def beforeValidate() {
        circle.validate()
        circle.errors.getAllErrors().each {
            ObjectError objectError = (ObjectError) it
            this.errors.reject(objectError.getCode(), objectError.toString())
        }
    }

    public void setName(String name){
        circle.setName(name)
    }

    public void setDescription(String description){
        circle.setDescription(description)
    }

    public String toString() {
        return circle.toString();
    }

    public boolean secureSave(){
        withTransaction { status ->
            try {
                circle.save(flush: true, failOnError: true)
                save(flush: true, failOnError: true)
                return true
            }catch(Exception ignored){
                status.setRollbackOnly()
                return false
            }
        }
    }

    public String getCssClass(){
        "fa fa-home"
    }

    public String getCssColor(){
        "green"
    }
}
