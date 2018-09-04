package com.lequiz.practice;

import android.content.Intent;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

public class MathematicsActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mathematics);

        toolbar = findViewById(R.id.mathematics_toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_button_mathematics);

        // Heading TextView gradient


        TextView learnHeaderTech = findViewById(R.id.heading_on_mathematics);
        Shader textShader = new LinearGradient(0, 0, 180, 0,
                new int[]{getResources().getColor(R.color.purpleOnMathematics), getResources().getColor(R.color.yellowOnMathematics)},
                new float[]{0, 1}, Shader.TileMode.CLAMP);
        learnHeaderTech.getPaint().setShader(textShader);

        // Hey UserName Initilization on learn section

        TextView heyUserName = findViewById(R.id.hey_user_name);
        String heyUserNameMaker = "Hey "+getString(R.string.user_first_name)+",";
        heyUserName.setText(heyUserNameMaker);



        ImageView startQuiz = findViewById(R.id.start_image_of_mathematics_quiz);
        startQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MathematicsActivity.this,MathematicsQuiz.class));
            }
        });

    }
}
