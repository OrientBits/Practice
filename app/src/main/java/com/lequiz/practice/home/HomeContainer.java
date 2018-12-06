package com.lequiz.practice.home;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.github.ksoichiro.android.observablescrollview.ScrollUtils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lequiz.practice.R;
import com.lequiz.practice.base.FullScreenStatusOnly;
import com.lequiz.practice.module.Users;
import com.lequiz.practice.nav_drawer.NavInviteFriends;
import com.lequiz.practice.nav_drawer.NavLeaderboard;
import com.lequiz.practice.nav_drawer.NavPayment;
import com.lequiz.practice.nav_drawer.NavSettings;
import com.lequiz.practice.nav_drawer.ProfileActivity;
import com.lequiz.practice.nav_drawer.QuizFactory;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import java.util.Objects;
import de.hdodenhof.circleimageview.CircleImageView;
import static android.widget.Toast.LENGTH_SHORT;

public class HomeContainer extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    static  Fragment fragmentHome;
    static  Fragment fragmentJobAlert;
    Fragment previousFragment;

    public static View mToolbarView;
    public static RelativeLayout toolbarLayout;
    boolean doubleBackToExitPressedOnce = false,isFragmentInitialized=false;
    protected DrawerLayout mDrawerLayout;
    protected ActionBarDrawerToggle mToggle;
    protected CircleImageView profile_home;
    public MenuItem menuItem;
    TextView userNameOnDrawar;
    public static CardView toolbar_card_view_2;
    public static TextView title_text;
    DatabaseReference currentUserRef;
    public static FirebaseAuth mAuth;
    CircleImageView drawarProfileImg;
    String profileImgUrl;
    FirebaseUser mUser;
    String firstName;
    FloatingActionButton homeBtn, randomBtn, jobBtn;
    public static float homeToolbarColor = 0.0f, randomToolbarColor = 0.0f, jobToolbarColor = 0.0f;
    public static float homeTitleAlpha = 0.0f, randomTitleAlpha = 0.0f, jobTitleAlpha = 0.0f;
    public int baseColor1;



    @SuppressLint("StaticFieldLeak")
    public static Activity fa; // finish activity


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home_container);

        if (!isFragmentInitialized){
            fragmentHome = new FragmentHome();
            fragmentJobAlert = new FragmentJobAlert();
            isFragmentInitialized = true;
        }

        fa = this; // for only context
        mAuth = FirebaseAuth.getInstance();
        mUser = FirebaseAuth.getInstance().getCurrentUser();



        new FullScreenStatusOnly(this);
        baseColor1 = getResources().getColor(R.color.colorPrimary);


        // initialization of bottom app bar
        homeBtn = findViewById(R.id.home_fab_bottom);
        randomBtn = findViewById(R.id.random_fab_bottom);
        jobBtn = findViewById(R.id.job_fab_bottom);


        // toolbar setup
        mToolbarView = findViewById(R.id.toolbar);
        setSupportActionBar((Toolbar) mToolbarView);
        Objects.requireNonNull(getSupportActionBar()).setTitle(null);
        title_text = findViewById(R.id.toolbar_title);
        title_text.setTextColor(getResources().getColor(R.color.black));
        title_text.setAlpha(0);
        toolbar_card_view_2 = findViewById(R.id.toolbar_card_view_2);

        // open drawer when navigation button is tapped
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, (Toolbar) mToolbarView, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        mDrawerLayout.setStatusBarBackgroundColor(getResources().getColor(R.color.transparent));
        Objects.requireNonNull(getSupportActionBar()).setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_drawer_indicator);

        // status bar height calculation
        int statusBarHeight = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = getResources().getDimensionPixelSize(resourceId);
        }
        toolbarLayout = findViewById(R.id.toolbar_root_layout);
        mToolbarView.setPadding(0, statusBarHeight, 8, 0);
        toolbarLayout.setPadding(0, statusBarHeight, 0, 0);


        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // bottom app bar
        title_text.setText(getText(R.string.home));
        loadFragment(fragmentHome);

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(fragmentHome);
                toolbar_card_view_2.setVisibility(View.VISIBLE);
                title_text.setText(getText(R.string.home));
                mToolbarView.setBackgroundColor(ScrollUtils.getColorWithAlpha(homeToolbarColor, baseColor1));
                title_text.setAlpha(homeTitleAlpha);
            }
        });




        randomBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(HomeContainer.this, android.R.style.Theme_Dialog);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.play_random_dialog_layout);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();


            }
        });









        jobBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(fragmentJobAlert);
                toolbar_card_view_2.setVisibility(View.INVISIBLE);
                title_text.setText(getText(R.string.job_alerts));
                mToolbarView.setBackgroundColor(ScrollUtils.getColorWithAlpha(jobToolbarColor, baseColor1));
                title_text.setAlpha(jobTitleAlpha);
            }
        });

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        // implementing item of navigation drawer
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        View headerView = navigationView.getHeaderView(0);
        drawarProfileImg = headerView.findViewById(R.id.profile_image_drawar);
        userNameOnDrawar = headerView.findViewById(R.id.user_name_on_drawar);
        currentUserRef = FirebaseDatabase.getInstance().getReference("Users").child(HomeContainer.mAuth.getCurrentUser().getUid());
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////Fetching name

        currentUserRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    String fancyName = dataSnapshot.child("fancyName").getValue().toString();

                    // Here we can set fancy name
                    userNameOnDrawar.setText(fancyName);

                } catch (NullPointerException e) {
                    try {
                        String firstName = dataSnapshot.child("firstName").getValue().toString();
                        userNameOnDrawar.setText(firstName);
                    } catch (NullPointerException f) {
                        String displayName = mUser.getDisplayName();
                        try {
                            int indexOfBlank = displayName.indexOf(" ");
                            firstName = displayName.substring(0, indexOfBlank);
                            userNameOnDrawar.setText(firstName);
                        } catch (StringIndexOutOfBoundsException g) {
                            userNameOnDrawar.setText(displayName);
                        }
                    }


                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        currentUserRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {

                    profileImgUrl = dataSnapshot.child("profileImgUrl").getValue(String.class);
                } catch (NullPointerException e) {
                    try {
                        // Fetching google photo
                        profileImgUrl = mUser.getPhotoUrl().toString();
                    } catch (NullPointerException f) {

                    }
                }


                if (TextUtils.isEmpty(profileImgUrl) && profileImgUrl == null)

                {


                    // if profile Img url is empty then this code will run

                    currentUserRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            try {
                                String gender = dataSnapshot.child("gender").getValue().toString();

                                if (gender.equals("male")) {
                                    Picasso.get()
                                            .load(profileImgUrl).error(R.drawable.male_avatar_placeholder).placeholder(R.drawable.male_avatar_placeholder)
                                            .networkPolicy(NetworkPolicy.OFFLINE)
                                            .into(drawarProfileImg, new Callback() {
                                                @Override
                                                public void onSuccess() {


                                                }

                                                @Override
                                                public void onError(Exception e) {


                                                    Picasso.get()
                                                            .load(profileImgUrl).placeholder(R.drawable.male_avatar_placeholder)
                                                            .error(R.drawable.male_avatar_placeholder)
                                                            .into(drawarProfileImg, new Callback() {
                                                                @Override
                                                                public void onSuccess() {
                                                                    Toast.makeText(getApplicationContext(), "Profile pic fetched successfully", Toast.LENGTH_SHORT).show();
                                                                }

                                                                @Override
                                                                public void onError(Exception e) {
                                                                    Log.v("Picasso", "Could not fetch image");
                                                                }


                                                            });

                                                }


                                            });
                                }
                                if (gender.equals("female")) {
                                    System.out.println("Inside female");
                                    Picasso.get()
                                            .load(profileImgUrl).error(R.drawable.female_avatar_placeholder).placeholder(R.drawable.female_avatar_placeholder)
                                            .networkPolicy(NetworkPolicy.OFFLINE)
                                            .into(drawarProfileImg, new Callback() {
                                                @Override
                                                public void onSuccess() {

                                                }

                                                @Override
                                                public void onError(Exception e) {


                                                    Picasso.get()
                                                            .load(Users.getProfileImgUrl()).placeholder(R.drawable.female_avatar_placeholder)
                                                            .error(R.drawable.female_avatar_placeholder)
                                                            .into(profile_home, new Callback() {
                                                                @Override
                                                                public void onSuccess() {
                                                                    Toast.makeText(getApplicationContext(), "Profile pic fetched successfully", Toast.LENGTH_SHORT).show();
                                                                }

                                                                @Override
                                                                public void onError(Exception e) {
                                                                    Log.v("Picasso", "Could not fetch image");
                                                                }


                                                            });

                                                }


                                            });
                                }
                            } catch (NullPointerException e) {
                                return;
                            }


                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            System.out.println("Database error 346");
                        }
                    });


                } else {

                    // If the profile Img Url is not empty. Setting the profile Img from the url
                    Picasso.get()
                            .load(profileImgUrl).placeholder(R.drawable.default_profile_picture)
                            .networkPolicy(NetworkPolicy.OFFLINE)
                            .into(drawarProfileImg, new Callback() {
                                @Override
                                public void onSuccess() {


                                }

                                @Override
                                public void onError(Exception e) {


                                    Picasso.get()
                                            .load(profileImgUrl).placeholder(R.drawable.default_profile_picture)

                                            .into(drawarProfileImg, new Callback() {
                                                @Override
                                                public void onSuccess() {
                                                    Toast.makeText(getApplicationContext(), "Profile pic fetched successfully", Toast.LENGTH_SHORT).show();
                                                }

                                                @Override
                                                public void onError(Exception e) {
                                                    Log.v("Picasso", "Could not fetch image");
                                                }


                                            });

                                }


                            });


                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        drawarProfileImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent PA = new Intent(HomeContainer.this, ProfileActivity.class);
                startActivity(PA);
                mDrawerLayout.closeDrawer(GravityCompat.START);
            }
        });


    } // onCreate method


    public void loadFragment(final Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        final FragmentTransaction transaction = manager.beginTransaction();

        if (!fragment.isAdded()) {
            transaction.add(R.id.home_fragment_container, fragment).hide(fragment);
        }

        if (fragment == fragmentHome) {
          //  transaction.setCustomAnimations(R.anim.enter_from_left,R.anim.exit_to_right);
            transaction.hide(fragmentJobAlert);
            transaction.show(fragment);
            this.previousFragment = fragment;

        }  else if (fragment == fragmentJobAlert) {
            //transaction.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left);
            transaction.hide(fragmentHome);
            transaction.show(fragment);
            this.previousFragment = fragment;
        }



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
    protected void onResume() {
        super.onResume();
        mDrawerLayout.closeDrawers();
    }


    @Override
    protected void onStart() {
        new FullScreenStatusOnly(this);
        super.onStart();
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
                final Intent navQuizFactory = new Intent(HomeContainer.this, QuizFactory.class);
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
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://github.com/"));
                startActivity(intent);
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