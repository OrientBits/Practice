package com.lequiz.practice.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.lequiz.practice.base.FullScreenStatusOnly;
import com.lequiz.practice.module.SharedPreferenceConfig;
import com.lequiz.practice.R;

public class Login extends AppCompatActivity {

    private SharedPreferenceConfig sharedPreferenceConfig;
    private EditText userEmail, userPassword;
    private ImageView back_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_login);

        // Set transparency
        FullScreenStatusOnly fullScreenStatusOnly = new FullScreenStatusOnly(this);

        //  back_img = findViewById(R.id.back_img_in_sign_in);
        sharedPreferenceConfig = new SharedPreferenceConfig(getApplicationContext());

        userEmail = findViewById(R.id.sign_in_email);
        userPassword = findViewById(R.id.sign_in_password);


        if (sharedPreferenceConfig.readLoginStatus()) {
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        }

   /*     back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(getParentActivityIntent());
            }
        });  **/

    }

    public void Login_user(View view) {
        String user_email = userEmail.getText().toString().trim();
        String user_password = userPassword.getText().toString().trim();

        if (checkUserEmail() && checkUserPassword()) {

            if (user_email.equals(getResources().getString(R.string.user_email)) && user_password.equals(getResources().getString(R.string.user_password))) {
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
        if (userEmail.equals("")) {
            userEmail.setError("Please enter a valid email");
            return false;
        }
        return true;
    }

    private boolean checkUserPassword() {
        if (userPassword.equals("")) {
            userPassword.setError("Please enter a password");
            return false;
        } else if (userPassword.length() < 6 || userPassword.length() > 15) {
            userPassword.setError("Password should be between 6 to 10 Character");

            return false;
        }
        return true;
    }

    public void createAccount(View view) {
        startActivity(new Intent(this,RegisterUser.class));
    }
}