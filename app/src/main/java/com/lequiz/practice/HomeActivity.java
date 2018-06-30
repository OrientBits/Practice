package com.lequiz.practice;


import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {


    protected DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    protected CardView currentAffairs, computer, mathematics, reasoning, science,
            english, geography, history, technology, sport, special, entertainment;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_list);

        toolbar = findViewById(R.id.category_toolbar);
        setSupportActionBar(toolbar);


        mDrawerLayout = findViewById(R.id.drawer_layout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);

        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }


        ArrayList<Word> arrayList = new ArrayList<>();
        arrayList.add(new Word("Current Affairs", R.drawable.current_affairs));
        arrayList.add(new Word("Computer", R.drawable.computer));
        arrayList.add(new Word("Mathematics", R.drawable.mathematics));
        arrayList.add(new Word("Reasoning", R.drawable.reasoning));
        arrayList.add(new Word("General Science", R.drawable.science));
        arrayList.add(new Word("English", R.drawable.english));
        arrayList.add(new Word("Technology", R.drawable.technology));
        arrayList.add(new Word("Sport", R.drawable.history));// here should be sport image but i'm using history
        arrayList.add(new Word("Special", R.drawable.special));
        arrayList.add(new Word("Entertainment", R.drawable.entertainment));

        WordAdapter wordAdapter = new WordAdapter(this, arrayList);
        GridView gridView = findViewById(R.id.list);
        gridView.setAdapter(wordAdapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int res_if = item.getItemId();
        switch (res_if)
        {
            case R.id.profile:
                Toast.makeText(getApplicationContext(), "Profile", Toast.LENGTH_LONG).show();
                break;
            case R.id.notifications:
                Toast.makeText(getApplicationContext(), "Notifications", Toast.LENGTH_LONG).show();
                break;
            case R.id.leaderboard:
                Toast.makeText(getApplicationContext(), "LeaderBoard", Toast.LENGTH_LONG).show();
                break;
            case R.id.lesson_factory:
                Toast.makeText(getApplicationContext(), "Lesson Factory", Toast.LENGTH_LONG).show();
                break;
            case R.id.quiz_factory:
                Toast.makeText(getApplicationContext(), "Quiz Factory", Toast.LENGTH_LONG).show();
                break;
            case R.id.invite_friends:
                Toast.makeText(getApplicationContext(), "Invite Friends", Toast.LENGTH_LONG).show();
                break;
            case R.id.rate:
                Toast.makeText(getApplicationContext(), "Rate us", Toast.LENGTH_LONG).show();
                break;
            case R.id.settings:
                Toast.makeText(getApplicationContext(), "Settings", Toast.LENGTH_LONG).show();
                break;
        }
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return true;
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        int res_id = menuItem.getItemId();

                        switch (res_id)
                        {
                            case R.id.profile:
                                Toast.makeText(getApplicationContext(), "Profile", Toast.LENGTH_LONG).show();
                                break;
                            case R.id.home_activity:
                                Toast.makeText(getApplicationContext(), "Home", Toast.LENGTH_LONG).show();
                                break;
                            case R.id.leaderboard:
                                Toast.makeText(getApplicationContext(), "Leaderboard", Toast.LENGTH_LONG).show();
                                break;
                            case R.id.notifications:
                                Toast.makeText(getApplicationContext(), "Notifications", Toast.LENGTH_LONG).show();
                                break;
                            case R.id.payment:
                                Toast.makeText(getApplicationContext(), "Payment", Toast.LENGTH_LONG).show();
                                break;
                            case R.id.settings:
                                Toast.makeText(getApplicationContext(), "Settings", Toast.LENGTH_LONG).show();
                                break;
                            case R.id.invite_friends:
                                Toast.makeText(getApplicationContext(), "Invite Friends", Toast.LENGTH_LONG).show();
                                break;
                            case R.id.feedback:
                                Toast.makeText(getApplicationContext(), "Feedback", Toast.LENGTH_LONG).show();
                                break;
                            case R.id.help:
                                Toast.makeText(getApplicationContext(), "Help", Toast.LENGTH_LONG).show();
                                break;
                        }


                        mDrawerLayout.closeDrawers();

                        return true;
                    }
                });
    }
}
