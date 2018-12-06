package com.lequiz.practice.module;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.lequiz.practice.R;

public class WeeklyCurrentAffairsViewHolder extends RecyclerView.ViewHolder {

    public TextView title;
    public TextView desc;
    boolean clicked;

    public WeeklyCurrentAffairsViewHolder(@NonNull View itemView) {
        super(itemView);

        title = itemView.findViewById(R.id.title_text_view);
        desc = itemView.findViewById(R.id.desc_text_view);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                clicked=!clicked;
                if (clicked)
                {
                    desc.setVisibility(View.VISIBLE);
                }
                if(!clicked)
                {
                    desc.setVisibility(View.GONE);
                }


            }
        });

    }
}
