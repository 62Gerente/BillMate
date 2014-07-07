package com.billmate

class BMDate {
    static mapWith = "none"

    public static Date convertStringsToDate(String dateString, boolean generateDateIfNotExists){
        Date date = null
        if (!dateString.equals("")) date = Date.parse("dd/MM/yyyy", dateString)
        if(generateDateIfNotExists) date = new Date()
        return date
    }

    public static String convertDateFormat(Date date){
        return date?.format("dd/MM/yyyy")
    }
}
