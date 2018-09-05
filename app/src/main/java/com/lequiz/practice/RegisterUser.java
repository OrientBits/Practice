package com.lequiz.practice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class RegisterUser extends AppCompatActivity {

    private SharedPreferenceConfig sharedPreferenceConfig;
    private TextView regAlready;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        regAlready = findViewById(R.id.already_register_textView);
        sharedPreferenceConfig = new SharedPreferenceConfig(getApplicationContext());

        if (sharedPreferenceConfig.readLoginStatus())
        {
            startActivity(new Intent(this,HomeActivity.class));
            finish();
        }


        regAlready.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterUser.this,Login.class));
                finish();
            }
        });


    }

    public void register_user(View view) {
        startActivity(new Intent(RegisterUser.this,Login.class));
        finish();
    }
}
