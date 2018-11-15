package com.lequiz.practice;

import android.content.Intent;
import android.net.Uri;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.lequiz.practice.base.FullScreenStatusOnly;
import com.lequiz.practice.home.HomeContainer;
import com.lequiz.practice.module.SharedPreferenceConfig;

public class Login extends AppCompatActivity {

    private static final int RC_SIGN_IN = 1;
    private static final String TAG = "LoginActivity";
    private SharedPreferenceConfig sharedPreferenceConfig;
    private EditText etLogEmail, etLogPassword;
    private FirebaseAuth mAuth;
    private ProgressBar loginProgressBar;
    private Vibrator myVib;
    private Button googleSignInButton;
    GoogleSignInClient mGoogleSignInClient;
    String fullName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_login);
        System.out.println("This is login activity");

        // Set transparency
        FullScreenStatusOnly fullScreenStatusOnly = new FullScreenStatusOnly(this);


        sharedPreferenceConfig = new SharedPreferenceConfig(getApplicationContext());
        googleSignInButton = findViewById(R.id.sign_in_with_google); // Google sign in button initialization
        etLogEmail= findViewById(R.id.sign_in_email);
        etLogPassword = findViewById(R.id.sign_in_password);
        mAuth = FirebaseAuth.getInstance();
        loginProgressBar = findViewById(R.id.loginProgressBar);

        // // // Configuring google sign in

        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

// Sign in client initialization

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        // Opening sign in Intent

        googleSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                signIn();

            }
        });



    }
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {

                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
                // ...
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Auth failed with google", Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });
    }

    private void updateUI(FirebaseUser user) {

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        if(acct!=null)
        {
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();



            // Check if user's email is verified
            boolean emailVerified = user.isEmailVerified();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getIdToken() instead.
            String uid = user.getUid();

            /////////////////////////////////////////////////////////////

            Toast.makeText(Login.this, "Welcome to LeQuiz!", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(Login.this, HomeContainer.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            finish();
            startActivity(intent);



            //////////////////////////////////////////////////////////////




        }

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
                Intent intent = new Intent(Login.this, HomeContainer.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                myVib = (Vibrator) getApplicationContext().getSystemService(VIBRATOR_SERVICE);
                myVib.vibrate(500);

                }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                loginProgressBar.setVisibility(View.INVISIBLE);
                if(e.getMessage().contains("user does not have a password"))
                {
                    Toast.makeText(Login.this, "W've detected your google account with us. Please sign in with Google", Toast.LENGTH_LONG).show();
                }
                else
                Toast.makeText(Login.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
        });




    }

    public void createAccount(View view) {
        Intent intent = new Intent(this,RegisterUser.class);
        intent.putExtra("From Activity","Login");
        startActivity(intent);
        finish();

    }


}