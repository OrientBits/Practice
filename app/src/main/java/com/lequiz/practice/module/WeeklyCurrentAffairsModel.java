package com.lequiz.practice.module;

public class WeeklyCurrentAffairsModel {


    String description;
    String title;

    public WeeklyCurrentAffairsModel(String description, String title) {
        this.title = title;
        this.description = description;
    }

    public WeeklyCurrentAffairsModel() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
