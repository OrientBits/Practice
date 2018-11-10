package com.lequiz.practice.home;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lequiz.practice.R;
import com.lequiz.practice.nav_drawer.NavNotifications;
import com.loopeer.shadow.ShadowView;

import java.sql.Time;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentHome extends Fragment {

    MenuItem fav;
    FirebaseAuth mAuth;
    DatabaseReference currentUserRef;
    TextView userName, wishes;
    protected ShadowView currentAffairs, computer, mathematics, reasoning, generalScience, english, technology, sports, special, entertainment;


    public FragmentHome() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflateView = inflater.inflate(R.layout.fragment_home, container, false);
        setHasOptionsMenu(true);

        mAuth  = FirebaseAuth.getInstance();

        userName = inflateView.findViewById(R.id.user_name);
        wishes = inflateView.findViewById(R.id.wishing);
        userNameInGradient();

        currentAffairs = inflateView.findViewById(R.id.current_affairs_shadow_view);
        shadowViewSize(currentAffairs);
        computer = inflateView.findViewById(R.id.computer_shadow_view);
        shadowViewSize(computer);
        mathematics = inflateView.findViewById(R.id.mathematics_shadow_view);
        shadowViewSize(mathematics);
        reasoning = inflateView.findViewById(R.id.reasoning_shadow_view);
        shadowViewSize(reasoning);
        generalScience = inflateView.findViewById(R.id.general_science_shadow_view);
        shadowViewSize(generalScience);
        english = inflateView.findViewById(R.id.english_shadow_view);
        shadowViewSize(english);
        technology = inflateView.findViewById(R.id.technology_shadow_view);
        shadowViewSize(technology);
        sports = inflateView.findViewById(R.id.sports_shadow_view);
        shadowViewSize(sports);
        special = inflateView.findViewById(R.id.special_shadow_view);
        shadowViewSize(special);
        entertainment = inflateView.findViewById(R.id.entertainment_shadow_view);
        shadowViewSize(entertainment);



//        // Firebase Setup
//
//        currentUserRef = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid());
//
//        currentUserRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                try
//                {
//                    String loginStatus=Objects.requireNonNull(dataSnapshot.child("manualLoginStatus").getValue()).toString();
//                    if(loginStatus.equals("F"))
//                    {
//                        mAuth.getInstance().signOut();
//                        // startActivity(new Intent(HomeActivity.this,Login.class));
//                        Toast.makeText(getActivity(),"Session Expired. You need to login again", Toast.LENGTH_SHORT).show();
//                    }
//
//
//                }
//                catch (NullPointerException e)
//                {
//
//                }
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//



        return inflateView;
    }



    public void shadowViewSize(final ShadowView shadowView){
        ViewTreeObserver vto1 = shadowView.getViewTreeObserver();
        vto1.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                shadowView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                int width = shadowView.getMeasuredWidth();
                ViewGroup.LayoutParams params = shadowView.getLayoutParams();
                params.height = (width * 77) / 100;
                shadowView.setLayoutParams(params);
            }
        });
    }












    // more option right top corner (here only notification)
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.notifications){
            startActivity(new Intent(getActivity(),NavNotifications.class));
            return true;
        }
        return false;
    }


    @SuppressLint("SetTextI18n")
    public void userNameInGradient() {
        Shader textShader = new LinearGradient(0, 0, 180, 0,
                new int[]{getResources().getColor(R.color.blueOnHomeText), getResources().getColor(R.color.purpleOnHomeText)},
                new float[]{0, 1}, Shader.TileMode.CLAMP);
        userName.getPaint().setShader(textShader);



        int hours = new Time(System.currentTimeMillis()).getHours();
        if (hours >= 5 && hours < 12) {
            wishes.setText("Good morning ");
        } else if (hours >= 12 && hours < 18) {
            wishes.setText("Good afternoon ");
        } else if (hours >= 18 && hours < 22) {
            wishes.setText("Good evening");
        } else
            wishes.setText("Good night");

        Shader textShader1 = new LinearGradient(0, 0, 180, 0,
                new int[]{getResources().getColor(R.color.blueOnHomeText), getResources().getColor(R.color.purpleOnHomeText)},
                new float[]{0, 1}, Shader.TileMode.CLAMP);
        wishes.getPaint().setShader(textShader1);
    }



}
