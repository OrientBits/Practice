package com.lequiz.practice.nav_drawer;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.lequiz.practice.base.FullScreenStatusOnly;
import com.lequiz.practice.R;

import java.util.Objects;

public class NavInviteFriends extends AppCompatActivity {

    protected Button shareButton,emailButton,smsButton,whatsappButton;
    protected Toolbar toolbar;
    CardView toolbar_card_view_2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_invite_friends);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back_arrow_default);
        toolbar_card_view_2 = findViewById(R.id.toolbar_card_view_2);
        toolbar_card_view_2.setVisibility(View.INVISIBLE);

        // for status bar color
        FullScreenStatusOnly fullScreenStatusOnly = new FullScreenStatusOnly(this);


        shareButton =  findViewById(R.id.share_button);
        shareButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                try {
                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("text/plain");
                    i.putExtra(Intent.EXTRA_SUBJECT, "Le-Quiz The Learning Earning app");
                    String MWLink = "\nRefer this code and get 10 ₹ after successful Installation \n\n";
                    MWLink = MWLink + "https://play.google.com/store/apps/details?id=com.quizup.core \n\n";
                    i.putExtra(Intent.EXTRA_TEXT, MWLink);
                    startActivity(Intent.createChooser(i, "Le-quiz The Learning Earning quiz app"));
                } catch (Exception e) {
                    //e.toString();
                }
            }
        });


        emailButton = findViewById(R.id.email_button);
        emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto","", null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Le-Quiz The Learning Earning app");
                String MWLink = "\nRefer this code and get 10 ₹ after successful Installation \n\n";
                MWLink = MWLink + "https://play.google.com/store/apps/details?id=com.quizup.core \n\n";
                emailIntent.putExtra(Intent.EXTRA_TEXT, MWLink);
                startActivity(emailIntent);
            }
        });


        smsButton = findViewById(R.id.sms_button);
        smsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                sendIntent.setData(Uri.parse("sms:"));
                String MWLink = "\nRefer this code and get 10 ₹ after successful Installation \n\n";
                MWLink = MWLink + "https://play.google.com/store/apps/details?id=com.quizup.core \n\n";
                sendIntent.putExtra(Intent.EXTRA_TEXT,MWLink);
                startActivity(sendIntent);
            }
        });


        whatsappButton = findViewById(R.id.whatsapp_button);
        whatsappButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                String MWLink = "\nRefer this code and get 10 ₹ after successful Installation \n\n";
                MWLink = MWLink + "https://play.google.com/store/apps/details?id=com.quizup.core \n\n";
                sendIntent.putExtra(Intent.EXTRA_TEXT, MWLink);
                sendIntent.setType("text/plain");
                sendIntent.setPackage("com.whatsapp");
                if (sendIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(sendIntent);
                }
            }
        });



    }
}
