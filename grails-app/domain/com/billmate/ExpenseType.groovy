package com.billmate

class ExpenseType {

    String name
    byte[] icon

    static constraints = {
        name blank: false, nullable: false
        icon maxSize: 1024 * 1024, nullable: false
    }

    String toString(){
        name
    }
}
