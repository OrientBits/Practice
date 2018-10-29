package com.lequiz.practice.activity;

import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.lequiz.practice.R;
import com.lequiz.practice.adapters.NewsListAdapter;
import com.lequiz.practice.base.FullScreenStatusOnly;

import static android.view.View.GONE;

public class JobAlertsActivity extends AppCompatActivity {

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
    Window window;
    TextView title_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_alerts);

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
        toolbar_card_view_2 = findViewById(R.id.toolbar_card_view_2);
        toolbar_card_view_2.setVisibility(GONE);

        new FullScreenStatusOnly(this);

        // Heading TextView gradient
        TextView learnHeaderJobs = findViewById(R.id.heading_on_job_alerts);
        Shader textShader = new LinearGradient(0, 0, 180, 0,
                new int[]{getResources().getColor(R.color.violet_on_job_alert), getResources().getColor(R.color.pink_on_job_alert)},
                new float[]{0, 1}, Shader.TileMode.CLAMP);
        learnHeaderJobs.getPaint().setShader(textShader);

        // Hey UserName Initilization on learn section

        TextView heyUserName = findViewById(R.id.hey_user_name);
        String heyUserNameMaker = "Hey "+getString(R.string.user_first_name)+",";
        heyUserName.setText(heyUserNameMaker);


    }
}
