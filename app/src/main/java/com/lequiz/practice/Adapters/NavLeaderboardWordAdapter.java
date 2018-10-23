package com.lequiz.practice.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.lequiz.practice.R;
import java.util.ArrayList;
import de.hdodenhof.circleimageview.CircleImageView;

public class NavLeaderboardWordAdapter extends ArrayAdapter<NavLeaderboardWord>{

    public NavLeaderboardWordAdapter(@NonNull Context context, ArrayList<NavLeaderboardWord> arrayList) {
        super(context, 0,arrayList);
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.activity_nav_leaderboard_list_item, parent, false);
        }

        NavLeaderboardWord navLeaderboardWord = getItem(position);

        CircleImageView nCircleImageView = listItemView.findViewById(R.id.leaderboard_profile_picture);
        assert navLeaderboardWord != null;
        nCircleImageView.setImageResource(navLeaderboardWord.getImageId());


        TextView nameView = listItemView.findViewById(R.id.leaderboard_user_name);
        nameView.setText(navLeaderboardWord.getName());

        TextView xpTextView = listItemView.findViewById(R.id.leaderboard_total_xp);
        xpTextView.setText("+"+navLeaderboardWord.getXp());

        TextView rTextView = listItemView.findViewById(R.id.leaderboard_ranking);
        rTextView.setText(" "+navLeaderboardWord.getRanking());

        return listItemView;

    }
}
