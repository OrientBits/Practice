package com.lequiz.practice.activity;

import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.lequiz.practice.base.FullScreenStatusOnly;
import com.lequiz.practice.R;

import java.util.Objects;

public class SpecialActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special);

        // Set transparency
        FullScreenStatusOnly fullScreenStatusOnly = new FullScreenStatusOnly(this);

        toolbar = findViewById(R.id.special_toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_button_special);

        // Heading TextView gradient


      TextView learnHeaderTech = findViewById(R.id.heading_on_special);
        Shader textShader = new LinearGradient(0, 0, 180, 0,
                new int[]{getResources().getColor(R.color.redOnSpecial), getResources().getColor(R.color.purpleOnSpecial)},
                new float[]{0, 1}, Shader.TileMode.CLAMP);
        learnHeaderTech.getPaint().setShader(textShader);

    //    Hey UserName Initillization on learn section

//        TextView heyUserName = findViewById(R.id.hey_user_name);
//        String heyUserNameMaker = "Hey "+getString(R.string.user_first_name)+",";
//          heyUserName.setText(heyUserNameMaker);
    }
}
