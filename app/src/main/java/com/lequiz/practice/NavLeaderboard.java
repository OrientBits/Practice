package com.lequiz.practice;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Objects;

public class NavLeaderboard extends AppCompatActivity {

    protected Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_leaderboard);


        toolbar = findViewById(R.id.nav_leaderboard_toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // for status bar color
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(NavLeaderboard.this,R.color.colorPrimary));



        ArrayList<NavLeaderboardWord> leaderboardWords = new ArrayList<>();
        leaderboardWords.add(new NavLeaderboardWord(R.drawable.ramshek,"Ramshek Rama",100000,1));
        leaderboardWords.add(new NavLeaderboardWord(R.drawable.profile_image,"Rishabh Raj",15500,2));
        leaderboardWords.add(new NavLeaderboardWord(R.drawable.reasoning,"Mithlesh Arya",12000,4));
        leaderboardWords.add(new NavLeaderboardWord(R.drawable.current_affairs,"Jyotsana Devi",2000,5));
        leaderboardWords.add(new NavLeaderboardWord(R.drawable.computer,"Rashi Bhabhi",2020,9));
        leaderboardWords.add(new NavLeaderboardWord(R.drawable.mathematics,"Botali Devi",1500,10));
        leaderboardWords.add(new NavLeaderboardWord(R.drawable.general_science,"Suraj Soni",1200,16));
        leaderboardWords.add(new NavLeaderboardWord(R.drawable.technology,"Mithlesh Ranaji",1000,14));
        leaderboardWords.add(new NavLeaderboardWord(R.drawable.special,"Akshay Cha",900,13));
        leaderboardWords.add(new NavLeaderboardWord(R.drawable.sport,"Bull ",850,11));
        leaderboardWords.add(new NavLeaderboardWord(R.drawable.computer,"Ramshek Rama",810,17));
        leaderboardWords.add(new NavLeaderboardWord(R.drawable.mathematics,"Ramshek Rama",800,18));
        leaderboardWords.add(new NavLeaderboardWord(R.drawable.technology,"Ramshek Rama",750,19));
        leaderboardWords.add(new NavLeaderboardWord(R.drawable.mithlesh,"Ramshek Rama",700,21));


        NavLeaderboardWordAdapter leaderboardWordAdapter = new NavLeaderboardWordAdapter(this,leaderboardWords);

        ListView listView = findViewById(R.id.leaderboard_list_view);

        listView.setAdapter(leaderboardWordAdapter);


    }
}
