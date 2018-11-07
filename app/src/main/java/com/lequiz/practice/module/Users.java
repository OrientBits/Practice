package com.lequiz.practice.module;

public class Users {
    static String firstName;
    static String lastName;
    static String email;
    static String profileImgUrl;

    public Users(String firstName, String lastName, String email) {
        Users.firstName = firstName;
        Users.lastName = lastName;
        Users.email = email;

    }

    public static String getProfileImgUrl() {
        return profileImgUrl;
    }

    public static void setProfileImgUrl(String profileImgUrl) {
        Users.profileImgUrl = profileImgUrl;
    }

    public String getFirstName() {
        return Users.firstName;
    }

    public void setFirstName(String firstName) {
        Users.firstName = firstName;
    }

    public String getLastName() {
        return Users.lastName;
    }

    public void setLastName(String lastName) {
        Users.lastName = lastName;
    }

    public String getEmail() {
        return Users.email;
    }

    public void setEmail(String email) {
        Users.email = email;
    }
}
