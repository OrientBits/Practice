package com.lequiz.practice;


import android.annotation.SuppressLint;

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

    public static String getDate() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy");
        Date newDate = new Date();
        String dateFormat = format.format(newDate);
        return dateFormat;
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

}
