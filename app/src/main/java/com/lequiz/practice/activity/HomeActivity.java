package com.lequiz.practice.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.github.ksoichiro.android.observablescrollview.ScrollUtils;
import com.google.firebase.database.DatabaseReference;
import com.lequiz.practice.base.FullScreenStatusOnly;
import com.lequiz.practice.nav_drawer.JobAlertsActivity;
import com.lequiz.practice.nav_drawer.NavInviteFriends;
import com.lequiz.practice.nav_drawer.NavLeaderboard;
import com.lequiz.practice.nav_drawer.NavNotifications;
import com.lequiz.practice.nav_drawer.NavPayment;
import com.lequiz.practice.nav_drawer.NavSettings;
import com.lequiz.practice.nav_drawer.ProfileActivity;
import com.lequiz.practice.nav_drawer.QuizFactory;
import com.lequiz.practice.R;
import java.sql.Time;
import java.util.Objects;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.widget.Toast.LENGTH_SHORT;

public class HomeActivity extends AppCompatActivity implements ObservableScrollViewCallbacks, NavigationView.OnNavigationItemSelectedListener {

    private View mToolbarView;
    private ObservableScrollView mScrollView;
    private int mParallaxImageHeight;
    private TextView title_text;
    protected DrawerLayout mDrawerLayout;
    protected ActionBarDrawerToggle mToggle;
    protected CircleImageView profile_home;
    protected ImageView profile_header;
    protected CardView currentAffairs, computer, mathematics, reasoning, generalScience, english, technology, sports, special, entertainment;
    Toolbar toolbar;
    Window window;
    boolean doubleBackToExitPressedOnce = false;
    @SuppressLint("StaticFieldLeak")
    public static Activity fa; // finish activity
    public MenuItem menuItem;

    // Testing firebase
    DatabaseReference mRefRoot;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        fa = this; // for only context

        // toolbar setup
        mToolbarView = findViewById(R.id.toolbar);
        setSupportActionBar((Toolbar) mToolbarView);
        getSupportActionBar().setTitle(null);
        title_text = findViewById(R.id.toolbar_title);
        title_text.setText(getResources().getText(R.string.home));
        title_text.setTextColor(getResources().getColor(R.color.black));
        title_text.setAlpha(0);
        //mToolbarView.setBackgroundColor(ScrollUtils.getColorWithAlpha(0, getResources().getColor(R.color.white)));

        mScrollView =  findViewById(R.id.scroll);
        mScrollView.setScrollViewCallbacks(this);
        mParallaxImageHeight = getResources().getDimensionPixelSize(R.dimen.image_height_home_part_1);
        new FullScreenStatusOnly(this);


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
         toolbarLayout.setPadding(0,statusBarHeight,0,0);
         window= getWindow();
         window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);



        // user name on home page gradient
        userNameInGradient();

        // implementing item of navigation drawer
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        View headerView = navigationView.getHeaderView(0);
        profile_header = headerView.findViewById(R.id.profile_image);


        // bottom navigation bar...
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_layout);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        bottomNavigationView.setItemIconTintList(null);





        currentAffairs = findViewById(R.id.current_affairs_card_view);
        ViewTreeObserver vto1 = currentAffairs.getViewTreeObserver();
        vto1.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                currentAffairs.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                int width = currentAffairs.getMeasuredWidth();
                ViewGroup.LayoutParams params = currentAffairs.getLayoutParams();
                params.height = (width * 70) / 100;
                currentAffairs.setLayoutParams(params);
            }
        });

        computer = findViewById(R.id.computer_card_view);
        ViewTreeObserver vto2 = computer.getViewTreeObserver();
        vto2.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                computer.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                int width = computer.getMeasuredWidth();
                ViewGroup.LayoutParams params = computer.getLayoutParams();
                params.height = (width * 70) / 100;
                computer.setLayoutParams(params);
            }
        });

        mathematics = findViewById(R.id.mathematics_card_view);
        ViewTreeObserver vto3 = mathematics.getViewTreeObserver();
        vto3.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mathematics.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                int width = mathematics.getMeasuredWidth();
                ViewGroup.LayoutParams params = mathematics.getLayoutParams();
                params.height = (width * 70) / 100;
                mathematics.setLayoutParams(params);
            }
        });

        reasoning = findViewById(R.id.reasoning_card_view);
        ViewTreeObserver vto4 = reasoning.getViewTreeObserver();
        vto4.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                reasoning.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                int width = reasoning.getMeasuredWidth();
                ViewGroup.LayoutParams params = reasoning.getLayoutParams();
                params.height = (width * 70) / 100;
                reasoning.setLayoutParams(params);
            }
        });

        generalScience = findViewById(R.id.general_science_card_view);
        ViewTreeObserver vto5 = generalScience.getViewTreeObserver();
        vto5.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                generalScience.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                int width = generalScience.getMeasuredWidth();
                ViewGroup.LayoutParams params = generalScience.getLayoutParams();
                params.height = (width * 70) / 100;
                generalScience.setLayoutParams(params);
            }
        });

        english = findViewById(R.id.english_card_view);
        ViewTreeObserver vto6 = english.getViewTreeObserver();
        vto6.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                english.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                int width = english.getMeasuredWidth();
                ViewGroup.LayoutParams params = english.getLayoutParams();
                params.height = (width * 70) / 100;
                english.setLayoutParams(params);
            }
        });

        technology = findViewById(R.id.technology_card_view);
        ViewTreeObserver vto7 = technology.getViewTreeObserver();
        vto7.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                technology.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                int width = technology.getMeasuredWidth();
                ViewGroup.LayoutParams params = technology.getLayoutParams();
                params.height = (width * 70) / 100;
                technology.setLayoutParams(params);
            }
        });

        sports = findViewById(R.id.sports_card_view);
        ViewTreeObserver vto8 = sports.getViewTreeObserver();
        vto8.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                sports.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                int width = sports.getMeasuredWidth();
                ViewGroup.LayoutParams params = sports.getLayoutParams();
                params.height = (width * 70) / 100;
                sports.setLayoutParams(params);
            }
        });

        special = findViewById(R.id.special_card_view);
        ViewTreeObserver vto9 = special.getViewTreeObserver();
        vto9.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                special.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                int width = special.getMeasuredWidth();
                ViewGroup.LayoutParams params = special.getLayoutParams();
                params.height = (width * 70) / 100;
                special.setLayoutParams(params);
            }
        });

        entertainment = findViewById(R.id.entertainment_card_view);
        ViewTreeObserver vto10 = entertainment.getViewTreeObserver();
        vto10.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                entertainment.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                int width = entertainment.getMeasuredWidth();
                ViewGroup.LayoutParams params = entertainment.getLayoutParams();
                params.height = (width * 70) / 100;
                entertainment.setLayoutParams(params);
            }
        });

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
        Toast.makeText(this, "Please click BACK again to exit", LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }





    // parallax toolbar implementation
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        onScrollChanged(mScrollView.getCurrentScrollY(), false, false);
    }
    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {
        int baseColor = getResources().getColor(R.color.colorPrimary);
        float alpha = Math.min(1, (float) scrollY / mParallaxImageHeight);
        mToolbarView.setBackgroundColor(ScrollUtils.getColorWithAlpha(alpha-(float)0.035, baseColor));
        title_text.setAlpha(alpha-(float)0.035);


        window.setStatusBarColor(ScrollUtils.getColorWithAlpha(alpha-(float)0.03, baseColor));
    }
    @Override
    public void onDownMotionEvent() { }
    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) { }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.notifications){
            Intent NavNotification = new Intent(HomeActivity.this, NavNotifications.class);
            startActivity(NavNotification);
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


    @Override
    protected void onPostResume() {
        super.onPostResume();
        mDrawerLayout.closeDrawers();
    }


    @Override
    protected void onResume() {
        super.onResume();
        mDrawerLayout.closeDrawers();
        userNameInGradient();
    }

    @SuppressLint("SetTextI18n")
    public void userNameInGradient() {
        TextView txt = findViewById(R.id.user_name);
        Shader textShader = new LinearGradient(0, 0, 180, 0,
                new int[]{getResources().getColor(R.color.blueOnHomeText), getResources().getColor(R.color.purpleOnHomeText)},
                new float[]{0, 1}, Shader.TileMode.CLAMP);
        txt.getPaint().setShader(textShader);


        TextView txt1 = findViewById(R.id.wishing);

        int hours = new Time(System.currentTimeMillis()).getHours();
        if (hours >= 5 && hours < 12) {
            txt1.setText("Good morning ");
        } else if (hours >= 12 && hours < 18) {
            txt1.setText("Good afternoon ");
        } else if (hours >= 18 && hours < 22) {
            txt1.setText("Good evening");
        } else
            txt1.setText("Good night");

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

            case R.id.job_alerts:
                final Intent navJobAlerts = new Intent(HomeActivity.this, JobAlertsActivity.class);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                      startActivity(navJobAlerts);
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

            case R.id.quiz_factory:
                final Intent navQuizFactory = new Intent(HomeActivity.this,QuizFactory.class);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(navQuizFactory);
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
        }, 100);
        return true;

    }



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.bottom_nav_home:

                    return true;
                case R.id.bottom_nav_random_quiz:

                    return true;
                case R.id.bottom_nav_job_alert:

                    return true;
            }
            return false;
        }
    };


}