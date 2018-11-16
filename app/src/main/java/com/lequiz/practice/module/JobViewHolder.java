package com.lequiz.practice.module;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.lequiz.practice.R;

public class JobViewHolder extends RecyclerView.ViewHolder {

    public TextView jobTitle, startDate, endDate;
    public String applyOnlineLink;

    public JobViewHolder(final View itemView) {
        super(itemView);

        jobTitle = itemView.findViewById(R.id.job_title_on_card_view_jobs);
        startDate = itemView.findViewById(R.id.job_starting_date);
        endDate = itemView.findViewById(R.id.job_ending_date);
    //    applyOnlineLink = itemView.findViewById(R.id.apply_online_text_view_on_jobs);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(), WebViewLayout.class);
                String sourceUrlAgain = applyOnlineLink;
                intent.putExtra("sourceUrlAgain",sourceUrlAgain);
                view.getContext().startActivity(intent);

            }
        });


    }
}
