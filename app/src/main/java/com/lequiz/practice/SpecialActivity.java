package com.lequiz.practice;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.Objects;

public class SpecialActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special);

        toolbar = findViewById(R.id.special_toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // Size troubleshoot Learn Section
        Drawable drawable= getResources().getDrawable(R.drawable.back_button_special);
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        Drawable newdrawable = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 130, 130, true));

        getSupportActionBar().setHomeAsUpIndicator(newdrawable);

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
