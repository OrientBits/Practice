package com.lequiz.practice;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toolbar;

import com.hbb20.CountryCodePicker;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditProfileActivity extends AppCompatActivity {

   @Override
   protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_edit_profile);

       android.support.v7.widget.Toolbar toolbar = findViewById(R.id.edit_profile_toolbar);
       setSupportActionBar(toolbar);
       Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
       getSupportActionBar().setDisplayShowHomeEnabled(true);
       getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_button_settings);
       toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
       TextView textViewChangeAvatar = findViewById(R.id.changeAvatar);
       EditText editTextOfLocationOnEditProfile = findViewById(R.id.location_edit_text_on_profile);
       CountryCodePicker codePickerOnEditProfile =  findViewById(R.id.countryCodePickerOnEditProfile);
       String Country=codePickerOnEditProfile.getSelectedCountryName();
       editTextOfLocationOnEditProfile.setText(Country);

}

}
