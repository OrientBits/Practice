package com.lequiz.practice;
import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lequiz.practice.Word;

import java.util.ArrayList;

/**
 * Created by Ramshek Rana on 08-04-2018.
 */
public class WordAdapter extends ArrayAdapter<Word> {


    public WordAdapter(Activity context, ArrayList<Word> words) {
        super(context, 0, words);
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.category_item, parent, false);
        }
        Word currentWord = getItem(position);

        TextView categoryText = (TextView) listItemView.findViewById(R.id.category_text_view);
        categoryText.setText(currentWord.getText());



        ImageView imageView = (ImageView) listItemView.findViewById(R.id.category_image);

            imageView.setImageResource(currentWord.getImageResourceId());
            imageView.setVisibility(View.VISIBLE);



        return listItemView;
    }
}
