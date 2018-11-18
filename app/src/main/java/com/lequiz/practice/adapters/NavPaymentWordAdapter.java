package com.lequiz.practice.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.lequiz.practice.R;
import com.lequiz.practice.module.WebViewLayout;

import java.util.ArrayList;

public class NavPaymentWordAdapter extends ArrayAdapter<NavPaymentWord>{

    public NavPaymentWordAdapter(@NonNull Context context, ArrayList<NavPaymentWord> arrayList) {
        super(context, 0,arrayList);
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.nav_payment_list_user, parent, false);
        }

        NavPaymentWord navPaymentWord = getItem(position);

        TextView nView = listItemView.findViewById(R.id.user_name_in_payment_textView);
        assert navPaymentWord != null;
        nView.setText(navPaymentWord.getName());

        TextView xpView = listItemView.findViewById(R.id.total_xp_in_payment_textView);
        xpView.setText("XP reached = "+navPaymentWord.getTotalXp());

        TextView prizeView = listItemView.findViewById(R.id.prize_amount_in_Payment_textView);
        prizeView.setText("₹ "+navPaymentWord.getPrizeAmount());

        TextView dateView = listItemView.findViewById(R.id.date_of_getting_prize_textView);
        dateView.setText(NavPaymentWord.getDate());

       listItemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(view.getContext(), WebViewLayout.class);
               String sourceUrlAgain = "https://firebasestorage.googleapis.com/v0/b/lequiz-4abd1.appspot.com/o/categoryComputer%2Flequiz.html?alt=media&token=350609e7-a738-4a1d-8bb9-5423a301a558";
               intent.putExtra("sourceUrlAgain",sourceUrlAgain);
               view.getContext().startActivity(intent);
           }
       });

        return listItemView;

    }
}
