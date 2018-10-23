package com.lequiz.practice.NavDrawer;

import android.annotation.SuppressLint;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.lequiz.practice.Base.FullScreenStatusOnly;
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


//        editProfileText = findViewById(R.id.edit_profile_data);
//        editProfileText.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Dialog dialog=new Dialog(ProfileActivity.this,android.R.style.Theme_Black_NoTitleBar_Fullscreen);
//                dialog.setContentView(R.layout.activity_nav_invite_friends);
//                dialog.show();
//            }
//        });

    }


}