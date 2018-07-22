package com.lequiz.practice;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Objects;

public class NavSettings extends AppCompatActivity {
    AlertDialog alertDialogRadioButtons;
    protected Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_settings);

        toolbar = findViewById(R.id.nav_settings_toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_button_settings);
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.greenOnToolBarSettings)));

        // Edit Profile Click Event
        TextView edit_profile_on_settings=findViewById(R.id.edit_profile_on_settings);
        edit_profile_on_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentEditProfile = new Intent(NavSettings.this, EditProfileActivity.class);
                startActivity(intentEditProfile);
            }
        });
        // Night Mode Click Event
        LinearLayout night_mode_text_view = findViewById(R.id.night_mode_text_view);
        night_mode_text_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAlertDialogWithRadioButton();
            }
        });
        // Language Click Event
        LinearLayout language_text_view = findViewById(R.id.language_text_view);
       language_text_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAlertDialogWithRadioButtonForLanguage();
            }
        });




    }
    public void createAlertDialogWithRadioButton()
        {
            CharSequence[] value = {"On","Off"};
            final AlertDialog.Builder builder = new AlertDialog.Builder(NavSettings.this);
            builder.setTitle("Night Mode");
            builder.setSingleChoiceItems(value, -1, new DialogInterface.OnClickListener() {
                        @SuppressLint("SetTextI18n")
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            switch (i)
                            {
                                case 0:
                                    TextView txtOn = findViewById(R.id.on_off_textView);
                                    txtOn.setText("On");
                                    break;
                                case 1:
                                    TextView txtOff = findViewById(R.id.on_off_textView);
                                    txtOff.setText("Off");
                                    break;
                            }
                            alertDialogRadioButtons.dismiss();
                        }

                    }

            );
            alertDialogRadioButtons = builder.create();
            alertDialogRadioButtons.show();

    }
    public void createAlertDialogWithRadioButtonForLanguage()
    {
        CharSequence[] value = {"English","Hindi"};
        final AlertDialog.Builder builder = new AlertDialog.Builder(NavSettings.this);
        builder.setTitle("Language");
        builder.setSingleChoiceItems(value, -1, new DialogInterface.OnClickListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i)
                        {
                            case 0:
                                TextView txtOn = findViewById(R.id.hindi_english_textview);
                                txtOn.setText("English");
                                break;
                            case 1:
                                TextView txtOff = findViewById(R.id.hindi_english_textview);
                                txtOff.setText("Hindi");
                                break;
                        }
                        alertDialogRadioButtons.dismiss();
                    }

                }

        );
        alertDialogRadioButtons = builder.create();
        alertDialogRadioButtons.show();

    }
}
