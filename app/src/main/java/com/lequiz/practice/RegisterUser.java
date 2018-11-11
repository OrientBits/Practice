package com.lequiz.practice;

import android.content.Intent;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.lequiz.practice.base.FullScreenStatusOnly;
import com.lequiz.practice.home.HomeContainer;
import com.lequiz.practice.module.SharedPreferenceConfig;
import com.lequiz.practice.module.Users;

public class RegisterUser extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText etRegEmail;
    private EditText etRegPassword;
    private EditText etRegConfirmPassword;
    private EditText etRegFirstName;
    private EditText etRegLastName;
    private ProgressBar regProgress;
    private Vibrator myVib;
    DatabaseReference mDatabaseRefrence;


    private SharedPreferenceConfig sharedPreferenceConfig;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_register);

        System.out.println("This is register activity");

        findingViews();

        // Set transparency
        FullScreenStatusOnly fullScreenStatusOnly = new FullScreenStatusOnly(this);

        // For testing

        mAuth = FirebaseAuth.getInstance();

        mDatabaseRefrence = FirebaseDatabase.getInstance().getReference("Users");

        sharedPreferenceConfig = new SharedPreferenceConfig(getApplicationContext());

        if (sharedPreferenceConfig.readLoginStatus())
        {
            startActivity(new Intent(this,HomeContainer.class));
            finish();
        }


    }

    private void findingViews() {
        etRegEmail = findViewById(R.id.reg_email);
        etRegPassword = findViewById(R.id.reg_password);
        etRegConfirmPassword = findViewById(R.id.reg_confirm_password);
        etRegFirstName = findViewById(R.id.reg_first_name);
        etRegLastName = findViewById(R.id.reg_last_name);
        regProgress = findViewById(R.id.reg_progress_bar);
    }

    public void IHaveAnAccount(View view) {
        startActivity(new Intent(this,Login.class));
        finish();
    }


    public void registerUser(View view) {

      final String userEnteredEmail = etRegEmail.getText().toString().trim();
      String userEnteredPassword = etRegPassword.getText().toString().trim();
      String userEnteredConfirmPassword = etRegConfirmPassword.getText().toString().trim();
      final String userEnteredFirstName = etRegFirstName.getText().toString().trim();
      final String userEnteredLastName = etRegLastName.getText().toString().trim();
        if(TextUtils.isEmpty(userEnteredFirstName))
        {
            etRegFirstName.setError("First Name is required");
     //       etRegFirstName.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(userEnteredLastName))
        {
            etRegLastName.setError("Last Name is required");
      //      etRegLastName.requestFocus();
            return;
        }

      if(TextUtils.isEmpty(userEnteredEmail))
      {
          etRegEmail.setError("Email is required");
          etRegEmail.requestFocus();
          return;
      }
        if(TextUtils.isEmpty(userEnteredPassword))
        {
            etRegPassword.setError("Password is required");
            etRegPassword.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(userEnteredConfirmPassword))
        {
            etRegConfirmPassword.setError("Password is required");
            etRegConfirmPassword.requestFocus();
            return;
        }

      if(!Patterns.EMAIL_ADDRESS.matcher(userEnteredEmail).matches())
      {
          etRegEmail.setError("Enter a valid Email.");
          etRegEmail.requestFocus();
          return;
      }
        if(userEnteredPassword.length()<6)
        {
            etRegPassword.setError("Password must be 6 characters long");
            etRegPassword.requestFocus();
            return;
        }
      if(!userEnteredPassword.equals(userEnteredConfirmPassword))
      {
          etRegConfirmPassword.setError("Those passwords didn't match. Try again.");
          etRegConfirmPassword.requestFocus();
          return;

      }

      // Making authentication
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        regProgress.setVisibility(View.VISIBLE);
        if(mAuth.getCurrentUser()==null)
        {
        mAuth.createUserWithEmailAndPassword(userEnteredEmail, userEnteredPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful())
                {

                    Users users = new Users(userEnteredFirstName, userEnteredLastName, userEnteredEmail);

                    mDatabaseRefrence.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if(task.isSuccessful())
                            {
                                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                regProgress.setVisibility(View.INVISIBLE);
                                Toast.makeText(getApplicationContext(), "Welcome to LeQuiz!",Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(RegisterUser.this, HomeContainer.class);
                                startActivity(intent);
                                myVib = (Vibrator) getApplicationContext().getSystemService(VIBRATOR_SERVICE);
                                myVib.vibrate(600);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                finish();

                            }

                        }
                    });




                }
                else
                {
                    regProgress.setVisibility(View.INVISIBLE);
                    // if registration fails
                    Toast.makeText(getApplicationContext(),"Registration failed try again later",Toast.LENGTH_SHORT).show();
                    return;
                }

            }
        });}





    }

}
