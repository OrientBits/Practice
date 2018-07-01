package com.lequiz.practice;

import android.widget.ArrayAdapter;

public class Word {
    /**
     * this is for set elements in word instead of ArryAdapter
     */
    private String mText;

    /*this is for image on ListView*/
    private int mImageResourceId = -1;
    private int mColorResourceId;

    public Word(String text, int imageResourceId,int colorResourceId) {
        mText = text;
        mImageResourceId = imageResourceId;
        mColorResourceId= colorResourceId;
    }

    /*
     * get default translation of the word */
    public String getText(){
        return mText;
    }
    /*
    /*get image of each text*/

    public int getColorResourceId(){  return mColorResourceId; }
    public int getImageResourceId(){ return mImageResourceId; }

}
