package com.lequiz.practice;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NavPaymentWord {
    private String name;
    private long totalXp;
    private int prizeAmount;


    public NavPaymentWord(String name, long totalXp, int prizeAmount) {
        this.name = name;
        this.totalXp = totalXp;
        this.prizeAmount = prizeAmount;
    }

    public String getName() {
        return name;
    }

    public long getTotalXp() {
        return totalXp;
    }

    public int getPrizeAmount() {
        return prizeAmount;
    }

    public static String getDate() {
//        Date date = new Date();
//        SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy");
//        String fDate = format.getDateTimeInstance().format(date);
//        return format.get;

        SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy");
        Date newDate = new Date();
        String date = format.format(newDate);
        return date;
    }

}
