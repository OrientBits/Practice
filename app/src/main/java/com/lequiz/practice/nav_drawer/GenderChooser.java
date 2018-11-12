package com.lequiz.practice.nav_drawer;

import android.content.Intent;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lequiz.practice.R;
import com.lequiz.practice.RegisterUser;
import com.lequiz.practice.base.FullScreenStatusOnly;
import com.lequiz.practice.home.HomeContainer;
import com.lequiz.practice.module.Users;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class GenderChooser extends AppCompatActivity {
    FirebaseAuth mAuth;
    CircleImageView maleCircle, femaleCircle;
    TextView maleText, femaleText, hasntGivenGenderTextiew,textViewHeyUsername;
    Button registerButton;
    String gender;
    String firstName;
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

        refrenceToCurrentUsersNode = FirebaseDatabase.getInstance().getReference().child("Users").child(uID);

        // Initializing the variables

        maleCircle = findViewById(R.id.circleImageViewMale);
        femaleCircle = findViewById(R.id.circleImageViewFemale);
        textViewHeyUsername = findViewById(R.id.textViewHeyUserName);
        maleText = findViewById(R.id.textViewMale);
        femaleText = findViewById(R.id.textViewFemale);
        registerButton = findViewById(R.id.buttonRegister);
        hasntGivenGenderTextiew = findViewById(R.id.textviewIdontWantToSay);

        // Onclick listnera for male circle

        maleCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                femaleCircle.setImageResource(R.drawable.female_avatar_placeholder);
                maleCircle.setImageResource(R.drawable.male_avatar_selected);
                gender= MALE;

            }
        });

        // Onclick listner for female circle

        femaleCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                femaleCircle.setImageResource(R.drawable.female_avatar_selected);
                maleCircle.setImageResource(R.drawable.male_avatar_placeholder);
                gender = FEMALE;

            }
        });

        // OnClick listner for i dont want to tell

        hasntGivenGenderTextiew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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


        // Getting name from database


        refrenceToCurrentUsersNode.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                firstName=Objects.requireNonNull(dataSnapshot.child("firstName").getValue()).toString();
                String heyUserNameString = "Hii, "+firstName;
                textViewHeyUsername.setText(heyUserNameString);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        // maleTextOnclick listner

        maleText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                femaleCircle.setImageResource(R.drawable.female_avatar_placeholder);
                maleCircle.setImageResource(R.drawable.male_avatar_selected);
                gender= MALE;
            }
        });

        // female text onClick
        femaleText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                femaleCircle.setImageResource(R.drawable.female_avatar_selected);
                maleCircle.setImageResource(R.drawable.male_avatar_placeholder);
                gender = FEMALE;
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(),"Please select your gender or skip by pressing 'I don't want to tell'", Toast.LENGTH_SHORT).show();


                // Upload to Gender details to firebase
                if(!TextUtils.isEmpty(gender))
                {
                refrenceToCurrentUsersNode.child("gender").setValue(gender);
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                Toast.makeText(getApplicationContext(), "Welcome to LeQuiz! ",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), HomeContainer.class);
                startActivity(intent);
                vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                vibrator.vibrate(600);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();}


            }
        });


        }



    }

