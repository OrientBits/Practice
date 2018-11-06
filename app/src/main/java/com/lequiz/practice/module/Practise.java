package com.lequiz.practice.module;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

public class Practise extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
