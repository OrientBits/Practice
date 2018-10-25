package com.lequiz.practice.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.lequiz.practice.R;

public class WebViewLayout extends AppCompatActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_layout);
        webView=findViewById(R.id.webView);
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        String sourceUrl = b.getString("sourceUrlAgain");
        System.out.println("Url to be loaded "+sourceUrl);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(sourceUrl);
    }


}
