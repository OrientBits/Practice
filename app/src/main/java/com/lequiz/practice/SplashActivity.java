package com.lequiz.practice;

import android.content.Intent;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.felipecsl.gifimageview.library.GifImageView;
import org.apache.commons.io.IOUtils;
import java.io.IOException;
import java.io.InputStream;

public class SplashActivity extends AppCompatActivity {

    public static int SPLASH_TIME_OUT=1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


//        GifImageView gifImageView = (GifImageView) findViewById(R.id.splash_gif_id);
//        try
//        {
//            InputStream inputStream = getAssets().open("splash.gif");
//            byte [] bytes = IOUtils.toByteArray(inputStream);
//            gifImageView.setBytes(bytes);
//            gifImageView.startAnimation();
//        }
//        catch(IOException ex)
//        {
//          // here is your catch statement
//        }


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent = new Intent(SplashActivity.this,HomeActivity.class);
                startActivity(homeIntent);
                finish();
            }
        },SPLASH_TIME_OUT );
    }
}
