package com.lequiz.practice.DataBase;

import android.content.Context;
import android.content.SharedPreferences;

import com.lequiz.practice.R;

public class SharedPreferenceConfig {

    private SharedPreferences sharedPreferences;
    private Context context;

    public SharedPreferenceConfig(Context context)
    {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(context.getResources().getString(R.string.login_preference), Context.MODE_PRIVATE);
    }


    public void writeLoginStatus(Boolean status)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(context.getResources().getString(R.string.login_status_preference),status);
        editor.apply();
    }

    public boolean readLoginStatus()
    {
        Boolean status;
        status = sharedPreferences.getBoolean(context.getResources().getString(R.string.login_status_preference),false);
        return status;
    }

}
