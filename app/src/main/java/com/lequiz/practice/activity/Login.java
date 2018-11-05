package com.lequiz.practice.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.lequiz.practice.base.FullScreenStatusOnly;
import com.lequiz.practice.module.SharedPreferenceConfig;
import com.lequiz.practice.R;

public class Login extends AppCompatActivity {

    private SharedPreferenceConfig sharedPreferenceConfig;
    private EditText etLogEmail, etLogPassword;
    private ImageView back_img;
    private FirebaseAuth mAuth;
    private ProgressBar loginProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_login);

        // Set transparency
        FullScreenStatusOnly fullScreenStatusOnly = new FullScreenStatusOnly(this);

        //  back_img = findViewById(R.id.back_img_in_sign_in);
        sharedPreferenceConfig = new SharedPreferenceConfig(getApplicationContext());

        etLogEmail= findViewById(R.id.sign_in_email);
        etLogPassword = findViewById(R.id.sign_in_password);
        mAuth = FirebaseAuth.getInstance();
        loginProgressBar = findViewById(R.id.loginProgressBar);




    }

    public void Login_user(View view) {




        String user_email = etLogEmail.getText().toString().trim();
        String user_password = etLogPassword.getText().toString().trim();

        if(TextUtils.isEmpty(user_email))
        {
            etLogEmail.setError("Email is required.");
            etLogEmail.requestFocus();
            return;
        }

        if(TextUtils.isEmpty(user_password))
        {
            etLogPassword.setError("Password is required.");
            etLogPassword.requestFocus();
            return;
        }

        loginProgressBar.setVisibility(View.VISIBLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        mAuth.signInWithEmailAndPassword(user_email, user_password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                loginProgressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(Login.this, "Welcome back to LeQuiz!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Login.this, HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();
                startActivity(intent);


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                loginProgressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(Login.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
        });




    }

    public void createAccount(View view) {
        startActivity(new Intent(this,RegisterUser.class));
    }
}