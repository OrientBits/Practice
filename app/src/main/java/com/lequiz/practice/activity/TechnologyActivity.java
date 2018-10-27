package com.lequiz.practice.activity;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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

import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.github.ksoichiro.android.observablescrollview.ScrollUtils;
import com.lequiz.practice.base.FullScreenStatusOnly;
import com.lequiz.practice.R;
import com.lequiz.practice.adapters.NewsListAdapter;
import com.lequiz.practice.custom_classes.News;
import com.lequiz.practice.loaders.NewsLoader;

import java.util.ArrayList;
import java.util.Objects;

public class TechnologyActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<News>> , ObservableScrollViewCallbacks {


    RecyclerView recyclerView;
    NewsListAdapter newsListAdapter;
    TextView mEmptyStateTextView;
    ImageView imageErrorLogo;
    TextView errorMessageNoInternet;
    pl.droidsonroids.gif.GifImageView gifImageView;
    CardView toolbar_card_view_2;
    private View mToolbarView;
    private ObservableScrollView mScrollView;
    private int mParallaxImageHeight;
    Window window;
    TextView title_text;

    private static final String NEWS_REQUEST_URL =
            "https://newsapi.org/v2/top-headlines?category=technology&sortBy=publishedAt&country=in&apiKey=ff020c6745fc4704bd9cc18bafbeaaca";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technology);

        mEmptyStateTextView = findViewById(R.id.empty_view_technology);
        gifImageView = findViewById(R.id.technology_loading_spinner);

        imageErrorLogo = findViewById(R.id.le_quiz_error_logo);
        errorMessageNoInternet = findViewById(R.id.error_message_no_internet);
        /* Loader manager and network state check **/

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(0, null, this);
        } else {
            gifImageView.setVisibility(View.GONE);
            imageErrorLogo.setVisibility(View.VISIBLE);
            mEmptyStateTextView.setText("Whoops!");
            errorMessageNoInternet.setText("No internet connection found. Check your connection and try again");
        }

        mToolbarView = findViewById(R.id.toolbar);
        setSupportActionBar((Toolbar) mToolbarView);
        getSupportActionBar().setTitle(null);
        title_text = findViewById(R.id.toolbar_title);
        title_text.setText(getResources().getText(R.string.technology));
        title_text.setTextColor(getResources().getColor(R.color.black));
        title_text.setAlpha(0);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back_arrow_technology);
        toolbar_card_view_2 = findViewById(R.id.toolbar_card_view_2);
        toolbar_card_view_2.setVisibility(View.INVISIBLE);


        // status bar calculation
        int statusBarHeight = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = getResources().getDimensionPixelSize(resourceId);
        }
        RelativeLayout toolbarLayout = findViewById(R.id.toolbar_root_layout);
        toolbarLayout.setPadding(0,statusBarHeight,0,0);
        window= getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        mScrollView = (ObservableScrollView) findViewById(R.id.scroll);
        mScrollView.setScrollViewCallbacks(this);
        mParallaxImageHeight = getResources().getDimensionPixelSize(R.dimen.image_height_home_part_1);
        new FullScreenStatusOnly(this);


        // news section
        recyclerView = (RecyclerView) findViewById(R.id.technology_recycler_view);
        newsListAdapter = new NewsListAdapter(this, new ArrayList<News>());
        recyclerView.setAdapter(newsListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Adding divider

        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));

        // Heading TextView gradient


        TextView learnHeaderTech = findViewById(R.id.heading_on_technology);
        Shader textShader = new LinearGradient(0, 0, 180, 0,
                new int[]{getResources().getColor(R.color.blueGradientTech), getResources().getColor(R.color.purpleOnHomeText)},
                new float[]{0, 1}, Shader.TileMode.CLAMP);
        learnHeaderTech.getPaint().setShader(textShader);

        // Hey UserName Initialization on learn section

        TextView heyUserName = findViewById(R.id.hey_user_name);
        String heyUserNameMaker = "Hey " + getString(R.string.user_first_name) + ",";
        heyUserName.setText(heyUserNameMaker);


    }

    /*Async task for Query of news **/

    @Override
    public Loader<ArrayList<News>> onCreateLoader(int i, Bundle bundle) {
        return new NewsLoader(this, NEWS_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<News>> loader, ArrayList<News> news) {

        if (news == null || news.isEmpty()) {
            // Server problem message
            mEmptyStateTextView.setText("Whoops!");
            errorMessageNoInternet.setText("Server is busy right now, we are fixing the issue");
            gifImageView.setVisibility(View.GONE);
            imageErrorLogo.setVisibility(View.VISIBLE);
        }


        // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (news != null && !news.isEmpty()) {
            newsListAdapter.addAll(news);
            newsListAdapter.notifyDataSetChanged();
            gifImageView.setVisibility(View.GONE);
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
        window.setStatusBarColor(ScrollUtils.getColorWithAlpha(alpha-(float)0.03, baseColor));
        title_text.setAlpha(alpha-(float)0.035);
    }


    @Override
    public void onDownMotionEvent() {

    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {
    }


}
