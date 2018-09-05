package com.lequiz.practice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    private SharedPreferenceConfig sharedPreferenceConfig;
    private EditText userName,userPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        sharedPreferenceConfig = new SharedPreferenceConfig(getApplicationContext());

        userName = findViewById(R.id.reg_mobile_no_editText);
        userPassword = findViewById(R.id.reg_password_editText);


        if (sharedPreferenceConfig.readLoginStatus())
        {
            startActivity(new Intent(this,HomeActivity.class));
            finish();
        }
    }

    public void Login_user(View view)
    {
        view.setVisibility(View.INVISIBLE);
        ProgressBar progressBar = findViewById(R.id.sign_in_progressBar);
        progressBar.setVisibility(View.VISIBLE);
        String user_name = userName.getText().toString();
        String user_password = userPassword.getText().toString();

        if (user_name.equals(getResources().getString(R.string.user_name)) && user_password.equals(getResources().getString(R.string.user_password)))
        {
            startActivity(new Intent(this,HomeActivity.class));
            sharedPreferenceConfig.writeLoginStatus(true);
            finish();
        }
        else
        {
            Toast.makeText(this,"Login failed... Please try again...",Toast.LENGTH_LONG).show();
            userName.setText("");
            userPassword.setText("");
            view.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.INVISIBLE);
        }

    }
}
