package com.lequiz.practice.nav_drawer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.lequiz.practice.adapters.NavPaymentWord;
import com.lequiz.practice.adapters.NavPaymentWordAdapter;
import com.lequiz.practice.base.FullScreenStatusOnly;
import com.lequiz.practice.R;

import java.util.ArrayList;
import java.util.Objects;

public class NavPayment extends AppCompatActivity {

    protected Toolbar toolbar;
    CardView toolbar_card_view_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_payment);


        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back_arrow_default);
        toolbar_card_view_2 = findViewById(R.id.toolbar_card_view_2);
        toolbar_card_view_2.setVisibility(View.INVISIBLE);
        // for status bar color
        FullScreenStatusOnly fullScreenStatusOnly = new FullScreenStatusOnly(this);

//      this is going to display list of payment users
        ArrayList<NavPaymentWord> paymentWords = new ArrayList<>();
        paymentWords.add(new NavPaymentWord("Ramshek Kumar Rana",15000,1500));
        paymentWords.add(new NavPaymentWord("Mithlesh Kumar Das",15000,99000));
        paymentWords.add(new NavPaymentWord("Suraj Kumar Soni",15000,5500));
        paymentWords.add(new NavPaymentWord("Rishabh Raj",15000,20));
        paymentWords.add(new NavPaymentWord("Rashi Singh",15000,10));
        paymentWords.add(new NavPaymentWord("Neha Kumari",15000,5));
        paymentWords.add(new NavPaymentWord("Jyotsana Kumari Arya",15000,1));
        paymentWords.add(new NavPaymentWord("Mithlesh Kumar Rana",15000,1500));
        paymentWords.add(new NavPaymentWord("Rakesh Kumar ",15000,700));
        paymentWords.add(new NavPaymentWord("Akshay Cha",15000,5600));
        paymentWords.add(new NavPaymentWord("Sonu Devi",15000,400));
        paymentWords.add(new NavPaymentWord("Manisha Soni",15000,0));

        NavPaymentWordAdapter paymentAdapter = new NavPaymentWordAdapter(this,paymentWords);

        ListView listView = findViewById(R.id.payment_list);

        listView.setAdapter(paymentAdapter);

        listView.setDivider(null);
        listView.setDividerHeight(0);

    }
}
