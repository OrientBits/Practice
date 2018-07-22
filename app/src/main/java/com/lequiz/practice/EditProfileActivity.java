package com.lequiz.practice;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toolbar;

import com.hbb20.CountryCodePicker;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditProfileActivity extends AppCompatActivity {

    protected CountryCodePicker countryCodePicker;
   @Override
   protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_edit_profile);

       android.support.v7.widget.Toolbar toolbar = findViewById(R.id.edit_profile_toolbar);
       setSupportActionBar(toolbar);
       Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
       getSupportActionBar().setDisplayShowHomeEnabled(true);


// for status bar color
       Window window = getWindow();
       window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
       window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
       window.setStatusBarColor(ContextCompat.getColor(EditProfileActivity.this,R.color.darkGreenOnToolBarSettings));

//       TextView textViewChangeAvatar = findViewById(R.id.changeAvatar);
       final EditText editTextOfLocationOnEditProfile = findViewById(R.id.location_edit_text_on_profile);
       countryCodePicker = findViewById(R.id.countryCodePickerOnEditProfile);
       countryCodePicker.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
           @Override
           public void onCountrySelected() {
               String Country= countryCodePicker.getSelectedCountryName();
               editTextOfLocationOnEditProfile.setText(Country);
           }
       });




}

}
