package com.prestashop.utils;


import java.util.Date;

public class Utils {
    public static String getUniqueEmailAddress() {
        long timeInMilliseconds;
        String name = "user_";
        String domain = "@yahoo.com";
        String emailAddress;
        Date date = new Date();
        timeInMilliseconds = date.getTime();
        emailAddress = name + timeInMilliseconds + domain;
        return emailAddress;
    }
}