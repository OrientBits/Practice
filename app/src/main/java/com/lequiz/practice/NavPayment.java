package com.lequiz.practice;

import android.annotation.SuppressLint;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class NavPayment extends AppCompatActivity {

    protected Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_payment);


        toolbar = findViewById(R.id.nav_payment_toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //   for status bar color
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(NavPayment.this, R.color.colorPrimaryDark));

        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

//      this is going to display list of payment users
        ArrayList<NavPaymentWord> paymentWords = new ArrayList<>();
        paymentWords.add(new NavPaymentWord("Ramshek Kumar Rana",15000,1500,calendar));
        paymentWords.add(new NavPaymentWord("Mithlesh Kumar Das",15000,99000,calendar));
        paymentWords.add(new NavPaymentWord("Suraj Kumar Soni",15000,5500,calendar));
        paymentWords.add(new NavPaymentWord("Rishabh Raj",15000,20,calendar));
        paymentWords.add(new NavPaymentWord("Rashi Singh",15000,10,calendar));
        paymentWords.add(new NavPaymentWord("Neha Kumari",15000,5,calendar));
        paymentWords.add(new NavPaymentWord("Jyotsana Kumari Arya",15000,1,calendar));
        paymentWords.add(new NavPaymentWord("Mithlesh Kumar Rana",15000,1500,calendar));
        paymentWords.add(new NavPaymentWord("Rakesh Kumar ",15000,700,calendar));
        paymentWords.add(new NavPaymentWord("Akshay Cha",15000,5600,calendar));
        paymentWords.add(new NavPaymentWord("Sonu Devi",15000,400,calendar));
        paymentWords.add(new NavPaymentWord("Manisha Soni",15000,0,calendar));

        NavPaymentWordAdapter paymentAdapter = new NavPaymentWordAdapter(this,paymentWords);

        ListView listView = findViewById(R.id.payment_list);

        listView.setAdapter(paymentAdapter);

        listView.setDivider(null);
        listView.setDividerHeight(0);

    }
}
