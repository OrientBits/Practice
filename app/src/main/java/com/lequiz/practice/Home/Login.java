package com.lequiz.practice.Home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.lequiz.practice.R;
import com.lequiz.practice.DataBase.SharedPreferenceConfig;

public class Login extends AppCompatActivity {

    private SharedPreferenceConfig sharedPreferenceConfig;
    private EditText userName,userPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        sharedPreferenceConfig = new SharedPreferenceConfig(getApplicationContext());

        userName = findViewById(R.id.login_user_name);
        userPassword = findViewById(R.id.login_user_password);


        if (sharedPreferenceConfig.readLoginStatus())
        {
            startActivity(new Intent(this,HomeActivity.class));
            finish();
        }
    }

    public void Login_user(View view)
    {
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
        }

    }
}
