package com.billmate

class BMDate {
    public static Date convertStringsToDate(String dateString, boolean generateDateIfNotExists){
        Date date = null
        if (dateString && !dateString.equals("")) date = Date.parse("dd/MM/yyyy", dateString)
        else if(generateDateIfNotExists) date = new Date()
        return date
    }

    public static String convertDateFormat(Date date){
        return date?.format("dd/MM/yyyy")
    }
}
