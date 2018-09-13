package com.lequiz.practice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    private SharedPreferenceConfig sharedPreferenceConfig;
    private EditText userEmail, userPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreferenceConfig = new SharedPreferenceConfig(getApplicationContext());

        userEmail = findViewById(R.id.sign_in_email);
        userPassword = findViewById(R.id.sign_in_password);


        if (sharedPreferenceConfig.readLoginStatus()) {
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        }
    }

    public void Login_user(View view) {
        String user_name = userEmail.getText().toString().trim();
        String user_password = userPassword.getText().toString().trim();

        if (checkUserEmail() && checkUserPassword()) {

            if (user_name.equals(getResources().getString(R.string.user_name)) && user_password.equals(getResources().getString(R.string.user_password))) {
                startActivity(new Intent(this, HomeActivity.class));
                sharedPreferenceConfig.writeLoginStatus(true);
                finish();
            } else {
                Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                userEmail.setText("");
                userPassword.setText("");
            }
        }

    }


    private boolean checkUserEmail() {
        if (TextUtils.isEmpty((CharSequence) userEmail)) {
            userEmail.setError("Please enter a valid email");
            return false;
        }
        return true;
    }

    private boolean checkUserPassword() {
        if (TextUtils.isEmpty((CharSequence) userPassword)) {
            userPassword.setError("Please enter a password");
            return false;
        } else if (userPassword.length() < 6 || userPassword.length() > 10) {
            userPassword.setError("Password should be between 6 to 10 Character");
            return false;
        }
        return true;
    }


}
