package com.lequiz.practice;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import java.util.Objects;

public class ProfileActivity extends AppCompatActivity {

    protected Toolbar toolbar;
    private TextView editProfileText;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        toolbar = findViewById(R.id.profile_toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(null);

        //Set transparency

        Window w = getWindow(); // in Activity's onCreate() for instance
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        w.setNavigationBarColor(getResources().getColor(R.color.white));


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