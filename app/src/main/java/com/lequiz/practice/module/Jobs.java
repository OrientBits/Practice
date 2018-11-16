package com.lequiz.practice.module;

public class Jobs {

    private String jobTitle;
    private String startDate;
    private String endDate;
    private String applyOnlineLink;

    public Jobs(String jobTitle, String startDate, String endDate, String applyOnlineLink) {
        this.jobTitle = jobTitle;
        this.startDate = startDate;
        this.endDate = endDate;
        this.applyOnlineLink = applyOnlineLink;
    }
    public Jobs()
    {

    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getApplyOnlineLink() {
        return applyOnlineLink;
    }

    public void setApplyOnlineLink(String applyOnlineLink) {
        this.applyOnlineLink = applyOnlineLink;
    }
}
