package com.lequiz.practice;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class RegisterUser extends AppCompatActivity {

    private SharedPreferenceConfig sharedPreferenceConfig;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);



        sharedPreferenceConfig = new SharedPreferenceConfig(getApplicationContext());

        if (sharedPreferenceConfig.readLoginStatus())
        {
            startActivity(new Intent(this,HomeActivity.class));
            finish();
        }


    }

    public void registerUser(View view) {
        startActivity(new Intent(RegisterUser.this,Login.class));
        finish();
    }

    public void IHaveAnAccount(View view) {
        startActivity(new Intent(RegisterUser.this,Login.class));
        finish();
    }
}
