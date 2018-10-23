package com.lequiz.practice.Base;

import android.content.Context;

public class StatusBarHieght
{
    int statusBarHeight = 0;

    public StatusBarHieght(Context context)
    {
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = context.getResources().getDimensionPixelSize(resourceId);
        }

    }


    public int getStatusBarHieght(){
        return statusBarHeight;
    }

}
