package com.lequiz.practice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.GridView;
import java.util.ArrayList;

public class CurrentAffairsActivity extends AppCompatActivity {

    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_list);

        toolbar = findViewById(R.id.category_toolbar);
        setSupportActionBar(toolbar);


        ArrayList<Word> arrayList = new ArrayList<>();
        arrayList.add(new Word("Current Affairs",R.drawable.current_affairs));
        arrayList.add(new Word("Computer",R.drawable.computer));
        arrayList.add(new Word("Mathematics",R.drawable.mathematics));
        arrayList.add(new Word("Reasoning",R.drawable.reasoning));
        arrayList.add(new Word("General Science",R.drawable.science));
        arrayList.add(new Word("English",R.drawable.english));
        arrayList.add(new Word("Technology",R.drawable.technology));
        arrayList.add(new Word("Sport",R.drawable.sport));
        arrayList.add(new Word("Special",R.drawable.special));
        arrayList.add(new Word("Entertainment",R.drawable.entertainment));

        WordAdapter wordAdapter = new WordAdapter(this,arrayList);
        GridView gridView = findViewById(R.id.list);
        gridView.setAdapter(wordAdapter);
    }
}
