package com.billmate

abstract class Circle {

    String name;

    static constraints = {
        name blank: false, nullable: false
    }
}
