package com.lequiz.practice.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lequiz.practice.R;

public class OnBoardingSliderAdapter extends PagerAdapter {

    public int[] slide_images = {
            R.drawable.onboarding1,
            R.drawable.onboarding2,
            R.drawable.onboarding3,
            R.drawable.onboarding4
    };


    public String[] slide_messages = {
            "Let's Dive deep into the world's biggest Quiz Competition",
            "Ultimate monthly winner with a single scale XP",
            "Earn with your knowledge ",
            "Daily Database updation"
    };
    Context context;
    LayoutInflater layoutInflater;

    public OnBoardingSliderAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return slide_images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (LinearLayout) object;
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.onboarding_slide_layout,container,false);

        ImageView imageView = view.findViewById(R.id.onboarding_img1);
        TextView textView = view.findViewById(R.id.onbarding1_message);

        imageView.setImageResource(slide_images[position]);
        textView.setText(slide_messages[position]);


        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);
    }
}
