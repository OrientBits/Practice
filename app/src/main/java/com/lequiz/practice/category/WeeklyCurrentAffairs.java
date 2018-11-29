package com.lequiz.practice.category;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.github.ksoichiro.android.observablescrollview.ScrollUtils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.lequiz.practice.R;
import com.lequiz.practice.base.FullScreenStatusOnly;

import java.util.Objects;

public class WeeklyCurrentAffairs extends AppCompatActivity implements ObservableScrollViewCallbacks {

    private View mToolbarView;
    private ObservableScrollView mScrollView;
    private int mParallaxImageHeight;
    RelativeLayout toolbarLayout;
    TextView title_text;
    CardView toolbar_card_view_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_current_affairs);

        mToolbarView = findViewById(R.id.toolbar);
        setSupportActionBar((Toolbar) mToolbarView);
        getSupportActionBar().setTitle(null);
        title_text = findViewById(R.id.toolbar_title);
        title_text.setText(getResources().getText(R.string.weekly_current_affairs));
        title_text.setTextColor(getResources().getColor(R.color.black));
        title_text.setAlpha(0);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_arrow_technology);
        toolbar_card_view_2 = findViewById(R.id.toolbar_card_view_2);
        toolbar_card_view_2.setVisibility(View.INVISIBLE);

        // status bar calculation
        int statusBarHeight = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = getResources().getDimensionPixelSize(resourceId);
        }
        toolbarLayout = findViewById(R.id.toolbar_root_layout);
        mToolbarView.setPadding(0, statusBarHeight, 4, 0);
        toolbarLayout.setPadding(0,statusBarHeight,0,0);

        mScrollView = findViewById(R.id.scroll_weekly_current_affairs);
        mScrollView.setScrollViewCallbacks(this);
        mParallaxImageHeight = getResources().getDimensionPixelSize(R.dimen.image_height_home_part_1);
        new FullScreenStatusOnly(this);

    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        onScrollChanged(mScrollView.getCurrentScrollY(), false, false);
    }

    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {
        int baseColor = getResources().getColor(R.color.colorPrimary);
        float alpha = Math.min(1, (float) scrollY / mParallaxImageHeight);
        mToolbarView.setBackgroundColor(ScrollUtils.getColorWithAlpha(alpha-(float)0.02, baseColor));
        title_text.setAlpha(alpha-(float)0.035);
    }


    @Override
    public void onDownMotionEvent() {

    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {
    }


}
