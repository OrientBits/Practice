package com.lequiz.practice;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Calendar;

public class NavPaymentWordAdapter extends ArrayAdapter<NavPaymentWord>{

    NavPaymentWordAdapter(@NonNull Context context, ArrayList<NavPaymentWord> arrayList) {
        super(context, 0,arrayList);
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.activity_nav_payment_list_user, parent, false);
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

        return listItemView;

    }
}
