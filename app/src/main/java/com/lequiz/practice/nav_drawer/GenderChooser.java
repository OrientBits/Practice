package com.lequiz.practice.nav_drawer;

import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.lequiz.practice.R;
import com.lequiz.practice.RegisterUser;
import com.lequiz.practice.base.FullScreenStatusOnly;
import com.lequiz.practice.home.HomeContainer;
import com.lequiz.practice.module.Users;

import de.hdodenhof.circleimageview.CircleImageView;

public class GenderChooser extends AppCompatActivity {
    FirebaseAuth mAuth;
    CircleImageView maleCircle, femaleCircle;
    TextView maleText, femaleText, hasntGivenGenderTextiew,textViewHeyUsername;
    Button registerButton;
    String gender;
    private final String MALE = "male";
    private final String FEMALE = "female";
    private Vibrator vibrator;



    DatabaseReference refrenceToCurrentUsersNode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gender_chooser);

        // Making the status bar disappear

        FullScreenStatusOnly fullScreenStatusOnly = new FullScreenStatusOnly(this);

        // Initializing the Firebase Auth

        mAuth = FirebaseAuth.getInstance();

        // Getting the user id

        String uID=mAuth.getCurrentUser().getUid();

        // Initializing the Firebase Database

        refrenceToCurrentUsersNode= FirebaseDatabase.getInstance().getReference().child("Users").child(uID);

        // Initializing the variables

        maleCircle = findViewById(R.id.circleImageViewMale);
        femaleCircle = findViewById(R.id.circleImageViewFemale);
        textViewHeyUsername = findViewById(R.id.textViewHeyUserName);
        maleText = findViewById(R.id.textViewMale);
        femaleText = findViewById(R.id.textViewFemale);
        registerButton = findViewById(R.id.buttonRegister);

        // Onclick listnera for male circle

        maleCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                gender= MALE;

            }
        });

        // Onclick listner for female circle

        femaleCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                gender = FEMALE;

            }
        });
       String heyUserNameString = "Hii, "+Users.firstNameForUse;
       textViewHeyUsername.setText(heyUserNameString);

        // maleTextOnclick listner

        maleText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gender=MALE;
            }
        });

        // female text onClick
        femaleText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gender=FEMALE;
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Upload to Gender details to firebase

                refrenceToCurrentUsersNode.child("gender").setValue(gender);
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                Toast.makeText(getApplicationContext(), "Welcome to LeQuiz! ",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), HomeContainer.class);
                startActivity(intent);
                vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                vibrator.vibrate(600);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();


            }
        });


        }



    }

