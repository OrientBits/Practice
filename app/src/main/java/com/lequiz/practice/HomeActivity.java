package com.lequiz.practice;


import android.os.Build;
import android.support.annotation.NonNull;;
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
import android.view.WindowManager;
import android.widget.Toast;

import java.util.Objects;


public class HomeActivity extends AppCompatActivity {


    protected DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    protected CardView computer, mathematics, reasoning,
            english, technology, sport, special, entertainment;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        /*
        * this is used for status bar to make visible
        * */
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);


        toolbar = findViewById(R.id.category_toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Home");


        mDrawerLayout = findViewById(R.id.drawer_layout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        Objects.requireNonNull(getSupportActionBar()).setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_drawer_indicator);


        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);
        setupDrawerContent(navigationView); // default true

    } // onCreate method


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int res_if = item.getItemId();
        switch (res_if) {
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
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int res_id = menuItem.getItemId();

                switch (res_id) {
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
                    case R.id.about_us:
                        Toast.makeText(getApplicationContext(), "About us", Toast.LENGTH_LONG).show();
                        break;
                }

                mDrawerLayout.closeDrawers();
                return true;
            }
        });
    }


} // activity class
