package com.lequiz.practice.nav_drawer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.lequiz.practice.adapters.NavLeaderboardWord;
import com.lequiz.practice.adapters.NavLeaderboardWordAdapter;
import com.lequiz.practice.base.FullScreenStatusOnly;
import com.lequiz.practice.R;

import java.util.ArrayList;
import java.util.Objects;

public class NavLeaderboard extends AppCompatActivity {

    protected Toolbar toolbar;
    CardView toolbar_card_view_2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_leaderboard);


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


        ArrayList<NavLeaderboardWord> leaderboardWords = new ArrayList<>();
        leaderboardWords.add(new NavLeaderboardWord(R.drawable.mithlesh,"Ramshek Rama",100000,1));
        leaderboardWords.add(new NavLeaderboardWord(R.drawable.mithlesh,"Rishabh Raj",15500,2));
        leaderboardWords.add(new NavLeaderboardWord(R.drawable.home_reasoning,"Mithlesh Arya",12000,4));
        leaderboardWords.add(new NavLeaderboardWord(R.drawable.home_current_affairs,"Jyotsana Devi",2000,5));
        leaderboardWords.add(new NavLeaderboardWord(R.drawable.home_computer,"Rashi Bhabhi",2020,9));
        leaderboardWords.add(new NavLeaderboardWord(R.drawable.home_mathematics,"Botali Devi",1500,10));
        leaderboardWords.add(new NavLeaderboardWord(R.drawable.home_general_science,"Suraj Soni",1200,16));
        leaderboardWords.add(new NavLeaderboardWord(R.drawable.home_technology,"Mithlesh Ranaji",1000,14));
        leaderboardWords.add(new NavLeaderboardWord(R.drawable.home_current_affairs,"Akshay Cha",900,13));
        leaderboardWords.add(new NavLeaderboardWord(R.drawable.home_sport,"Bull ",850,11));
        leaderboardWords.add(new NavLeaderboardWord(R.drawable.home_computer,"Ramshek Rama",810,17));
        leaderboardWords.add(new NavLeaderboardWord(R.drawable.home_english,"Ramshek Rama",800,18));
        leaderboardWords.add(new NavLeaderboardWord(R.drawable.home_technology,"Ramshek Rama",750,19));
        leaderboardWords.add(new NavLeaderboardWord(R.drawable.home_reasoning,"Ramshek Rama",700,21));


        NavLeaderboardWordAdapter leaderboardWordAdapter = new NavLeaderboardWordAdapter(this,leaderboardWords);

        ListView listView = findViewById(R.id.leaderboard_list_view);

        listView.setAdapter(leaderboardWordAdapter);


    }
}
