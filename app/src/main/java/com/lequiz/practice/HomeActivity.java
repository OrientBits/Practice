package com.lequiz.practice;



import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;


public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    protected DrawerLayout mDrawerLayout;
    protected ActionBarDrawerToggle mToggle;
    protected CircleImageView  profile_home;
    protected ImageView profile_header;
    protected CardView currentAffairs, computer, mathematics, reasoning, generalScience, english, technology, sports, special, entertainment;
    Toolbar toolbar;
    boolean doubleBackToExitPressedOnce = false;
    @SuppressLint("StaticFieldLeak")
    public static Activity fa; // finish activity
    public MenuItem menuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        fa = this; // for only context


        // toolbar implementation
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle(null);

        // user name on home page gradient
        userNameInGradient();


        // open drawer when navigation button is tapped
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

//        Objects.requireNonNull(getSupportActionBar()).setHomeButtonEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_drawer_indicator);


        // implementing item of navigation drawer
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);
        profile_header = headerView.findViewById(R.id.profile_image);



        currentAffairs = findViewById(R.id.current_affairs_card_view);
        computer = findViewById(R.id.computer_card_view);
        mathematics = findViewById(R.id.mathematics_card_view);
        reasoning = findViewById(R.id.reasoning_card_view);
        generalScience = findViewById(R.id.general_science_card_view);
        english = findViewById(R.id.english_card_view);
        technology = findViewById(R.id.technology_card_view);
        sports = findViewById(R.id.sports_card_view);
        special = findViewById(R.id.special_card_view);
        entertainment = findViewById(R.id.entertainment_card_view);
        profile_home = findViewById(R.id.profile_home);

        currentAffairs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, CurrentAffairsActivity.class);
                startActivity(intent);
            }
        });
        computer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ComputerActivity.class);
                startActivity(intent);
            }
        });
        mathematics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, MathematicsActivity.class);
                startActivity(intent);
            }
        });
        reasoning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ReasoningActivity.class);
                startActivity(intent);
            }
        });
        generalScience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, GeneralScienceActivity.class);
                startActivity(intent);
            }
        });
        english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, EnglishActivity.class);
                startActivity(intent);
            }
        });
        technology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, TechnologyActivity.class);
                startActivity(intent);
            }
        });
        sports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, SportsActivity.class);
                startActivity(intent);
            }
        });
        special.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, SpecialActivity.class);
                startActivity(intent);
            }
        });
        entertainment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, EntertainmentActivity.class);
                startActivity(intent);
            }
        });

        profile_header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent PA = new Intent(HomeActivity.this, ProfileActivity.class);
                startActivity(PA);
                mDrawerLayout.closeDrawer(GravityCompat.START);
            }
        });
        profile_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent PA = new Intent(HomeActivity.this, ProfileActivity.class);
                startActivity(PA);

            }
        });

    } // onCreate method


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
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
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
        switch (res_if) {
            case R.id.profile:
                Intent profileA = new Intent(HomeActivity.this, ProfileActivity.class);
                startActivity(profileA);
                break;
            case R.id.notifications:
                Intent NavNotification = new Intent(HomeActivity.this, NavNotifications.class);
                startActivity(NavNotification);
                break;
            case R.id.leaderboard:
                Intent NavLeaderboard = new Intent(HomeActivity.this, NavLeaderboard.class);
                startActivity(NavLeaderboard);

                break;
            case R.id.quiz_factory:
                Intent QuizFactory = new Intent(HomeActivity.this, QuizFactory.class);
                startActivity(QuizFactory);
                break;
            case R.id.invite_friends:
                Intent navFriends = new Intent(HomeActivity.this, NavInviteFriends.class);
                startActivity(navFriends);
                break;
            case R.id.rate:
                Toast.makeText(getApplicationContext(), "Rate us", Toast.LENGTH_LONG).show();
                break;
            case R.id.settings:
                Intent NavSettings = new Intent(HomeActivity.this, NavSettings.class);
                startActivity(NavSettings);
                break;
        }

        return super.onOptionsItemSelected(item);
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

    public void userNameInGradient() {
        TextView txt = findViewById(R.id.user_name);
        Shader textShader = new LinearGradient(0, 0, 180, 0,
                new int[]{getResources().getColor(R.color.blueOnHomeText), getResources().getColor(R.color.purpleOnHomeText)},
                new float[]{0, 1}, Shader.TileMode.CLAMP);
        txt.getPaint().setShader(textShader);

        TextView txt1 = findViewById(R.id.wishing);
        Shader textShader1 = new LinearGradient(0, 0, 180, 0,
                new int[]{getResources().getColor(R.color.blueOnHomeText), getResources().getColor(R.color.purpleOnHomeText)},
                new float[]{0, 1}, Shader.TileMode.CLAMP);
        txt1.getPaint().setShader(textShader1);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        menuItem = item;

            // Handle navigation view item clicks here.
            int res_id = item.getItemId();
            Handler handler = new Handler();
            switch (res_id) {
                case R.id.leaderboard:
                    final Intent navLeaderboard = new Intent(HomeActivity.this, NavLeaderboard.class);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(navLeaderboard);
                        }
                    }, 300);
                    break;

                case R.id.notifications:
                    final Intent navNotification = new Intent(HomeActivity.this, NavNotifications.class);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(navNotification);
                        }
                    }, 300);
                    break;

                case R.id.payment:
                    final Intent navPayment = new Intent(HomeActivity.this, NavPayment.class);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(navPayment);
                        }
                    }, 300);
                    break;

                case R.id.settings:
                    final Intent navSettings = new Intent(HomeActivity.this, NavSettings.class);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(navSettings);
                        }
                    }, 300);
                    break;

                case R.id.invite_friends:
                    final Intent navFriends = new Intent(HomeActivity.this, NavInviteFriends.class);

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
            },100);
        return true;

    }



} // activity class