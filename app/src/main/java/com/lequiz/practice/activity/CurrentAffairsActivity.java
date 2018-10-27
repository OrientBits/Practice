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
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lequiz.practice.base.FullScreenStatusOnly;
import com.lequiz.practice.R;
import com.lequiz.practice.adapters.NewsListAdapter;
import com.lequiz.practice.custom_classes.News;
import com.lequiz.practice.loaders.NewsLoader;

import java.util.ArrayList;
import java.util.Objects;

public class CurrentAffairsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<News>> {

    Toolbar toolbar;
    ArrayList<News> newsArrayList;
    RecyclerView recyclerView;
    NewsListAdapter newsListAdapter;
    TextView mEmptyStateTextView;
    ImageView imageErrorLogo;
    TextView errorMessageNoInternet;
    pl.droidsonroids.gif.GifImageView gifImageView;

    private static final String NEWS_REQUEST_URL =
            "https://newsapi.org/v2/top-headlines?country=in&sortBy=publishedAt&apiKey=ff020c6745fc4704bd9cc18bafbeaaca";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_affairs);

        mEmptyStateTextView = findViewById(R.id.empty_view);


        gifImageView = findViewById(R.id.current_affairs_loading_spinner);

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
            gifImageView.setVisibility(View.GONE);
            imageErrorLogo.setVisibility(View.VISIBLE);
            mEmptyStateTextView.setText("Whoops!");
            errorMessageNoInternet.setText("No internet connection found. Check your connection and try again");
        }



        FullScreenStatusOnly fullScreenStatusOnly = new FullScreenStatusOnly(this);

  /*      toolbar = findViewById(R.id.category_toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_button_current_affairs); **/

        // news section


        recyclerView = (RecyclerView) findViewById(R.id.current_affairs_recycler_view);
        newsListAdapter = new NewsListAdapter(this,new ArrayList<News>());
        recyclerView.setAdapter(newsListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Adding divider

        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));

        // Heading Text Gradient
        TextView learnHeaderTech = findViewById(R.id.heading_on_current_affairs);
        Shader textShader = new LinearGradient(0, 0, 180, 0,
                new int[]{getResources().getColor(R.color.blueGradientTech), getResources().getColor(R.color.greenOnCurrentAffairs), getResources().getColor(R.color.yellowOnCurrentAffairs)},
                new float[]{0, 1, 2}, Shader.TileMode.CLAMP);
        learnHeaderTech.getPaint().setShader(textShader);

        //UserName Initialization

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

        if(news==null || news.isEmpty())
        {
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

}