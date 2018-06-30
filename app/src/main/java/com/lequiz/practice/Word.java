package com.lequiz.practice;

import android.widget.ArrayAdapter;

public class Word {
    /**
     * this is for set elements in word instead of ArryAdapter
     */
    private String mText;

    /*this is for image on ListView*/
    private int mImageResourceId = -1;

    public Word(String text, int imageResourceId) {
        mText = text;
        mImageResourceId = imageResourceId;
    }

    /*
     * get default translation of the word */
    public String getText(){
        return mText;
    }
    /*
    /*get image of each text*/
    public int getImageResourceId(){ return mImageResourceId; }

}
