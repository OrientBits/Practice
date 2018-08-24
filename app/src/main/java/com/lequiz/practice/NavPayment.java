package com.lequiz.practice;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

        // for status bar color
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(NavPayment.this, R.color.colorPrimaryDark));


//        String top10User[] = new String[10];
//        top10User[0] = "Mithlesh Arya";
//        top10User[1] = "Rishabh Raj";
//        top10User[2] = "Ramshek Kumar Rana";
//        top10User[3] = "Suraj Kumar Soni";
//        top10User[4] = "Mithlesh Rana Hariyana";
//        top10User[5] = "Manjeet Prasad";
//        top10User[6] = "Subash Harami";
//        top10User[7] = "Abhimanyu Kumari Devi";
//        top10User[8] = "Neha Devi";
//        top10User[9] = "Jyotsana Devi";
//
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, top10User);
//
//        ListView listView = findViewById(R.id.top_10_learners);
//
//        listView.setAdapter(arrayAdapter);

    }
}
