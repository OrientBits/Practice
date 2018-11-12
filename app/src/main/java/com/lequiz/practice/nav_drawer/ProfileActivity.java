package com.lequiz.practice.nav_drawer;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.github.ksoichiro.android.observablescrollview.ScrollUtils;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.lequiz.practice.Login;
import com.lequiz.practice.R;
import com.lequiz.practice.base.FullScreenStatusOnly;
import com.lequiz.practice.home.HomeContainer;
import com.lequiz.practice.module.Users;
import com.soundcloud.android.crop.Crop;
import com.soundcloud.android.crop.CropImageView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity implements ObservableScrollViewCallbacks {

    private View mToolbarView;

    String fName;


    private DatabaseReference refToSpecificUser;
    private ObservableScrollView mScrollView;
    private int mParallaxImageHeight;
    private CardView profileImageCardView;  // Profile section main Image variable
    private CircleImageView circleImageView;
    private ImageView pencilIconOnProfileImage;
    RelativeLayout toolbarLayout;
    private Uri imgToUpload;
    Uri profileImage;
    TextView title_text,user_name;
    CardView toolbar_card_view_2;
    FirebaseAuth mAuth;
    FirebaseUser firebaseUser;
    Uri imgProfile;
    Bitmap bitmap;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_profile);
        mAuth = FirebaseAuth.getInstance();
        circleImageView = findViewById(R.id.userImageProfileView);






        if(mAuth.getCurrentUser()!=null) {
            final String uId = mAuth.getCurrentUser().getUid();
            refToSpecificUser = FirebaseDatabase.getInstance().getReferenceFromUrl("https://lequiz-4abd1.firebaseio.com/Users/" + uId);





             //   if(Users.getProfileImgUrl()!=null)


                refToSpecificUser.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        try
                        {
                            Users.setProfileImgUrl(Objects.requireNonNull(dataSnapshot.child("profileImgUrl").getValue()).toString());

                            }
                        catch(NullPointerException e)
                        {

                        }
                        finally {
                            SharedPreferences.Editor editor = getSharedPreferences("userSharedPrefrences", MODE_PRIVATE).edit();
                            editor.putString("userProfileImgUrl", Users.getProfileImgUrl());
                            editor.apply();
                            System.out.println("finally " + Users.getProfileImgUrl());

                            System.out.println("Before "+Users.getProfileImgUrl());

                            if (!TextUtils.isEmpty(Users.getProfileImgUrl()))
                            {


                               Picasso.get()
                                        .load(Users.getProfileImgUrl()).placeholder(R.drawable.default_profile_picture)
                                        .networkPolicy(NetworkPolicy.OFFLINE)
                                        .into(circleImageView, new Callback() {
                                            @Override
                                            public void onSuccess() {


                                            }

                                            @Override
                                            public void onError(Exception e) {


                                                Picasso.get()
                                                        .load(Users.getProfileImgUrl()).placeholder(R.drawable.default_profile_picture)
                                                        .error(R.drawable.default_image_loading)
                                                        .into(circleImageView, new Callback() {
                                                            @Override
                                                            public void onSuccess() {
                                                                Toast.makeText(getApplicationContext(), "Profile pic updated successfully", Toast.LENGTH_SHORT).show();
                                                            }

                                                            @Override
                                                            public void onError(Exception e) {
                                                                Log.v("Picasso", "Could not fetch image");
                                                            }


                                                        });

                                            }


                                        });
                        } }

                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }












        // Finding profile section main image variable

        profileImageCardView = findViewById(R.id.profile_image_card_view);

        pencilIconOnProfileImage = findViewById(R.id.pencil_icon_on_profile_image);



        SharedPreferences sharedPreferences = getSharedPreferences("userSharedPrefrences",MODE_PRIVATE);
        String url=sharedPreferences.getString("userProfileImgUrl","");
        Users.setProfileImgUrl(url);










        // Setting onClickListener to profile Image CardView

        profileImageCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 showImageChooser();

            }
        });

        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                  showImageChooser();

            }
        });

        pencilIconOnProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                  showImageChooser();

            }
        });


        user_name = findViewById(R.id.profile_user_name);

        // toolbar setup
        mToolbarView = findViewById(R.id.toolbar);
        setSupportActionBar((Toolbar) mToolbarView);
        getSupportActionBar().setTitle(null);
        title_text = findViewById(R.id.toolbar_title);
        title_text.setText(getResources().getText(R.string.profile));
        title_text.setTextColor(getResources().getColor(R.color.black));
        title_text.setAlpha(0);


        //mToolbarView.setBackgroundColor(ScrollUtils.getColorWithAlpha(0, getResources().getColor(R.color.white)));

        Objects.requireNonNull(getSupportActionBar()).setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back_arrow_default);
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


        userNameInGradient();


        mScrollView = (ObservableScrollView) findViewById(R.id.scroll);
        mScrollView.setScrollViewCallbacks(this);
        mParallaxImageHeight = getResources().getDimensionPixelSize(R.dimen.image_height_home_part_1);
        new FullScreenStatusOnly(this);




    }




    // Getting image from the chooser

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1 && resultCode==RESULT_OK && data!=null && data.getData()!=null)
        {

            profileImage=data.getData();
            Uri destinationUri = Uri.fromFile(new File(getCacheDir(),"cropped"));

            Crop.of(profileImage, destinationUri).asSquare().start(this);
       //     circleImageView.setImageURI(Crop.getOutput(data));
            imgToUpload = Crop.getOutput(data);
            uploadToFirebase();

        }
        if(requestCode==Crop.REQUEST_CROP)
        {
            handleCrop(resultCode, data);
        }
    }

    private void uploadToFirebase() {
        final StorageReference profileImageRefrence = FirebaseStorage.getInstance().getReference("profilePics/"+mAuth.getCurrentUser().getUid()+".jpg");
        if(imgToUpload!=null)
        {
            profileImageRefrence.putFile(imgToUpload).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    profileImageRefrence.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            String downloadUrl=uri.toString();
                            Users.setProfileImgUrl(downloadUrl);
                            refToSpecificUser.child("profileImgUrl").setValue(downloadUrl);
                            SharedPreferences.Editor editor =  getSharedPreferences("userSharedPrefrences",MODE_PRIVATE).edit();
                            editor.putString("userProfileImgUrl",Users.getProfileImgUrl());
                            editor.apply();








                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            // Fetching profile image download failed

                            Toast.makeText(getApplicationContext(),""+e,Toast.LENGTH_SHORT).show();


                        }
                    });

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    Toast.makeText(getApplicationContext(),""+e,Toast.LENGTH_SHORT).show();
                    return;
                }
            });




        }

    }

    private void handleCrop(int resultCode, Intent result) {

        if(resultCode==RESULT_OK)
        {


            imgToUpload = Crop.getOutput(result);
            imgProfile = Crop.getOutput(result);
            uploadToFirebase();
        }
            else if(resultCode==Crop.RESULT_ERROR)
        {
            Toast.makeText(getApplicationContext(),"Problem updating profile image",Toast.LENGTH_SHORT).show();
        }



    }


    // Image chooser function

    public void showImageChooser()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Profile Image"), 1);
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

    public void userNameInGradient() {
        Shader textShader = new LinearGradient(0, 0, 180, 0,
                new int[]{getResources().getColor(R.color.purpleOnHomeText), getResources().getColor(R.color.blueOnProfileText)},
                new float[]{0, 1}, Shader.TileMode.CLAMP);
        user_name.getPaint().setShader(textShader);
    }
}