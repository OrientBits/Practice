package com.lequiz.practice;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lequiz.practice.base.FullScreenStatusOnly;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class EditProfile extends AppCompatActivity {

    TextView textViewUserDobOnEditProfile, textViewEditDob;
    Spinner spinnerGenderSelector;
    int day, month, year;
    Calendar calendar;
    String gender,status;
    private double latitude, longitude;
    String finalAddress;
    private FusedLocationProviderClient client;
    FirebaseUser mUser;
    FirebaseAuth mAuth;
    DatabaseReference currentUserRef;
    String firstName,location, lastName, fullName, email, fancyName, userDob;
    ImageView rightOnEditProfileImageView;
    DatePickerDialog datePickerDialogBirthday;
    ArrayAdapter<String> arrayAdapter;
    private DatabaseReference refToSpecificUser;
    TextInputEditText locationEditText;
    TextInputEditText textInputEditTextFancyName, textInputEditTextStatus, textInputEditTextFirstName, textInputEditTextLastName, textInputEditTextEmailOnProfileEditDialog;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        mAuth = FirebaseAuth.getInstance();
        mUser = FirebaseAuth.getInstance().getCurrentUser();


        new FullScreenStatusOnly(this);
        try{
        client = LocationServices.getFusedLocationProviderClient(this);}
        catch (Exception e)
        {

        }



        if(mAuth.getCurrentUser()!=null){
        refToSpecificUser = FirebaseDatabase.getInstance().getReferenceFromUrl("https://lequiz-4abd1.firebaseio.com/Users/" + mAuth.getCurrentUser().getUid());
        currentUserRef = FirebaseDatabase.getInstance().getReference("Users");
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
                    try{
                        location=dataSnapshot.child("location").getValue().toString();
                        locationEditText.setText(location);
                    }
                    catch (NullPointerException e)
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
        locationEditText = findViewById(R.id.text_input_location_edit_profile);

        locationEditText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if(event.getAction() == MotionEvent.ACTION_UP) {
                    if(event.getRawX() >= (locationEditText.getRight() - locationEditText.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // your action here
                        requestPermission();
                        Toast.makeText(EditProfile.this,"Auto Detecting Location",Toast.LENGTH_SHORT).show();
                        if (ActivityCompat.checkSelfPermission(EditProfile.this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(EditProfile.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                            // TODO: Consider calling
                            //    ActivityCompat#requestPermissions
                            // here to request the missing permissions, and then overriding
                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                            //                                          int[] grantResults)
                            // to handle the case where the user grants the permission. See the documentation
                            // for ActivityCompat#requestPermissions for more details.


                        }
                        client.getLastLocation().addOnSuccessListener(EditProfile.this, new OnSuccessListener<Location>() {
                            @Override
                            public void onSuccess(Location location) {

                                if(location!=null)
                                {
                                    latitude= location.getLatitude();
                                    longitude= location.getLongitude();
                                    getExactLocation();

                                }

                            }
                        });

                        return true;
                    }
                }
                return false;
            }
        });
//
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



                refToSpecificUser.child("firstName").setValue(textInputEditTextFirstName.getText().toString());
                refToSpecificUser.child("lastName").setValue(textInputEditTextLastName.getText().toString());
                refToSpecificUser.child("email").setValue(textInputEditTextEmailOnProfileEditDialog.getText().toString());
                refToSpecificUser.child("gender").setValue(gender);
                currentUserRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        for(DataSnapshot data: dataSnapshot.getChildren()){
                           try{
                               if(textInputEditTextFancyName.equals("") || TextUtils.isEmpty(textInputEditTextFancyName.getText().toString()))
                               {
                                   Toast.makeText(EditProfile.this, "Fancy name must not be empty", Toast.LENGTH_SHORT).show();
                               }
                               else {


                                   if (data.child("fancyName").getValue().toString().equals(textInputEditTextFancyName.getText().toString())) {
                                       //do ur stuff
                                       Toast.makeText(EditProfile.this, "Fancy Name already exists", Toast.LENGTH_SHORT).show();
                                   } else {


                                       refToSpecificUser.child("fancyName").setValue(textInputEditTextFancyName.getText().toString());
                                   }
                               }

                           }

                            catch (NullPointerException e)
                            {

                            }
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                refToSpecificUser.child("status").setValue(textInputEditTextStatus.getText().toString());


                Toast.makeText(EditProfile.this, "Saved successfully", Toast.LENGTH_SHORT).show();

                finish();

            }
        });



    }

    private void getExactLocation() {

        Geocoder geocoder = new Geocoder(EditProfile.this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            finalAddress = addresses.get(0).getAddressLine(0);
            locationEditText.setText(finalAddress);
            refToSpecificUser.child("location").setValue(finalAddress);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void requestPermission() {

        ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION},1);

    }
}
