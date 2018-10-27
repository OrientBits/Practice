package com.lequiz.practice.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.lequiz.practice.activity.WebViewLayout;
import com.lequiz.practice.R;
import com.lequiz.practice.custom_classes.News;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.ViewHolder> {

    Context context;
    ArrayList<News> newsArrayList;

    public NewsListAdapter(Context context, ArrayList<News> newsArrayList) {
        this.context = context;
        this.newsArrayList = newsArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.custom_row_for_news, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)  {
        final News currentNewsItem = newsArrayList.get(position);
        holder.parentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(context, WebViewLayout.class);
               String sourceUrlAgain = currentNewsItem.getSourceUrl();
               intent.putExtra("sourceUrlAgain",sourceUrlAgain);
               context.startActivity(intent);


            }

        });

       Picasso.get().load(currentNewsItem.getNewsImageUrl()).placeholder(R.drawable.default_image_loading).centerCrop().resize(190,160).centerCrop().into(holder.img);


        holder.newsHeadline.setText(currentNewsItem.getNewsHeadlines());
        holder.newsTime.setText(currentNewsItem.getNewsTime());



    }

    @Override
    public int getItemCount() {
        return newsArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView newsHeadline;
        private TextView newsTime;
        private ImageView img;
        private View parentView;


        public ViewHolder(View view)
        {

            super(view);
            this.parentView=view;
            this.newsHeadline = view.findViewById(R.id.current_affairs_news_title);
            this.newsTime = view.findViewById(R.id.current_affairs_news_time);
            this.img = view.findViewById(R.id.current_affairs_news_image);

        }


    }
    public void addAll(ArrayList<News> data)
    {
        newsArrayList = data;
    }
}
