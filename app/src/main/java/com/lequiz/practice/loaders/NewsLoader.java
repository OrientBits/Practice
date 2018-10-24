package com.lequiz.practice.loaders;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.lequiz.practice.custom_classes.News;
import com.lequiz.practice.custom_query_utils.QueryUtilsCurrentAffairs;

import java.util.ArrayList;

public class NewsLoader extends AsyncTaskLoader<ArrayList<News>> {

    private static final String LOG_TAG = NewsLoader.class.getName();

    /** Query URL */
    private String mUrl;

    /**
     * Constructs a new {@link NewsLoader}.
     *
     * @param context of the activity
     * @param url to load data from
     */
    public NewsLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    /**
     * This is on a background thread.
     */
    @Override
    public ArrayList<News> loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract a list of earthquakes.
        ArrayList<News> news = QueryUtilsCurrentAffairs.fetchEarthquakeData(mUrl);
        return news;
    }

}
