package com.lequiz.practice.category;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.github.ksoichiro.android.observablescrollview.ScrollUtils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.lequiz.practice.R;
import com.lequiz.practice.base.FullScreenStatusOnly;
import com.lequiz.practice.module.WeeklyCurrentAffairsModel;
import com.lequiz.practice.module.WeeklyCurrentAffairsViewHolder;

import java.util.Objects;

public class WeeklyCurrentAffairs extends AppCompatActivity implements ObservableScrollViewCallbacks {

    private View mToolbarView;
    private ObservableScrollView mScrollView;
    private int mParallaxImageHeight;
    RelativeLayout toolbarLayout;
    TextView title_text;
    CardView toolbar_card_view_2;

    RecyclerView recyclerView;
    DatabaseReference mDatabaseRefrence;
    FirebaseRecyclerOptions<WeeklyCurrentAffairsModel> options;
    FirebaseRecyclerAdapter<WeeklyCurrentAffairsModel, WeeklyCurrentAffairsViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_current_affairs);

        recyclerView = findViewById(R.id.weekly_current_affairs_recycler_view);
        recyclerView.setNestedScrollingEnabled(false);
        //recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mDatabaseRefrence = FirebaseDatabase.getInstance().getReference("weeklyCurrentAffairs");

        options = new FirebaseRecyclerOptions.Builder<WeeklyCurrentAffairsModel>().setQuery(mDatabaseRefrence, WeeklyCurrentAffairsModel.class).build();

        adapter = new FirebaseRecyclerAdapter<WeeklyCurrentAffairsModel, WeeklyCurrentAffairsViewHolder>(options) {


            @Override
            protected void onBindViewHolder(@NonNull WeeklyCurrentAffairsViewHolder holder, int position, @NonNull WeeklyCurrentAffairsModel model) {
                holder.title.setText(model.getTitle());
                holder.desc.setText(model.getDescription());
            }

            @NonNull
            @Override
            public WeeklyCurrentAffairsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_row_for_weekly_current_affairs, viewGroup, false);

                return new WeeklyCurrentAffairsViewHolder(view);



            }
        };


        adapter.startListening();
        recyclerView.setAdapter(adapter);

        mToolbarView = findViewById(R.id.toolbar);
        setSupportActionBar((Toolbar) mToolbarView);
        getSupportActionBar().setTitle(null);
        title_text = findViewById(R.id.toolbar_title);
        title_text.setText(getResources().getText(R.string.weekly_current_affairs));
        title_text.setTextColor(getResources().getColor(R.color.black));
        title_text.setAlpha(0);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_arrow_technology);
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

        mScrollView = findViewById(R.id.scroll_weekly_current_affairs);
        mScrollView.setScrollViewCallbacks(this);
        mParallaxImageHeight = getResources().getDimensionPixelSize(R.dimen.image_height_home_part_1);
        new FullScreenStatusOnly(this);

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
    protected void onStart() {
        super.onStart();
        if(adapter!=null)
        {
            adapter.startListening();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        if(adapter!=null)
        {
            adapter.startListening();
        }

    }

    @Override
    public void onDownMotionEvent() {

    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {
    }


}
