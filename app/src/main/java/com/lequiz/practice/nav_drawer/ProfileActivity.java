package com.lequiz.practice.nav_drawer;

import android.annotation.SuppressLint;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.lequiz.practice.base.FullScreenStatusOnly;
import com.lequiz.practice.R;

import java.util.Objects;

public class ProfileActivity extends AppCompatActivity {

    protected Toolbar toolbar;
    private TextView editProfileText;
    CoordinatorLayout rootLayout;


    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_profile);

        toolbar = findViewById(R.id.profile_toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_arrow_ramu);


        //Set transparency
        FullScreenStatusOnly fullScreenStatusOnly = new FullScreenStatusOnly(this);


    }


}