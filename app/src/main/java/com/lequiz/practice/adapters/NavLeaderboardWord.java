package com.lequiz.practice.adapters;

public class NavLeaderboardWord {

    private String profileImgUrl;
    private String firstName;
    private String lastName;
    private Long xp;
    String uId;
    String fancyName;


    public NavLeaderboardWord(String profileImgUrl, String firstName, String lastName, Long xp, String uId, String fancyName) {
        this.profileImgUrl = profileImgUrl;
        this.firstName = firstName;
        this.lastName = lastName;
        this.xp = xp;
        this.uId = uId;
        this.fancyName = fancyName;

    }

    public NavLeaderboardWord() {



    }

    public String getuId() {
        return uId;
    }

    public String getProfileImgUrl() {
        return profileImgUrl;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Long getXp() {
        return xp;
    }

    public String getFancyName() {
        return fancyName;
    }
}
