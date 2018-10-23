package com.lequiz.practice.Adapters;

public class NavLeaderboardWord {

    private int imageId;
    private String name;
    private long xp;
    private long ranking;

    public NavLeaderboardWord(int imageId, String name, long xp, long ranking) {
        this.imageId = imageId;
        this.name = name;
        this.xp = xp;
        this.ranking = ranking;
    }

    public int getImageId() {
        return imageId;
    }

    public String getName() {
        return name;
    }

    public long getXp() {
        return xp;
    }

    public long getRanking() {
        return ranking;
    }

}
