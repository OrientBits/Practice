package com.lequiz.practice;

import android.content.Intent;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.lequiz.practice.Base.FullScreenStatusOnly;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CurrentAffairsActivity extends AppCompatActivity {

    Toolbar toolbar;
    List<ArrayListForHeadlinesAndImage> customArrayList;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_affairs);

        // Set transparency
        FullScreenStatusOnly fullScreenStatusOnly = new FullScreenStatusOnly(this);

        toolbar = findViewById(R.id.current_affairs_toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_button_current_affairs);




        // Heading Text Gradient
        TextView learnHeaderTech = findViewById(R.id.heading_on_current_affairs);
        Shader textShader = new LinearGradient(0, 0, 180, 0,
                new int[]{getResources().getColor(R.color.blueGradientTech), getResources().getColor(R.color.greenOnCurrentAffairs), getResources().getColor(R.color.yellowOnCurrentAffairs)},
                new float[]{0, 1, 2}, Shader.TileMode.CLAMP);
        learnHeaderTech.getPaint().setShader(textShader);

        //UserName Initialization

        TextView heyUserName = findViewById(R.id.hey_user_name);
        String heyUserNameMaker = "Hey " + getString(R.string.user_first_name) + ",";
        heyUserName.setText(heyUserNameMaker);



        // news section
        customArrayList = new ArrayList<>();
        customArrayList.add(new ArrayListForHeadlinesAndImage("Xiaomi Mi A2 is Available for \n" +
                "Pre-orders in India at Rs.17,499 on \n" +
                "Amazon Ahead of Launch Today",R.drawable.computer));
        customArrayList.add(new ArrayListForHeadlinesAndImage("Xiaomi Mi A2 is Available for \n" +
                "Pre-orders in India at Rs.17,499 on \n" +
                "Amazon Ahead of Launch Today",R.drawable.computer));
        customArrayList.add(new ArrayListForHeadlinesAndImage("Xiaomi Mi A2 is Available for \n" +
                "Pre-orders in India at Rs.17,499 on \n" +
                "Amazon Ahead of Launch Today",R.drawable.computer));
        customArrayList.add(new ArrayListForHeadlinesAndImage("Xiaomi Mi A2 is Available for \n" +
                "Pre-orders in India at Rs.17,499 on \n" +
                "Amazon Ahead of Launch Today",R.drawable.computer));
        customArrayList.add(new ArrayListForHeadlinesAndImage("Xiaomi Mi A2 is Available for \n" +
                "Pre-orders in India at Rs.17,499 on \n" +
                "Amazon Ahead of Launch Today",R.drawable.computer));
        customArrayList.add(new ArrayListForHeadlinesAndImage("Xiaomi Mi A2 is Available for \n" +
                "Pre-orders in India at Rs.17,499 on \n" +
                "Amazon Ahead of Launch Today",R.drawable.computer));
        customArrayList.add(new ArrayListForHeadlinesAndImage("Xiaomi Mi A2 is Available for \n" +
                "Pre-orders in India at Rs.17,499 on \n" +
                "Amazon Ahead of Launch Today",R.drawable.computer));
        customArrayList.add(new ArrayListForHeadlinesAndImage("Xiaomi Mi A2 is Available for \n" +
                "Pre-orders in India at Rs.17,499 on \n" +
                "Amazon Ahead of Launch Today",R.drawable.computer));
        customArrayList.add(new ArrayListForHeadlinesAndImage("Xiaomi Mi A2 is Available for \n" +
                "Pre-orders in India at Rs.17,499 on \n" +
                "Amazon Ahead of Launch Today",R.drawable.computer));

        listView = (ListView) findViewById(R.id.current_affairs_news_list);
        CustomArrayAdapterForCurrentAffairs customAdapter = new CustomArrayAdapterForCurrentAffairs(this, R.layout.custom_row_current_affairs, customArrayList);
        listView.setAdapter(customAdapter);

    }

}