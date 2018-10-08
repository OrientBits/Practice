package com.lequiz.practice;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.Objects;

public class ProfileActivity extends AppCompatActivity {

    protected Toolbar toolbar;
    private TextView editProfileText;
    CoordinatorLayout rootLayout;


    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        toolbar = findViewById(R.id.profile_toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("");

        final CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar_layout);
        AppBarLayout appBarLayout = findViewById(R.id.app_bar);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = true;
            int scrollRange = -1;
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0)
                {
                    getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_button_settings2);
                    collapsingToolbarLayout.setTitle(getTitle());
                    collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(android.R.color.tab_indicator_text));
                    isShow = true;
                } else if(isShow) {
                    getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_button_settings);
                    collapsingToolbarLayout.setTitle(" ");// carefull there should a space between double quote
                    isShow = false;
                }
            }
        });


        //Set transparency
        Window w = getWindow(); // in Activity's onCreate() for instance
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        rootLayout = findViewById(R.id.profile_second_root_Relative_layout);
        ViewGroup.MarginLayoutParams p = ( ViewGroup.MarginLayoutParams) rootLayout.getLayoutParams();
        p.setMargins(0, 0,0, getSoftButtonsBarSizePort(this));
        rootLayout.requestLayout();
        w.setNavigationBarColor(R.color.black_for_soft_navigation);



//        editProfileText = findViewById(R.id.edit_profile_data);
//        editProfileText.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Dialog dialog=new Dialog(ProfileActivity.this,android.R.style.Theme_Black_NoTitleBar_Fullscreen);
//                dialog.setContentView(R.layout.activity_nav_invite_friends);
//                dialog.show();
//            }
//        });

    }



    public static int getSoftButtonsBarSizePort(Activity activity)
    {
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int usableHeight = metrics.heightPixels;
        activity.getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
        int realHeight = metrics.heightPixels;
        if (realHeight > usableHeight) {
            realHeight = realHeight - usableHeight;
            return realHeight;
        }
        return 0;
    }

}