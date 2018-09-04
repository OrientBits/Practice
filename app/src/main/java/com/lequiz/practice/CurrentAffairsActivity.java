package com.lequiz.practice;

import android.content.Intent;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;

public class CurrentAffairsActivity extends AppCompatActivity {

    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_affairs);

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


        // open activity to play quiz
        ImageView startQuiz = findViewById(R.id.start_image_of_current_affairs_quiz);
        startQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CurrentAffairsActivity.this, CurrentAffairsQuiz.class));
            }
        });


        // news section


        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Rishi is going to become manager of Rashi singh");
        arrayList.add("Today mithlesh will become an Astrologer ");
        arrayList.add("Ramshek He is so jealous person according to Rishi ");
        arrayList.add("My heart is dead for me.....");
        arrayList.add("Rishi is going to become manager of Rashi singh..");
        arrayList.add("Today mithlesh will become an Astrologer.. ");
        arrayList.add("Ramshek He is so jealous person according to Rishi.. ");
        arrayList.add("My heart is dead for me.......");
        arrayList.add("Rishi is going to become manager of Rashi sing123");
        arrayList.add("Today mithlesh will become an Astrologer123 ");
        arrayList.add("Ramshek He is so jealous person according to Rishi 123");
        arrayList.add("My heart is dead for me.....123");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);

        ListView newList = findViewById(R.id.current_affairs_news_list);

        newList.setAdapter(arrayAdapter);


    }

    }