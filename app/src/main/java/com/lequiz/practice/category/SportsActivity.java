package com.lequiz.practice.category;

import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.github.ksoichiro.android.observablescrollview.ScrollUtils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lequiz.practice.base.FullScreenStatusOnly;
import com.lequiz.practice.R;

import java.util.Objects;

public class SportsActivity extends AppCompatActivity implements ObservableScrollViewCallbacks {

    private View mToolbarView;
    private ObservableScrollView mScrollView;
    private int mParallaxImageHeight;
    RelativeLayout toolbarLayout;
    TextView title_text;
    ShimmerFrameLayout shimmerFrameLayout;
    CardView toolbar_card_view_2;
    FirebaseUser mUser;
    DatabaseReference currentUserRef;
    FirebaseAuth mAuth;
    String firstName;
    String fancyName;
    String lastName;
    String heyUserNameMaker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_sports);

        // Initializing mAuth

        mAuth = FirebaseAuth.getInstance();
        mUser = FirebaseAuth.getInstance().getCurrentUser();

        shimmerFrameLayout = findViewById(R.id.shimmer_layout_container_sport);


        // toolbar setup
        mToolbarView = findViewById(R.id.toolbar);
        setSupportActionBar((Toolbar) mToolbarView);
        getSupportActionBar().setTitle(null);
        title_text = findViewById(R.id.toolbar_title);
        title_text.setText(getResources().getText(R.string.sports));
        title_text.setTextColor(getResources().getColor(R.color.black));
        title_text.setAlpha(0);


        //mToolbarView.setBackgroundColor(ScrollUtils.getColorWithAlpha(0, getResources().getColor(R.color.white)));

        Objects.requireNonNull(getSupportActionBar()).setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_arrow_entertainment);
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

        mScrollView = (ObservableScrollView) findViewById(R.id.scroll);
        mScrollView.setScrollViewCallbacks(this);
        mParallaxImageHeight = getResources().getDimensionPixelSize(R.dimen.image_height_home_part_1);
        new FullScreenStatusOnly(this);

        // Heading TextView gradient
        TextView learnHeaderTech = findViewById(R.id.heading_on_sports);
        Shader textShader = new LinearGradient(0, 0, 180, 0,
                new int[]{getResources().getColor(R.color.orangeOnSports), getResources().getColor(R.color.pinkOnSports)},
                new float[]{0, 1}, Shader.TileMode.CLAMP);
        learnHeaderTech.getPaint().setShader(textShader);

        //UserName Initilization UU

        final TextView heyUserName = findViewById(R.id.hey_user_name);
        heyUserNameMaker = "Hey, ";

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Fetching name

        final String uId = mAuth.getCurrentUser().getUid();
        currentUserRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://lequiz-4abd1.firebaseio.com/Users/" + uId);

        currentUserRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try{
                    fancyName=dataSnapshot.child("fancyName").getValue().toString();
                    heyUserNameMaker+=fancyName;
                    heyUserName.setText(heyUserNameMaker);

                }
                catch(NullPointerException e)
                {
                    try{
                        firstName=dataSnapshot.child("firstName").getValue().toString();
                        heyUserNameMaker+=firstName;
                        System.out.println("last line fname "+heyUserNameMaker);
                        heyUserName.setText(heyUserNameMaker);

                    }
                    catch(NullPointerException f)
                    {
                        String displayName = mUser.getDisplayName();
                        int indexOfBlank=displayName.indexOf(" ");
                        firstName = displayName.substring(0,indexOfBlank);
                        lastName=displayName.substring(indexOfBlank+1);

                        heyUserNameMaker+=firstName;
                        heyUserName.setText(heyUserNameMaker);

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


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
    public void onDownMotionEvent() {

    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {
    }



    @Override
    protected void onResume() {
        super.onResume();
        shimmerFrameLayout.startShimmer();
    }

    @Override
    protected void onPause() {
        super.onPause();
        shimmerFrameLayout.stopShimmer();
    }

}
