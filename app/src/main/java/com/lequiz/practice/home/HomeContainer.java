package com.lequiz.practice.home;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.google.firebase.auth.FirebaseAuth;
import com.lequiz.practice.R;
import com.lequiz.practice.base.FullScreenStatusOnly;
import com.lequiz.practice.nav_drawer.NavInviteFriends;
import com.lequiz.practice.nav_drawer.NavLeaderboard;
import com.lequiz.practice.nav_drawer.NavNotifications;
import com.lequiz.practice.nav_drawer.NavPayment;
import com.lequiz.practice.nav_drawer.NavSettings;
import com.lequiz.practice.nav_drawer.QuizFactory;
import com.loopeer.shadow.ShadowView;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.widget.Toast.LENGTH_SHORT;

public class HomeContainer extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Fragment fragment = null;
    View mToolbarView;

    boolean doubleBackToExitPressedOnce = false;
    protected DrawerLayout mDrawerLayout;
    protected ActionBarDrawerToggle mToggle;
    protected CircleImageView profile_home;
    public MenuItem menuItem;

    @SuppressLint("StaticFieldLeak")
    public static Activity fa; // finish activity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home_container);
        fa = this; // for only context


        new FullScreenStatusOnly(this);

        // toolbar setup
        mToolbarView = findViewById(R.id.toolbar);
        setSupportActionBar((Toolbar) mToolbarView);
        Objects.requireNonNull(getSupportActionBar()).setTitle(null);

        // open drawer when navigation button is tapped
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, (Toolbar) mToolbarView, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        Objects.requireNonNull(getSupportActionBar()).setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_drawer_indicator);

        // status bar height calculation
        int statusBarHeight = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = getResources().getDimensionPixelSize(resourceId);
        }
        RelativeLayout toolbarLayout = findViewById(R.id.toolbar_root_layout);
        toolbarLayout.setPadding(0, statusBarHeight, 0, 0);


        fragment = new FragmentHome();
        loadFragment(fragment);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(rOnNavigationItemSelectedListener);


        // implementing item of navigation drawer
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);


    }

    private BottomNavigationView.OnNavigationItemSelectedListener rOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.bottom_nav_home:
                    fragment = new FragmentHome();
                    loadFragment(fragment);
                    return true;

                case R.id.bottom_nav_play_random:
                    fragment = new FragmentPlayRandom();
                    loadFragment(fragment);
                    return true;

                case R.id.bottom_nav_job_alert:
                    fragment = new FragmentJobAlert();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };

    private void loadFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.home_fragment_container, fragment);
        transaction.commit();
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Press again to close LeQuiz", LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 3000);
    }


    @Override
    protected void onPostResume() {
        super.onPostResume();
        mDrawerLayout.closeDrawers();
    }


    @Override
    protected void onResume() {
        super.onResume();
        mDrawerLayout.closeDrawers();
        new FullScreenStatusOnly(this);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        menuItem = item;

        // Handle navigation view item clicks here.
        int res_id = item.getItemId();
        Handler handler = new Handler();
        switch (res_id) {
            case R.id.leaderboard:
                final Intent navLeaderboard = new Intent(HomeContainer.this, NavLeaderboard.class);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(navLeaderboard);
                    }
                }, 300);
                break;


            case R.id.payment:
                final Intent navPayment = new Intent(HomeContainer.this, NavPayment.class);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(navPayment);
                    }
                }, 300);
                break;

            case R.id.quiz_factory:
                final Intent navQuizFactory = new Intent(HomeContainer.this,QuizFactory.class);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(navQuizFactory);
                    }
                }, 300);
                break;

            case R.id.settings:
                final Intent navSettings = new Intent(HomeContainer.this, NavSettings.class);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(navSettings);
                    }
                }, 300);
                break;

            case R.id.invite_friends:
                final Intent navFriends = new Intent(HomeContainer.this, NavInviteFriends.class);

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(navFriends);
                    }
                }, 300);
                break;

            case R.id.send_feedback:
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        makeFeedBackIntent();
                    }
                }, 200);
                break;

            case R.id.about_us:
                // this section will be linked with Website directly
                break;
        }

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                DrawerLayout drawer = findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                menuItem.setCheckable(false);
                menuItem.setChecked(false);
            }
        }, 100);
        return true;

    }


    public void makeFeedBackIntent() {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto", "", null));
        String osVersion = Build.VERSION.RELEASE;
        String locale = getResources().getConfiguration().locale.getDisplayCountry();
        String manufacturerAndModal = Build.MANUFACTURER + " : " + Build.MODEL;
        String msgtxt = "-----------------------------------------------------" + "\n";
        msgtxt = msgtxt + "Support Diagnostics (Do Not Delete)" + "\n" + "-----------------------------------------------------" + "\n";
        msgtxt = msgtxt + "U: " + getString(R.string.user_name) + "\n" + "V: " + osVersion + "\n" + "M: " + manufacturerAndModal + "\n" + "S: " + Build.VERSION.SDK_INT + "\n" + "G: " + locale + "\n";
        msgtxt = msgtxt + "-----------------------------------------------------" + "\n\n";
        String subjectMsg = "Feedback From " + getString(R.string.user_name);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subjectMsg);
        String[] to = {"feedback@lequiz.com"};
        emailIntent.putExtra(Intent.EXTRA_EMAIL, to);
        emailIntent.putExtra(Intent.EXTRA_TEXT, msgtxt);
        startActivity(emailIntent);
    }


}
