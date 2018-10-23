package com.lequiz.practice.Activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lequiz.practice.Base.FullScreenStatusOnly;
import com.lequiz.practice.R;


public class SplashActivity extends AppCompatActivity {

    public static int SPLASH_TIME_OUT = 500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_splash_screen);

        new FullScreenStatusOnly(this);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent1 = new Intent(SplashActivity.this, OnBoarding.class);
                startActivity(intent1);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
