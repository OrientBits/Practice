package com.lequiz.practice;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

public class OnBoarding extends AppCompatActivity {

    private ViewPager mSlidViewPager;
    private LinearLayout mDotLayout;
    private OnBoardingSliderAdapter sliderAdapter;
    private TextView[] mDots = new TextView[4];
    public int[] slide_activity_background = {
            R.drawable.onboarding_background1,
            R.drawable.onboarding_background2,
            R.drawable.onboarding_background3,
            R.drawable.onboarding_background4
    };
    private int pc = 0;
    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDotIndicator(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
    private SharedPreferenceConfig sharedPreferenceConfig;
    private LinearLayout linearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);

//        // full screen
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        sharedPreferenceConfig = new SharedPreferenceConfig(getApplicationContext());

        if (sharedPreferenceConfig.readLoginStatus()) {
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        }


        linearLayout = findViewById(R.id.root_onboarding);
        mSlidViewPager = findViewById(R.id.viewPagerLayout);
        mDotLayout = findViewById(R.id.three_dots_layout);
        sliderAdapter = new OnBoardingSliderAdapter(this);
        mSlidViewPager.setAdapter(sliderAdapter);
        addDotIndicator(0);
        mSlidViewPager.addOnPageChangeListener(viewListener);
    }

    public void addDotIndicator(int pos) {
        for (int i = 0; i < 4; i++) {

            if (pc == 0)
                mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextColor(getResources().getColor(R.color.transparent_white));
            mDots[i].setTextSize(45);
            if (pc == 0)
                mDotLayout.addView(mDots[i]);
        }

        if (mDots.length > 0) {
            mDots[pos].setTextColor(getResources().getColor(R.color.white));
            linearLayout.setBackgroundResource(slide_activity_background[pos]);
        }
        pc++;
    }

    public void signInClickEvent(View view) {
        startActivity(new Intent(this, Login.class));
    }
}
