package com.lequiz.practice.home;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.github.ksoichiro.android.observablescrollview.ScrollUtils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lequiz.practice.Login;
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
import com.lequiz.practice.module.Users;
import com.lequiz.practice.nav_drawer.NavNotifications;
import com.lequiz.practice.nav_drawer.ProfileActivity;
import com.loopeer.shadow.ShadowView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.sql.Time;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class FragmentHome extends Fragment implements ObservableScrollViewCallbacks {

    MenuItem fav;
    FirebaseAuth mAuth;
    DatabaseReference currentUserRef;
    TextView userName, wishes;
    protected CircleImageView profile_home;
    private ObservableScrollView mScrollView;
    private int mParallaxImageHeight;
    protected ShadowView currentAffairs, computer, mathematics, reasoning, generalScience, english, technology, sports, special, entertainment;
    Resources profileImgDrawableToSet;
    String gender;
    String profileImgUrl;


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
        HomeContainer.title_text.setText(getResources().getText(R.string.home));
        HomeContainer.toolbar_card_view_2.setVisibility(View.VISIBLE);



        mScrollView = inflateView.findViewById(R.id.home_fragmnet_scroll);
        mScrollView.setScrollViewCallbacks(this);
        mParallaxImageHeight = getResources().getDimensionPixelSize(R.dimen.image_height_home_part_1);

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

        currentUserRef = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid());

     currentUserRef.addValueEventListener(new ValueEventListener() {
            @Override
          public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                   try
                   {
                   String loginStatus=Objects.requireNonNull(dataSnapshot.child("manualLoginStatus").getValue()).toString();


                    if(loginStatus.equals("F"))
                    {

                        mAuth.signOut();
                         startActivity(new Intent(getContext(),Login.class));
                        Toast.makeText(getActivity(),"Session Expired. You need to login again", Toast.LENGTH_SHORT).show();
                    }
                    }
            catch (NullPointerException e)
            {

            }
            }




@Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

          }
        });

// Checking if the user has current img url or not

        currentUserRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try
                {

                profileImgUrl=dataSnapshot.child("profileImgUrl").getValue().toString();}
                catch (NullPointerException e)
                {
                    System.out.println("is empty "+profileImgUrl);
                }


                if(TextUtils.isEmpty(profileImgUrl) && profileImgUrl==null  )

                {


                    // if profile Img url is empty then this code will run

                    currentUserRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            try
                            {
                                String gender=dataSnapshot.child("gender").getValue().toString();
                                System.out.println("Gender "+gender);
                                if(gender.equals("male"))
                                {
                                    Picasso.get()
                                            .load(profileImgUrl).error(R.drawable.male_avatar_placeholder).placeholder(R.drawable.male_avatar_placeholder).centerCrop()
                                            .networkPolicy(NetworkPolicy.OFFLINE)
                                            .into(profile_home, new Callback() {
                                                @Override
                                                public void onSuccess() {


                                                }

                                                @Override
                                                public void onError(Exception e) {


                                                    Picasso.get()
                                                            .load(profileImgUrl).placeholder(R.drawable.male_avatar_placeholder).centerCrop()
                                                            .error(R.drawable.male_avatar_placeholder)
                                                            .into(profile_home, new Callback() {
                                                                @Override
                                                                public void onSuccess() {
                                                                    Toast.makeText(getContext(), "Profile pic fetched successfully", Toast.LENGTH_SHORT).show();
                                                                }

                                                                @Override
                                                                public void onError(Exception e) {
                                                                    Log.v("Picasso", "Could not fetch image");
                                                                }


                                                            });

                                                }


                                            });}
                                if(gender.equals("female"))
                                {
                                    Picasso.get()
                                            .load(profileImgUrl).error(R.drawable.female_avatar_placeholder).placeholder(R.drawable.female_avatar_placeholder).centerCrop()
                                            .networkPolicy(NetworkPolicy.OFFLINE)
                                            .into(profile_home, new Callback() {
                                                @Override
                                                public void onSuccess() {


                                                }

                                                @Override
                                                public void onError(Exception e) {


                                                    Picasso.get()
                                                            .load(Users.getProfileImgUrl()).placeholder(R.drawable.female_avatar_placeholder).centerCrop()
                                                            .error(R.drawable.female_avatar_placeholder)
                                                            .into(profile_home, new Callback() {
                                                                @Override
                                                                public void onSuccess() {
                                                                    Toast.makeText(getContext(), "Profile pic fetched successfully", Toast.LENGTH_SHORT).show();
                                                                }

                                                                @Override
                                                                public void onError(Exception e) {
                                                                    Log.v("Picasso", "Could not fetch image");
                                                                }


                                                            });

                                                }


                                            });}
                            }
                            catch(NullPointerException e)
                            {
                               return;
                            }




                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            System.out.println("Database error 346");
                        }
                    });



                }
                else
                {

                    // If the profile Img Url is not empty. Setting the profile Img from the url
                    Picasso.get()
                            .load(profileImgUrl).placeholder(R.drawable.default_profile_picture)
                            .networkPolicy(NetworkPolicy.OFFLINE)
                            .into(profile_home, new Callback() {
                                @Override
                                public void onSuccess() {


                                }

                                @Override
                                public void onError(Exception e) {


                                    Picasso.get()
                                            .load(profileImgUrl).placeholder(R.drawable.default_profile_picture)

                                            .into(profile_home, new Callback() {
                                                @Override
                                                public void onSuccess() {
                                                    Toast.makeText(getContext(), "Profile pic fetched successfully", Toast.LENGTH_SHORT).show();
                                                }

                                                @Override
                                                public void onError(Exception e) {
                                                    Log.v("Picasso", "Could not fetch image");
                                                }


                                            });

                                }


                            });






                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });






















     /////////////////////////////////////////////////////////



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


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        onScrollChanged(mScrollView.getCurrentScrollY(), false, false);
    }


    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {
        int baseColor = getResources().getColor(R.color.colorPrimary);
        float alpha = Math.min(1, (float) scrollY / mParallaxImageHeight);
        HomeContainer.mToolbarView.setBackgroundColor(ScrollUtils.getColorWithAlpha(alpha-(float)0.02, baseColor));
        HomeContainer.title_text.setAlpha(alpha-(float)0.035);
    }

    @Override
    public void onDownMotionEvent() {

    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {
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
