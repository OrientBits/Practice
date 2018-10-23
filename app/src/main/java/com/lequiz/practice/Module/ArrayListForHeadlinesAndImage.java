package com.lequiz.practice.Module;

public class ArrayListForHeadlinesAndImage {
    private String headlines;
    private int imageId;

    public String getHeadlines() {
        return headlines;
    }

    public void setHeadlines(String headlines) {
        this.headlines = headlines;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public ArrayListForHeadlinesAndImage(String headlines, int imageId) {
        this.headlines = headlines;
        this.imageId = imageId;
    }
}
