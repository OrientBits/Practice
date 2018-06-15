package com.lequiz.practice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class HomeActivity extends AppCompatActivity {
    private CardView currentAffairs,computer,mathematics,reasoning,chemistry,
            physics,biology,geography,history,entertainment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        currentAffairs = findViewById(R.id.current_affairs_card_view);
        computer = findViewById(R.id.computer_card_view);
        mathematics = findViewById(R.id.mathematics_card_view);
        reasoning = findViewById(R.id.reasoning_card_view);
        chemistry = findViewById(R.id.chemistry_card_view);
        physics = findViewById(R.id.physics_card_view);
        biology = findViewById(R.id.biology_card_view);
        geography = findViewById(R.id.geography_card_view);
        history = findViewById(R.id.history_card_view);
        entertainment = findViewById(R.id.entertainment_card_view);

        currentAffairs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,CurrentAffairsActivity.class);
                startActivity(intent);
            }
        });

        computer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,ComputerActivity.class);
                startActivity(intent);
            }
        });

        mathematics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,MathematicsActivity.class);
                startActivity(intent);
            }
        });

        reasoning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,ReasoningActivity.class);
                startActivity(intent);
            }
        });

        chemistry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,ChemistryActivity.class);
                startActivity(intent);
            }
        });

        physics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,PhysicsActivity.class);
                startActivity(intent);
            }
        });

        biology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,BiologyActivity.class);
                startActivity(intent);
            }
        });

        geography.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,GeographyActivity.class);
                startActivity(intent);
            }
        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,HistoryActivity.class);
                startActivity(intent);
            }
        });

        entertainment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,EntertainmentActivity.class);
                startActivity(intent);
            }
        });
    }
}
