package com.lequiz.practice.category;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.github.ksoichiro.android.observablescrollview.ScrollUtils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lequiz.practice.base.FullScreenStatusOnly;
import com.lequiz.practice.R;
import com.lequiz.practice.adapters.NewsListAdapter;
import com.lequiz.practice.module.News;
import com.lequiz.practice.loaders.NewsLoader;
import java.util.ArrayList;
import java.util.Objects;

public class CurrentAffairsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<News>> , ObservableScrollViewCallbacks {


    RecyclerView recyclerView;
    NewsListAdapter newsListAdapter;
    TextView mEmptyStateTextView;
    ImageView imageErrorLogo;
    TextView errorMessageNoInternet;
    ShimmerFrameLayout shimmerFrameLayout;
    CardView toolbar_card_view_2;
    private View mToolbarView;
    private ObservableScrollView mScrollView;
    private int mParallaxImageHeight;
    RelativeLayout toolbarLayout;
    TextView title_text;
    FirebaseUser mUser;
    DatabaseReference currentUserRef;
    FirebaseAuth mAuth;
    String firstName;
    String fancyName;
    String lastName;
    String heyUserNameMaker;
    RelativeLayout relativeWeeklyUpdatesCard;


    private static final String NEWS_REQUEST_URL =
            "https://newsapi.org/v2/top-headlines?country=in&sortBy=publishedAt&apiKey=ff020c6745fc4704bd9cc18bafbeaaca";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_current_affairs);

        // Initializing mAuth

        mAuth = FirebaseAuth.getInstance();
        mUser = FirebaseAuth.getInstance().getCurrentUser();

        mEmptyStateTextView = findViewById(R.id.empty_view);
        relativeWeeklyUpdatesCard = findViewById(R.id.relative_card_view_current_affairs_weekly_bullet);

        relativeWeeklyUpdatesCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ///////////// Relative CardView weekly onClick

                startActivity(new Intent(CurrentAffairsActivity.this, WeeklyCurrentAffairs.class));

            }
        });

        //UserName Initialization

        final TextView heyUserName = findViewById(R.id.hey_user_name);
         heyUserNameMaker = "Hey, " ;


        shimmerFrameLayout = findViewById(R.id.shimmer_layout_container_current_affairs);

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Fetching name

        final String uId = mAuth.getCurrentUser().getUid();
        currentUserRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://lequiz-4abd1.firebaseio.com/Users/" + uId);

        currentUserRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try{
                    fancyName=dataSnapshot.child("fancyName").getValue().toString();
                    heyUserNameMaker+=fancyName;
                    heyUserName.setText(heyUserNameMaker);

                }
                    catch(NullPointerException e)
                    {
                        try{
                        firstName=dataSnapshot.child("firstName").getValue().toString();
                            heyUserNameMaker+=firstName;
                            System.out.println("last line fname "+heyUserNameMaker);
                            heyUserName.setText(heyUserNameMaker);

                        }
                        catch(NullPointerException f)
                        {
                            String displayName = mUser.getDisplayName();
                            int indexOfBlank=displayName.indexOf(" ");
                            firstName = displayName.substring(0,indexOfBlank);
                            lastName=displayName.substring(indexOfBlank+1);

                            heyUserNameMaker+=firstName;
                            heyUserName.setText(heyUserNameMaker);

                        }
                    }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        imageErrorLogo = findViewById(R.id.le_quiz_error_logo);
        errorMessageNoInternet = findViewById(R.id.error_message_no_internet);
        /* Loader manager and network state check **/

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(0, null, this);
        }
        else
        {
            shimmerFrameLayout.setVisibility(View.GONE);
            shimmerFrameLayout.stopShimmer();
            imageErrorLogo.setVisibility(View.VISIBLE);
            mEmptyStateTextView.setText("Whoops!");
            errorMessageNoInternet.setText("No internet connection found. Check your connection and try again");
        }



        mToolbarView = findViewById(R.id.toolbar);
        setSupportActionBar((Toolbar) mToolbarView);
        getSupportActionBar().setTitle(null);
        title_text = findViewById(R.id.toolbar_title);
        title_text.setText(getResources().getText(R.string.current_Affairs));
        title_text.setTextColor(getResources().getColor(R.color.black));
        title_text.setAlpha(0);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_arrow_current_affairs);
        toolbar_card_view_2 = findViewById(R.id.toolbar_card_view_2);
        toolbar_card_view_2.setVisibility(View.INVISIBLE);

        // status bar calculation
        int statusBarHeight = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = getResources().getDimensionPixelSize(resourceId);
        }
        toolbarLayout = findViewById(R.id.toolbar_root_layout);
        mToolbarView.setPadding(0, statusBarHeight, 4, 0);
        toolbarLayout.setPadding(0,statusBarHeight,0,0);

        mScrollView = (ObservableScrollView) findViewById(R.id.scroll);
        mScrollView.setScrollViewCallbacks(this);
        mParallaxImageHeight = getResources().getDimensionPixelSize(R.dimen.image_height_home_part_1);
        new FullScreenStatusOnly(this);


        // news section
        recyclerView = (RecyclerView) findViewById(R.id.current_affairs_recycler_view);
        newsListAdapter = new NewsListAdapter(this,new ArrayList<News>());
        recyclerView.setAdapter(newsListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setNestedScrollingEnabled(false);

        // Adding divider

        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));


        // Heading Text Gradient
        TextView learnHeaderTech = findViewById(R.id.heading_on_current_affairs);
        Shader textShader = new LinearGradient(0, 0, 180, 0,
                new int[]{getResources().getColor(R.color.greenOnCurrentAffairs2), getResources().getColor(R.color.yellowOnCurrentAffairs2)},
                new float[]{0, 1}, Shader.TileMode.CLAMP);
        learnHeaderTech.getPaint().setShader(textShader);




    }

    /*Async task for Query of news **/

    @Override
    public Loader<ArrayList<News>> onCreateLoader(int i, Bundle bundle) {
        return new NewsLoader(this, NEWS_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<News>> loader, ArrayList<News> news) {

        if(news==null || news.isEmpty())
        {
            // Server problem message
            mEmptyStateTextView.setText("Whoops!");
            errorMessageNoInternet.setText("Server is busy right now, we are fixing the issue");
            shimmerFrameLayout.setVisibility(View.GONE);
            shimmerFrameLayout.stopShimmer();
            imageErrorLogo.setVisibility(View.VISIBLE);
        }



        // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (news != null && !news.isEmpty()) {
            newsListAdapter.addAll(news);
            newsListAdapter.notifyDataSetChanged();
            shimmerFrameLayout.setVisibility(View.GONE);
            shimmerFrameLayout.stopShimmer();
        }
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<News>> loader) {

    }



    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        onScrollChanged(mScrollView.getCurrentScrollY(), false, false);
    }

    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {
        int baseColor = getResources().getColor(R.color.colorPrimary);
        float alpha = Math.min(1, (float) scrollY / mParallaxImageHeight);
        mToolbarView.setBackgroundColor(ScrollUtils.getColorWithAlpha(alpha-(float)0.02, baseColor));
        title_text.setAlpha(alpha-(float)0.035);
    }


    @Override
    public void onDownMotionEvent() {

    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {
    }

    @Override
    protected void onResume() {
        super.onResume();
        shimmerFrameLayout.startShimmer();
    }

    @Override
    protected void onPause() {
        super.onPause();
        shimmerFrameLayout.stopShimmer();
    }
}