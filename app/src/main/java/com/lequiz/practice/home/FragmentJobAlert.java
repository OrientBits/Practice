package com.lequiz.practice.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.github.ksoichiro.android.observablescrollview.ScrollUtils;
import com.lequiz.practice.R;


public class FragmentJobAlert extends Fragment implements ObservableScrollViewCallbacks {


    private ObservableScrollView mScrollView;
    private int mParallaxImageHeight;

    public FragmentJobAlert() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflaterView = inflater.inflate(R.layout.fragment_job_alert, container, false);

        HomeContainer.toolbar_card_view_2.setVisibility(View.INVISIBLE);
        HomeContainer.title_text.setText(getText(R.string.job_alerts));

        mScrollView = inflaterView.findViewById(R.id.job_alert_fragment_scroll);
        mScrollView.setScrollViewCallbacks(this);
        mParallaxImageHeight = getResources().getDimensionPixelSize(R.dimen.image_height_home_part_1);






        return inflaterView;
    }




    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        onScrollChanged(mScrollView.getCurrentScrollY(), false, false);
    }


    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {
        int baseColor = getResources().getColor(R.color.colorPrimary);
        float alpha = Math.min(1, (float) scrollY / mParallaxImageHeight);
        //HomeContainer.mToolbarView.setBackgroundColor(ScrollUtils.getColorWithAlpha(alpha-(float)0.02, baseColor));
        HomeContainer.title_text.setAlpha(alpha-(float)0.035);
    }

    @Override
    public void onDownMotionEvent() {

    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {
    }



}
