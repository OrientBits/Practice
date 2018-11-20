package com.lequiz.practice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.lequiz.practice.base.FullScreenStatusOnly;
import com.lequiz.practice.home.HomeContainer;


public class DispatcherActivity extends AppCompatActivity {

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();

        new FullScreenStatusOnly(this);


        final Intent intent1 = new Intent(DispatcherActivity.this, HomeContainer.class);
        final Intent intent = new Intent(DispatcherActivity.this, OnBoarding.class);

        if (mAuth.getCurrentUser() != null) {
            startActivity(intent1);
            finish();
        } else {
            startActivity(intent);
            finish();
        }


    }
}
