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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TechnologyActivity extends AppCompatActivity {

    Toolbar toolbar;
    List<ArrayListForHeadlinesAndImage> customArrayList;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technology);

        // Set transparency


        Window w = getWindow(); // in Activity's onCreate() for instance
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        w.setNavigationBarColor(getResources().getColor(R.color.white));

        toolbar = findViewById(R.id.technology_toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_button_tech);


        // Heading TextView gradient


        TextView learnHeaderTech = findViewById(R.id.heading_on_technology);
        Shader textShader = new LinearGradient(0, 0, 180, 0,
                new int[]{getResources().getColor(R.color.blueGradientTech), getResources().getColor(R.color.purpleOnHomeText)},
                new float[]{0, 1}, Shader.TileMode.CLAMP);
        learnHeaderTech.getPaint().setShader(textShader);

        // Hey UserName Initilization on learn section

        TextView heyUserName = findViewById(R.id.hey_user_name);
        String heyUserNameMaker = "Hey "+getString(R.string.user_first_name)+",";
        heyUserName.setText(heyUserNameMaker);


        ImageView startQuiz = findViewById(R.id.start_image_of_technology_quiz);
        startQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TechnologyActivity.this,TechnologyQuiz.class));
            }
        });
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
