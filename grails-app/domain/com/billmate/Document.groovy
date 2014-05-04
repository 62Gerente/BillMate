package com.billmate

class Document {

    String name
    String type
    byte[] content
    Date createdAt

    static constraints = {
        name nullable: false, blank: false
        type nullable: false, blank: false
        content maxSize: 1024 * 1024 * 5, nullable: false
        createdAt nullable: false, defaultValue: new Date(), min: new Date()
    }

    String toString(){
        name
    }
}
