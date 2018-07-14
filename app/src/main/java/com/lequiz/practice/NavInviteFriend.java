package com.lequiz.practice;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import java.util.Objects;

public class NavInviteFriend extends AppCompatActivity {

    protected Toolbar toolbar;
    private Button shareButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_invite_friend);

        toolbar = findViewById(R.id.nav_invite_friend_toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        shareButton =  findViewById(R.id.share_button);
        shareButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                try {
                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("text/plain");
                    i.putExtra(Intent.EXTRA_SUBJECT, "Le Quiz");
                    String sAux = "\nRefer this code and get 10 ₹ after successful Installation \n\n";
                    sAux = sAux + "https://play.google.com/store/apps/details?id=com.quizup.core \n\n";
                    i.putExtra(Intent.EXTRA_TEXT, sAux);
                    startActivity(Intent.createChooser(i, "Invite Friend"));
                } catch (Exception e) {
                    //e.toString();
                }
            }
        });





    }
}
