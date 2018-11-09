package com.lequiz.practice.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.lequiz.practice.base.FullScreenStatusOnly;
import com.lequiz.practice.R;


public class SplashActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    public static int SPLASH_TIME_OUT = 700;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_splash_screen);
        mAuth = FirebaseAuth.getInstance();
        ImageView splashImg = findViewById(R.id.splash_img);
        new FullScreenStatusOnly(this);

        Animation splashAnim = AnimationUtils.loadAnimation(this, R.anim.splash_screen_transition);
        splashImg.startAnimation(splashAnim);
        final Intent intent1 = new Intent(SplashActivity.this, HomeActivity.class);
        final Intent intent = new Intent(SplashActivity.this, OnBoarding.class);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mAuth.getCurrentUser() != null) {
                    startActivity(intent1);
                    finish();
                } else {
                    startActivity(intent);
                    finish();
                }
            }
            }, SPLASH_TIME_OUT);
    }
}
