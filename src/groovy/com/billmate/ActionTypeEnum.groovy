package com.billmate

enum ActionTypeEnum {
    signUp("signUp", "logo", "primary"),
    addHouse("addHouse", "fa fa-home", "success"),
    addCollective("addCollective", "fa fa-circle-o", "success"),
    addExpenseCircle("addExpenseCircle", "fa fa-money", "info"),
    addUserCircle("addUserCircle", "fa fa-user", "primary"),
    addPaymentExpense("addPaymentExpense", "fa fa-money", "primary"),
    addRegularExpenseCircle("addRegularExpenseCircle", "fa fa-money", "primary"),
    addedToCircle("addedToCircle", "fa fa-user", "primary"),
    removedUserCircle("removedUserCircle", "fa fa-user", "primary"),
    removedFromCircle("removedFromCircle", "fa fa-user", "primary"),
    receivedPaymentExpense("receivedPaymentExpense", "fa fa-money", "primary")

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
