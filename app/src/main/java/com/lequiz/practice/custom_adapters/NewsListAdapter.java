package com.lequiz.practice.custom_adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lequiz.practice.R;
import com.lequiz.practice.custom_classes.News;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        News currentNewsItem = newsArrayList.get(position);

        Picasso.get().load(currentNewsItem.getNewsImageUrl()).resize(190,160).centerCrop().into(holder.img);

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

        public ViewHolder(View view)
        {
            super(view);
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
