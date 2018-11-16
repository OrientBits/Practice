package com.lequiz.practice.home;

import android.content.Context;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.github.ksoichiro.android.observablescrollview.ScrollUtils;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.lequiz.practice.R;
import com.lequiz.practice.module.JobViewHolder;
import com.lequiz.practice.module.Jobs;

import static com.lequiz.practice.R.id.job_alerts_recycler_view;
import static com.lequiz.practice.R.id.parent;


public class FragmentJobAlert extends Fragment implements ObservableScrollViewCallbacks {



    private ObservableScrollView mScrollView;
    private int mParallaxImageHeight;
    private RecyclerView recycler;
    Context mContext;
    private DatabaseReference jobAlertListRef;
    TextView headingOnJobAlerts;
    FirebaseRecyclerOptions<Jobs> jobOptions;
    FirebaseRecyclerAdapter<Jobs, JobViewHolder> mAdapter;
    public FragmentJobAlert() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflaterView = inflater.inflate(R.layout.fragment_job_alert, container, false);

        HomeContainer.toolbar_card_view_2.setVisibility(View.INVISIBLE);
        HomeContainer.title_text.setText(getText(R.string.job_alerts));
        headingOnJobAlerts = inflaterView.findViewById(R.id.heading_on_job_alerts);
        mScrollView = inflaterView.findViewById(R.id.job_alert_fragment_scroll);
        mScrollView.setScrollViewCallbacks(this);
        mParallaxImageHeight = getResources().getDimensionPixelSize(R.dimen.image_height_home_part_1);

        // Heading Text Gradient
        Shader textShader = new LinearGradient(0, 0, 180, 0,
                new int[]{getResources().getColor(R.color.violet_on_job_alert), getResources().getColor(R.color.pink_on_job_alert)},
                new float[]{0, 1}, Shader.TileMode.CLAMP);
        headingOnJobAlerts.getPaint().setShader(textShader);

        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Retrieving jobListData

        jobAlertListRef = FirebaseDatabase.getInstance().getReference().child("fJobAlertsList");
        jobAlertListRef.keepSynced(true);

        // Recyclerview

        recycler = inflaterView.findViewById(R.id.job_alerts_recycler_view);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(mContext));

        jobOptions = new FirebaseRecyclerOptions.Builder<Jobs>().setQuery(jobAlertListRef,Jobs.class).build();

        mAdapter = new FirebaseRecyclerAdapter<Jobs, JobViewHolder>(jobOptions) {
            @Override
            protected void onBindViewHolder(JobViewHolder holder, int position, Jobs model) {

                holder.jobTitle.setText(model.getJobTitle());
                holder.endDate.setText(model.getEndDate());
                holder.startDate.setText(model.getStartDate());
                holder.applyOnlineLink = model.getApplyOnlineLink();



            }

            @NonNull
            @Override
            public JobViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(mContext).inflate(R.layout.custom_row_for_job_alerts_vacancy, parent, false);
                return new JobViewHolder(view);
            }
        };

        mAdapter.startListening();
        recycler.setAdapter(mAdapter);




        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        return inflaterView;
    }




    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        onScrollChanged(mScrollView.getCurrentScrollY(), false, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;

    }

    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {
        int baseColor = getResources().getColor(R.color.colorPrimary);
        float alpha = Math.min(1, (float) scrollY / mParallaxImageHeight);
        //HomeContainer.mToolbarView.setBackgroundColor(ScrollUtils.getColorWithAlpha(alpha-(float)0.02, baseColor));
        HomeContainer.title_text.setAlpha(alpha-(float)0.035);
    }

    @Override
    public void onDownMotionEvent() {

    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {
    }



}
