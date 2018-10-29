package com.lequiz.practice.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.lequiz.practice.R;

import static android.view.View.GONE;

public class WebViewLayout extends AppCompatActivity {
    private WebView webView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_layout);
        webView=findViewById(R.id.webview);
        progressBar = findViewById(R.id.progress_bar_on_webview_news);
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        String sourceUrl = b.getString("sourceUrlAgain");
        progressBar.getProgressDrawable().setColorFilter(
                Color.BLUE, android.graphics.PorterDuff.Mode.SRC_IN);
        webView.setWebChromeClient(new WebChromeClient(){

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                progressBar.setProgress(newProgress);

                if(progressBar.getProgress()==100)
                {
                    progressBar.setVisibility(GONE);
                }
            }
        });
        webView.setWebViewClient(new WebViewClient());  // Double webclient is used to open the links only in our app not in chrome
        webView.loadUrl(sourceUrl);

    }


}
