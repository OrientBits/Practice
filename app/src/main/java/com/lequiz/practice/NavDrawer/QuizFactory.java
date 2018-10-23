package com.lequiz.practice.NavDrawer;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.lequiz.practice.Base.FullScreenStatusOnly;
import com.lequiz.practice.R;

import java.util.ArrayList;
import java.util.Objects;

public class QuizFactory extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    protected Toolbar toolbar;
    protected Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_factory);

        toolbar = findViewById(R.id.quiz_factory_toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        // for status bar color
        FullScreenStatusOnly fullScreenStatusOnly = new FullScreenStatusOnly(this);

//        for spinner chooser
        spinner = findViewById(R.id.spinner_to_choose_category);

        spinner.setOnItemSelectedListener(this);

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
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

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
