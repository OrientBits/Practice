package com.lequiz.practice;

import android.app.DatePickerDialog;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class EditProfile extends AppCompatActivity {

    TextView textViewUserDobOnEditProfile, textViewEditDob;
    Spinner spinnerGenderSelector;
    int day, month, year;
    Calendar calendar;
    String gender,status;
    FirebaseUser mUser;
    FirebaseAuth mAuth;
    String firstName, lastName, fullName, email, fancyName, userDob;
    ImageView rightOnEditProfileImageView;
    DatePickerDialog datePickerDialogBirthday;
    ArrayAdapter<String> arrayAdapter;
    private DatabaseReference refToSpecificUser;
    TextInputEditText textInputEditTextFancyName, textInputEditTextStatus, textInputEditTextFirstName, textInputEditTextLastName, textInputEditTextEmailOnProfileEditDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        mAuth = FirebaseAuth.getInstance();
        mUser = FirebaseAuth.getInstance().getCurrentUser();

        if(mAuth.getCurrentUser()!=null){
        refToSpecificUser = FirebaseDatabase.getInstance().getReferenceFromUrl("https://lequiz-4abd1.firebaseio.com/Users/" + mAuth.getCurrentUser().getUid());

            refToSpecificUser.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    try {


                        firstName = dataSnapshot.child("firstName").getValue().toString();
                        lastName = dataSnapshot.child("lastName").getValue().toString();
                        fullName= firstName+" "+lastName;
                        textInputEditTextFirstName.setText(firstName);
                        textInputEditTextLastName.setText(lastName);
                        email = dataSnapshot.child("email").getValue().toString();
                        textInputEditTextEmailOnProfileEditDialog.setText(email);



                    }
                    catch (NullPointerException e)
                    {




                    }


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            /// Fetching extra details from firebase

            refToSpecificUser.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    try{
                        fancyName=dataSnapshot.child("fancyName").getValue().toString();
                        textInputEditTextFancyName.setText(fancyName);

                    }
                    catch (NullPointerException e)
                    {

                    }
                    try{
                        gender=dataSnapshot.child("gender").getValue().toString();
                        //gender.setText(gender);

                    }
                    catch (NullPointerException e)
                    {

                    }
                    try{
                        userDob=dataSnapshot.child("birthday").getValue().toString();
                        textViewUserDobOnEditProfile.setText(userDob);

                    }
                    catch (NullPointerException e)
                    {

                    }
                    try{

                       status = dataSnapshot.child("status").getValue().toString();
                       textInputEditTextStatus.setText(status);


                    }
                    catch (NullPointerException g)
                    {

                    }



                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {



                }
            });





        }

        // List to show male and female

        List<String> list = new ArrayList<String>();
        list.add("Gender");
        list.add("Male");
        list.add("Female");

        // ArrayAdapter for gender selctor

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,list);

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        textInputEditTextFancyName = findViewById(R.id.dialog_fancy_name_edit_text);
        textInputEditTextFirstName = findViewById(R.id.first_name_on_profile);
        textInputEditTextLastName = findViewById(R.id.last_name_on_profile);
        textViewUserDobOnEditProfile = findViewById(R.id.date_of_birth_edit_profile);
        textInputEditTextEmailOnProfileEditDialog = findViewById(R.id.email_on_profile_edit_dialog);
        spinnerGenderSelector = findViewById(R.id.gender_spinner_profile);
        spinnerGenderSelector.setAdapter(arrayAdapter);
        textInputEditTextStatus = findViewById(R.id.status_on_edit_profile);
        rightOnEditProfileImageView = findViewById(R.id.save_right_on_edit_profile);

        spinnerGenderSelector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(position==1)
                {
                    gender = "male";
                }
                if(position==2)
                {
                    gender = "female";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        textViewUserDobOnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);

                //////////////////////// open date picker

                datePickerDialogBirthday = new DatePickerDialog(EditProfile.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        i1++;
                        refToSpecificUser.child("birthday").setValue(i2+"/"+i1+"/"+i);


                    }
                },year,month,day);
                datePickerDialogBirthday.show();

            }
        });

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        rightOnEditProfileImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                // Save details to firebase

                if(TextUtils.isEmpty(textInputEditTextFirstName.getText()))
                {
                    textInputEditTextFirstName.setError("First name can't be empty");
                    return;
                }
                if(TextUtils.isEmpty(textInputEditTextLastName.getText()))
                {
                    textInputEditTextFirstName.setError("last name can't be empty");
                    return;
                }


                if(!Patterns.EMAIL_ADDRESS.matcher(textInputEditTextEmailOnProfileEditDialog.getText().toString()).matches())
                {
                    textInputEditTextEmailOnProfileEditDialog.setError("Enter a valid Email.");
                    textInputEditTextEmailOnProfileEditDialog.requestFocus();
                    return;
                }


                refToSpecificUser.child("fancyName").setValue(textInputEditTextFancyName.getText().toString());
                refToSpecificUser.child("firstName").setValue(textInputEditTextFirstName.getText().toString());
                refToSpecificUser.child("lastName").setValue(textInputEditTextLastName.getText().toString());
                refToSpecificUser.child("email").setValue(textInputEditTextEmailOnProfileEditDialog.getText().toString());
                refToSpecificUser.child("gender").setValue(gender);
                refToSpecificUser.child("status").setValue(textInputEditTextStatus.getText().toString());


                Toast.makeText(EditProfile.this, "Saved successfully", Toast.LENGTH_SHORT).show();

                finish();

            }
        });



    }
}
