package com.lequiz.practice.Module;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lequiz.practice.Module.ArrayListForHeadlinesAndImage;
import com.lequiz.practice.R;

import java.util.List;

public  class CustomArrayAdapterForCurrentAffairs extends ArrayAdapter<ArrayListForHeadlinesAndImage> {
    Context context;
    int resource;
    List<ArrayListForHeadlinesAndImage> customArrayList;
    public CustomArrayAdapterForCurrentAffairs(@NonNull Context context, int resource, List<ArrayListForHeadlinesAndImage> arrayListFor ) {
        super(context, R.layout.custom_row_current_affairs ,arrayListFor);
        this.context = context;
        this.resource = resource;
        this.customArrayList = arrayListFor;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.custom_row_current_affairs,null);
        TextView newsHeadlines =view.findViewById(R.id.headlines);
        ImageView besideHeadlines = view.findViewById(R.id.image_beside_headlines);
        ArrayListForHeadlinesAndImage arrayListForHeadlinesAndImage = customArrayList.get(position);
        newsHeadlines.setText(arrayListForHeadlinesAndImage.getHeadlines());
        besideHeadlines.setImageResource(R.drawable.a2);
        return view;
    }
}
