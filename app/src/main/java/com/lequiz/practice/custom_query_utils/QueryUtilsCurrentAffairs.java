package com.lequiz.practice.custom_query_utils;

import android.text.TextUtils;
import android.util.Log;

import com.lequiz.practice.custom_classes.News;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public final class QueryUtilsCurrentAffairs {



    private QueryUtilsCurrentAffairs() {


    }


    private static ArrayList<News> extractFeatureFromJSON(String JSONurl) {

        if (TextUtils.isEmpty(JSONurl)) {
            return null;
        }

        // Create an empty ArrayList that we can start adding earthquakes to
        ArrayList<News> newsArrayList = new ArrayList<>();

        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            JSONObject root = new JSONObject(JSONurl);

            JSONArray articles = root.getJSONArray("articles");
            for (int i =0;i<articles.length();i++)
            {
                JSONObject newsObject = articles.getJSONObject(i);
                String title = newsObject.getString("title");
                String publishedAt = newsObject.getString("publishedAt");
                String imgUrl = newsObject.getString("urlToImage");
                String sourceUrl = newsObject.getString("url");


                /* New date and time conversion **/
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
                String dateInString = publishedAt;
                String newsHour="";
                String newsMinute="";
                String newsDay="";
                String newsMonth="";
                try {

                    formatter.setTimeZone(TimeZone.getTimeZone("IST"));
                    Date date = formatter.parse(dateInString.replaceAll("Z$", "+0000"));
                    String newsDateTime = date.toString();
                    newsHour = newsDateTime.substring(11,13);
                    newsMinute = newsDateTime.substring(14,16);



                    String dateTimeNews = formatter.format(date);
                    newsMonth=dateTimeNews.substring(5,7);
                    newsDay = dateTimeNews.substring(8,10);



                } catch (ParseException e) {
                    e.printStackTrace();
                }

                /* Getting current date and time in java **/

                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date();
                String cDateTime = dateFormat.format(date);

                String cMonth = cDateTime.substring(5,7);
                String cDate = cDateTime.substring(8,10);


                String cHour = cDateTime.substring(11,13);
                String cMinute = cDateTime.substring(14,16);

                // Converting String values to int values

                int iNewsDay = Integer.parseInt(newsDay);
                int iNewsMonth = Integer.parseInt(newsMonth);
                int iNewsHour = Integer.parseInt(newsHour);
                int iNewsMinute = Integer.parseInt(newsMinute);

                int icDay = Integer.parseInt(cDate);
                int icMonth = Integer.parseInt(cMonth);
                int icHour = Integer.parseInt(cHour);
                int icMinute = Integer.parseInt(cMinute);
                System.out.println("icDay "+icDay+"iNewsDay "+iNewsDay);

                // Comparing time and date values

                if(icMonth==iNewsMonth)
                {
                    if(icDay==iNewsDay)
                    {

                        if(iNewsHour==icHour)
                        {
                            if(iNewsMinute==icMinute)
                            {
                                publishedAt="0 minutes ago";
                            }
                            else
                            {
                                publishedAt = String.valueOf(icMinute-iNewsMinute)+" minutes ago";
                            }

                        }
                        else
                        {
                            publishedAt=String.valueOf(icHour-iNewsHour)+" hours ago";
                        }
                    }
                    else
                    {
                        publishedAt=String.valueOf(icDay-iNewsDay)+" day ago";
                    }
                }
                else
                {
                    publishedAt=String.valueOf((icMonth-iNewsMonth))+" month ago";
                }









                News news = new News(title,publishedAt,imgUrl,sourceUrl);
                newsArrayList.add(news);

            }


            // build up a list of Earthquake objects with the corresponding data.

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtilsCurretAffairs", "Problem parsing the earthquake JSON results", e);
        }

        // Return the list of earthquakes
        return newsArrayList;
    }

    /**
     * Returns new URL object from the given string URL.
     */
    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e("CurrentAffairs", "Problem building the URL ", e);
        }
        return url;
    }

    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e("QueryUtils", "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e("CurrentAffairs", "Problem retrieving the earthquake JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // Closing the input stream could throw an IOException, which is why
                // the makeHttpRequest(URL url) method signature specifies than an IOException
                // could be thrown.
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }


    public static ArrayList<News> fetchEarthquakeData(String requestUrl) {
        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e("Current Affairs", "Problem making the HTTP request.", e);
        }

        // Extract relevant fields from the JSON response and create a list of {@link Earthquake}s
        ArrayList<News> earthquakes = extractFeatureFromJSON(jsonResponse);

        // Return the list of {@link Earthquake}s
        return earthquakes;
    }
}
