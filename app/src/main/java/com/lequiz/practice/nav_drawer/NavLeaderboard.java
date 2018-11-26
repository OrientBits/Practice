package com.lequiz.practice.nav_drawer;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceIdReceiver;
import com.lequiz.practice.adapters.NavLeaderboardWord;
import com.lequiz.practice.R;
import com.lequiz.practice.module.LeaderBoardViewHolder;
import com.lequiz.practice.module.Users;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class NavLeaderboard extends AppCompatActivity {

    protected Toolbar toolbar;
    CardView toolbar_card_view_2;
    RecyclerView recyclerView;
    String rank;
    DatabaseReference databaseReferenceUsers;
    FirebaseRecyclerOptions<NavLeaderboardWord> options;
    FirebaseRecyclerAdapter<NavLeaderboardWord, LeaderBoardViewHolder> adapter;
    FirebaseAuth mAuth;
    String profileImgUrl;
    String firstName;
    String lastName;
    String fancyName;
    String fullName;
    String userXP;
    TextView textViewUserOwnName, textViewUserXP, textViewUserRank;
    FirebaseUser mUser;
    ImageView userOwnPicImageView;


    @Override
    protected void onStart() {

        super.onStart();


    }

    @Override
    protected void onStop() {
        super.onStop();
        if(adapter!=null)
        {
        adapter.stopListening();}
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(adapter!=null)
        {
            adapter.startListening();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_leaderboard);
        recyclerView = findViewById(R.id.recycler_view_on_leader_board);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true); // Descending order list
        recyclerView.setLayoutManager(linearLayoutManager);
        mUser = FirebaseAuth.getInstance().getCurrentUser();
        mAuth = FirebaseAuth.getInstance();
        userOwnPicImageView = findViewById(R.id.profile_pic_on_leaderboard);
        textViewUserOwnName = findViewById(R.id.username_on_leaderboard);
        textViewUserXP = findViewById(R.id.xp_text_view_on_leaderboard);
        textViewUserRank = findViewById(R.id.user_rank_on_leaderboard);

        databaseReferenceUsers = FirebaseDatabase.getInstance().getReference().child("Users");

        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // Fetching current user's name

        if(mAuth.getCurrentUser()!=null) {
            final String uId = mAuth.getCurrentUser().getUid();


            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            // Fetching name from database

            databaseReferenceUsers.child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    try
                    {
                        fancyName = dataSnapshot.child("fancyName").getValue().toString();
                        textViewUserOwnName.setText(fancyName);
                        userXP="Total XP: ";
                        userXP += dataSnapshot.child("xp").getValue().toString();

                        textViewUserXP.setText(userXP);

                    }
                    catch (NullPointerException e)
                    {

                        try {


                            firstName = dataSnapshot.child("firstName").getValue().toString();
                            lastName = dataSnapshot.child("lastName").getValue().toString();
                            userXP="Total XP: ";
                            userXP += dataSnapshot.child("xp").getValue().toString();

                            textViewUserXP.setText(userXP);
                            fullName= firstName+" "+lastName;
                            textViewUserOwnName.setText(fullName);



                        }
                        catch (NullPointerException f)
                        {
                            fullName = mUser.getDisplayName();
                            textViewUserOwnName.setText(fullName);
                            userXP="Total XP: ";
                            userXP += dataSnapshot.child("xp").getValue().toString();
                            textViewUserXP.setText(userXP);
                        }
                    }

                    try{
                        rank=dataSnapshot.child("ranking").getValue().toString();
                        textViewUserRank.setText(rank);
                    }
                    catch (NullPointerException f)
                    {
                        }



                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // Setting user's own profile pic

        // Checking if the user has current img url or not


        databaseReferenceUsers.child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try
                {

                    profileImgUrl=dataSnapshot.child("profileImgUrl").getValue().toString();}
                catch (NullPointerException e)
                {
                    try{
                        // Fetching google photo
                        profileImgUrl=mUser.getPhotoUrl().toString();}
                    catch(NullPointerException f)
                    {

                    }
                }


                if(TextUtils.isEmpty(profileImgUrl) && profileImgUrl==null )

                {


                    // if profile Img url is empty then this code will run

                    databaseReferenceUsers.child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            try
                            {
                                String gender=dataSnapshot.child("gender").getValue().toString();

                                if(gender.equals("male"))
                                {
                                    Picasso.get()
                                            .load(profileImgUrl).error(R.drawable.male_avatar_placeholder).placeholder(R.drawable.male_avatar_placeholder)
                                            .networkPolicy(NetworkPolicy.OFFLINE)
                                            .into(userOwnPicImageView, new Callback() {
                                                @Override
                                                public void onSuccess() {


                                                }

                                                @Override
                                                public void onError(Exception e) {


                                                    Picasso.get()
                                                            .load(profileImgUrl).placeholder(R.drawable.male_avatar_placeholder)
                                                            .error(R.drawable.male_avatar_placeholder)
                                                            .into(userOwnPicImageView, new Callback() {
                                                                @Override
                                                                public void onSuccess() {
                                                                    Toast.makeText(getApplicationContext(), "Profile pic fetched successfully", Toast.LENGTH_SHORT).show();
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
                                    System.out.println("Inside female");
                                    Picasso.get()
                                            .load(profileImgUrl).error(R.drawable.female_avatar_placeholder).placeholder(R.drawable.female_avatar_placeholder)
                                            .networkPolicy(NetworkPolicy.OFFLINE)
                                            .into(userOwnPicImageView, new Callback() {
                                                @Override
                                                public void onSuccess() {

                                                }

                                                @Override
                                                public void onError(Exception e) {


                                                    Picasso.get()
                                                            .load(Users.getProfileImgUrl()).placeholder(R.drawable.female_avatar_placeholder)
                                                            .error(R.drawable.female_avatar_placeholder)
                                                            .into(userOwnPicImageView, new Callback() {
                                                                @Override
                                                                public void onSuccess() {
                                                                    Toast.makeText(getApplicationContext(), "Profile pic fetched successfully", Toast.LENGTH_SHORT).show();
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
                            .into(userOwnPicImageView, new Callback() {
                                @Override
                                public void onSuccess() {


                                }

                                @Override
                                public void onError(Exception e) {


                                    Picasso.get()
                                            .load(profileImgUrl).placeholder(R.drawable.default_profile_picture)

                                            .into(userOwnPicImageView, new Callback() {
                                                @Override
                                                public void onSuccess() {
                                                    Toast.makeText(getApplicationContext(), "Profile pic fetched successfully", Toast.LENGTH_SHORT).show();
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

















        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////




        Query query = databaseReferenceUsers.orderByChild("xp");

        options = new FirebaseRecyclerOptions.Builder<NavLeaderboardWord>().setQuery(query, NavLeaderboardWord.class).build();


        adapter = new FirebaseRecyclerAdapter<NavLeaderboardWord, LeaderBoardViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final LeaderBoardViewHolder holder, int position, @NonNull final NavLeaderboardWord model) {

                // If the profile Img Url is not empty. Setting the profile Img from the url
                Picasso.get()
                        .load(model.getProfileImgUrl()).placeholder(R.drawable.default_profile_picture)
                        .networkPolicy(NetworkPolicy.OFFLINE)
                        .into(holder.userImg, new Callback() {
                            @Override
                            public void onSuccess() {


                            }

                            @Override
                            public void onError(Exception e) {






                                Picasso.get()
                                        .load(model.getProfileImgUrl()).placeholder(R.drawable.default_profile_picture)

                                        .into(holder.userImg, new Callback() {
                                            @Override
                                            public void onSuccess() {
                                             //   Toast.makeText(getApplicationContext(), "Profile pic fetched successfully", Toast.LENGTH_SHORT).show();
                                            }

                                            @Override
                                            public void onError(Exception e) {
                                            //    Log.v("Picasso", "Could not fetch image");
                                            }


                                        });


                            }


                        });
               if(model.getFancyName()!=null)
               {
                    String fancyName = model.getFancyName();
                    holder.userName.setText(fancyName);}

                else
                {
                    String fullName = model.getFirstName()+" "+model.getLastName();
                    holder.userName.setText(fullName);
                }

                try{
                Long xp = model.getXp();
                String mXP = "+ ";
                mXP += Long.toString(xp);
                mXP+=" XP";
                holder.userXp.setText(mXP);}
                catch (NullPointerException e)
                {

                }

                int revPosition = position;
                int sortedPosition=getItemCount()-revPosition;
                rank=Integer.toString(sortedPosition);
             //   holder.ranking.setText(rank);
                try{
                if(model.getuId().equals(mAuth.getCurrentUser().getUid().toString()))
                {
                    databaseReferenceUsers.child(mAuth.getCurrentUser().getUid()).child("ranking").setValue(rank);
                }




                }
                catch (NullPointerException e)
                {

                }


            }



            @Override
            public int getItemCount() {
                return super.getItemCount();
            }

            @NonNull
            @Override
            public LeaderBoardViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_row_for_leaderboard, viewGroup, false);

                return new LeaderBoardViewHolder(view);

            }

        };
        adapter.startListening();
        recyclerView.setAdapter(adapter);

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



        toolbar = findViewById(R.id.toolbar);



        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_arrow_default);
        toolbar_card_view_2 = findViewById(R.id.toolbar_card_view_2);
        toolbar_card_view_2.setVisibility(View.INVISIBLE);


        // for status bar color
  /*      FullScreenStatusOnly fullScreenStatusOnly = new FullScreenStatusOnly(this);


        ArrayList<NavLeaderboardWord> leaderboardWords = new ArrayList<>();
        leaderboardWords.add(new NavLeaderboardWord(R.drawable.mithlesh,"Ramshek Rama",100000,1));
        leaderboardWords.add(new NavLeaderboardWord(R.drawable.mithlesh,"Rishabh Raj",15500,2));
        leaderboardWords.add(new NavLeaderboardWord(R.drawable.home_reasoning,"Mithlesh Arya",12000,4));
        leaderboardWords.add(new NavLeaderboardWord(R.drawable.home_current_affairs,"Jyotsana Devi",2000,5));
        leaderboardWords.add(new NavLeaderboardWord(R.drawable.home_computer,"Rashi Bhabhi",2020,9));
        leaderboardWords.add(new NavLeaderboardWord(R.drawable.home_mathematics,"Botali Devi",1500,10));
        leaderboardWords.add(new NavLeaderboardWord(R.drawable.home_general_science,"Suraj Soni",1200,16));
        leaderboardWords.add(new NavLeaderboardWord(R.drawable.home_technology,"Mithlesh Ranaji",1000,14));
        leaderboardWords.add(new NavLeaderboardWord(R.drawable.home_current_affairs,"Akshay Cha",900,13));
        leaderboardWords.add(new NavLeaderboardWord(R.drawable.home_sport,"Bull ",850,11));
        leaderboardWords.add(new NavLeaderboardWord(R.drawable.home_computer,"Ramshek Rama",810,17));
        leaderboardWords.add(new NavLeaderboardWord(R.drawable.home_english,"Ramshek Rama",800,18));
        leaderboardWords.add(new NavLeaderboardWord(R.drawable.home_technology,"Ramshek Rama",750,19));
        leaderboardWords.add(new NavLeaderboardWord(R.drawable.home_reasoning,"Ramshek Rama",700,21));


        NavLeaderboardWordAdapter leaderboardWordAdapter = new NavLeaderboardWordAdapter(this,leaderboardWords);

        ListView listView = findViewById(R.id.leaderboard_list_view);

        listView.setAdapter(leaderboardWordAdapter);   **/


    }
}}
