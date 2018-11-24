package com.lequiz.practice.module;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lequiz.practice.R;

public class LeaderBoardViewHolder extends RecyclerView.ViewHolder {

    public TextView userName;
    public TextView userXp;
    public ImageView userImg;
    public TextView ranking;


    public LeaderBoardViewHolder(@NonNull View itemView) {
        super(itemView);

        userName = itemView.findViewById(R.id.user_name_on_rows_leaderboard);
        userXp = itemView.findViewById(R.id.xp_on_rows_leaderboard);
        userImg = itemView.findViewById(R.id.profile_pic_on_rows_leaderboard);
        ranking = itemView.findViewById(R.id.ranking_on_rows_leaderboard);
    }


}
