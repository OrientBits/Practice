package com.lequiz.practice.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.lequiz.practice.Activity.HomeActivity;
import com.lequiz.practice.Activity.Login;
import com.lequiz.practice.Base.FullScreenStatusOnly;
import com.lequiz.practice.Module.SharedPreferenceConfig;
import com.lequiz.practice.R;

public class RegisterUser extends AppCompatActivity {

    private SharedPreferenceConfig sharedPreferenceConfig;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_register);

        // Set transparency
        FullScreenStatusOnly fullScreenStatusOnly = new FullScreenStatusOnly(this);


        sharedPreferenceConfig = new SharedPreferenceConfig(getApplicationContext());

        if (sharedPreferenceConfig.readLoginStatus())
        {
            startActivity(new Intent(this,HomeActivity.class));
            finish();
        }


    }

    public void IHaveAnAccount(View view) {
        startActivity(new Intent(this,Login.class));
        finish();
    }


    public void registerUser(View view) {
        startActivity(new Intent(this,Login.class));
    }
}
