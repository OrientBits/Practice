package com.lequiz.practice.home;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.lequiz.practice.R;
import com.lequiz.practice.category.ComputerActivity;
import com.lequiz.practice.category.CurrentAffairsActivity;
import com.lequiz.practice.category.EnglishActivity;
import com.lequiz.practice.category.EntertainmentActivity;
import com.lequiz.practice.category.GeneralScienceActivity;
import com.lequiz.practice.category.MathematicsActivity;
import com.lequiz.practice.category.ReasoningActivity;
import com.lequiz.practice.category.SpecialActivity;
import com.lequiz.practice.category.SportsActivity;
import com.lequiz.practice.category.TechnologyActivity;
import com.lequiz.practice.nav_drawer.NavNotifications;
import com.lequiz.practice.nav_drawer.ProfileActivity;
import com.loopeer.shadow.ShadowView;

import java.sql.Time;

import de.hdodenhof.circleimageview.CircleImageView;

public class FragmentHome extends Fragment {

    MenuItem fav;
    FirebaseAuth mAuth;
    DatabaseReference currentUserRef;
    TextView userName, wishes;
    protected CircleImageView profile_home;


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

        profile_home = inflateView.findViewById(R.id.profile_home);



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



        /*setting on click listener on shadow view*/

        currentAffairs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),CurrentAffairsActivity.class));
            }
        });
        computer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),ComputerActivity.class));
            }
        });
        mathematics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),MathematicsActivity.class));
            }
        });
        reasoning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),ReasoningActivity.class));
            }
        });
        generalScience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),GeneralScienceActivity.class));
            }
        });
        english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),EnglishActivity.class));
            }
        });
        technology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),TechnologyActivity.class));
            }
        });
        sports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),SportsActivity.class));
            }
        });
        special.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),SpecialActivity.class));
            }
        });
        entertainment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),EntertainmentActivity.class));
            }
        });


        profile_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),ProfileActivity.class));
            }
        });

        /*end of set on click listener */




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
