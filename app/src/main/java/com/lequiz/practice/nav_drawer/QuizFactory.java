package com.lequiz.practice.nav_drawer;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lequiz.practice.base.FullScreenStatusOnly;
import com.lequiz.practice.R;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class QuizFactory extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    protected Toolbar toolbar;
    protected Spinner spinner;
    CardView toolbar_card_view_2;
    String quizFactoryCategory;
    boolean noCategoryChoosen=true;
    String questionToSubmit;
    FirebaseAuth mAuth;
    String currentDateTimeString;
    String fullNameOfTheUserWhoSubmitted;
    boolean noRadioSelected=true;
    long databaseChildCount;
    Button submitButtonOnQuizFactory;
    String fullName,fancyName,firstName,lastName, currentDate;
    String correctOptionToSubmit;
    RadioGroup radioGroupForCorrectAnswers;
    EditText questionToSubmitEditText,option1ToSubmitEditText,option2ToSubmitEditText,option3ToSubmitEditText,option4ToSubmitEditText;
    DatabaseReference databaseReferenceToQuestions;
    DatabaseReference databaseReferenceToSpecificUsers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_quiz_factory);
        mAuth = FirebaseAuth.getInstance();

        databaseReferenceToQuestions = FirebaseDatabase.getInstance().getReference("Questions");
        databaseReferenceToSpecificUsers = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid());

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Finding current date in android studio

        currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());

        databaseReferenceToSpecificUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                try{
                    fancyName=dataSnapshot.child("fancyName").getValue().toString();
                    fullName=fancyName;
                }
                catch (NullPointerException e)
                {
                    try{
                    firstName = dataSnapshot.child("firstname").getValue().toString();
                    fullName = firstName;
                    }
                    catch (NullPointerException f)
                    {

                    }
                    try
                    {
                        lastName = dataSnapshot.child("lastName").getValue().toString();
                        fullName+=lastName;
                    }
                    catch (NullPointerException g)
                    {

                    }
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





















        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////




        databaseReferenceToQuestions.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                databaseChildCount=dataSnapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        toolbar = findViewById(R.id.toolbar);
        questionToSubmitEditText = findViewById(R.id.question_by_user);
        option1ToSubmitEditText = findViewById(R.id.editText1_for_correct);
        option2ToSubmitEditText = findViewById(R.id.editText2_for_correct);
        option3ToSubmitEditText = findViewById(R.id.editText3_for_correct);
        option4ToSubmitEditText = findViewById(R.id.editText4_for_correct);
        submitButtonOnQuizFactory = findViewById(R.id.submit_button_on_quiz_factory);


        radioGroupForCorrectAnswers = findViewById(R.id.radio_for_correct_answers);

        submitButtonOnQuizFactory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(questionToSubmitEditText.getText()))
                {
                    questionToSubmitEditText.setError("Option1 is required");
                    questionToSubmitEditText.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(option1ToSubmitEditText.getText()))
                {
                    option1ToSubmitEditText.setError("Option1 is required");
                    option1ToSubmitEditText.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(option2ToSubmitEditText.getText()))
                {
                    option1ToSubmitEditText.setError("Option2 is required");
                    option1ToSubmitEditText.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(option3ToSubmitEditText.getText()))
                {
                    option1ToSubmitEditText.setError("Option3 is required");
                    option1ToSubmitEditText.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(option4ToSubmitEditText.getText()))
                {
                    option1ToSubmitEditText.setError("Option4 is required");
                    option1ToSubmitEditText.requestFocus();
                    return;
                }
                if(noRadioSelected)
                {
                    Snackbar.make(v,"You must select the correct answer", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if(noCategoryChoosen)
                {
                    Snackbar.make(v,"You must select category also", Snackbar.LENGTH_SHORT).show();
                    return;
                }

                questionToSubmit=questionToSubmitEditText.getText().toString();
                long numberToCreate=databaseChildCount+1;
                String numberToMakeNode = Long.toString(numberToCreate);

                databaseReferenceToQuestions.child(numberToMakeNode).child("question").setValue(questionToSubmit);
                databaseReferenceToQuestions.child(numberToMakeNode).child("category").setValue(quizFactoryCategory);
                databaseReferenceToQuestions.child(numberToMakeNode).child("submittedOn").setValue(currentDateTimeString);
                databaseReferenceToQuestions.child(numberToMakeNode).child("correctAnswer").setValue(correctOptionToSubmit);
                databaseReferenceToQuestions.child(numberToMakeNode).child("option1").setValue(option1ToSubmitEditText.getText().toString());
                databaseReferenceToQuestions.child(numberToMakeNode).child("option2").setValue(option2ToSubmitEditText.getText().toString());
                databaseReferenceToQuestions.child(numberToMakeNode).child("option3").setValue(option3ToSubmitEditText.getText().toString());
                databaseReferenceToQuestions.child(numberToMakeNode).child("option4").setValue(option4ToSubmitEditText.getText().toString());
                databaseReferenceToQuestions.child(numberToMakeNode).child("submittedBy").setValue(fullName);
                finish();
                Toast.makeText(getApplicationContext(), "Your Question Submitted Successfully", Toast.LENGTH_SHORT).show();




            }
        });


        radioGroupForCorrectAnswers.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch(checkedId)
                {
                    case R.id.radio_button_option1: correctOptionToSubmit=option1ToSubmitEditText.getText().toString();
                    noRadioSelected=false;
                        break;
                    case R.id.radio_button_option2: correctOptionToSubmit=option2ToSubmitEditText.getText().toString();
                        noRadioSelected=false;
                        break;
                    case R.id.radio_button_option3: correctOptionToSubmit=option3ToSubmitEditText.getText().toString();
                        noRadioSelected=false;
                    break;
                    case R.id.radio_button_option4: correctOptionToSubmit=option4ToSubmitEditText.getText().toString();
                        noRadioSelected=false;
                        break;

                }

            }
        });


        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_arrow_default);
        toolbar_card_view_2 = findViewById(R.id.toolbar_card_view_2);
        toolbar_card_view_2.setVisibility(View.INVISIBLE);
        // for status bar color

        FullScreenStatusOnly fullScreenStatusOnly = new FullScreenStatusOnly(this);

//        for spinner chooser
        spinner = findViewById(R.id.spinner_to_choose_category);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch(position)
                {

                    case 1: quizFactoryCategory = "currentAffairs";
                    noCategoryChoosen=false;
                    break;
                    case 2: quizFactoryCategory = "computer";
                        noCategoryChoosen=false;
                    break;
                    case 3: quizFactoryCategory = "mathematics";
                        noCategoryChoosen=false;
                    break;
                    case 4: quizFactoryCategory = "reasoning";
                        noCategoryChoosen=false;
                    break;
                    case 5: quizFactoryCategory = "generalScience";
                        noCategoryChoosen=false;
                    break;
                    case 6: quizFactoryCategory = "english";
                        noCategoryChoosen=false;
                    break;
                    case 7: quizFactoryCategory = "technology";
                        noCategoryChoosen=false;
                    break;
                    case 8: quizFactoryCategory = "sports";
                        noCategoryChoosen=false;
                    break;
                    case 10: quizFactoryCategory = "entertainment";
                        noCategoryChoosen=false;
                    break;

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // spinner drop down elements or categories
        ArrayList<String> categorySelecter = new ArrayList<>();
        categorySelecter.add("Choose Category");
        categorySelecter.add("Current Affairs");
        categorySelecter.add("Computer");
        categorySelecter.add("Mathematics");
        categorySelecter.add("Reasoning");
        categorySelecter.add("General Science");
        categorySelecter.add("English");
        categorySelecter.add("Technology");
        categorySelecter.add("Sports");
        categorySelecter.add("Special");
        categorySelecter.add("Entertainment");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categorySelecter);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(R.layout.spinner_item_quiz_factory);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        // here as item action to be performed

    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


}
