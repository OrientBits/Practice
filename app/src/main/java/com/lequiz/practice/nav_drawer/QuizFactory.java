package com.lequiz.practice.nav_drawer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.lequiz.practice.base.FullScreenStatusOnly;
import com.lequiz.practice.R;

import java.util.ArrayList;
import java.util.Objects;

public class QuizFactory extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    protected Toolbar toolbar;
    protected Spinner spinner;
    CardView toolbar_card_view_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_quiz_factory);

        toolbar = findViewById(R.id.toolbar);
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
