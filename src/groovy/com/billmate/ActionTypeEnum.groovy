package com.billmate

enum ActionTypeEnum {
    signUp("signUp", "logo", "primary"),
    addExpenseCircle("addExpenseCircle", "fa fa-money", "info"),
    addUserCircle("addUserCircle", "fa fa-user", "primary"),
    addRegularExpenseCircle("addRegularExpenseCircle", "fa fa-money"),
    addPaymentExpense("addPaymentExpense", "fa fa-money"),
    addHouse("addHouse", "fa fa-home", "success"),
    addCollective("addCollective", "fa fa-circle-o", "success")

    final String value
    final String icon
    final String css

    ActionTypeEnum(String value) {
        this.value = value
    }

    ActionTypeEnum(String value, String icon) {
        this.value = value
        this.icon = icon
    }

    ActionTypeEnum(String value, String icon, String css) {
        this.value = value
        this.icon = icon
        this.css = css
    }

    String getCssClass(){
        this.css
    }

    String getIcon(){
        this.icon
    }

    String toString(){
        this.value
    }
}
