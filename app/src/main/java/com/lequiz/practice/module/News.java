package com.lequiz.practice.module;

public class News {
    private String newsHeadlines;
    private String newsTime;
    private String newsImageUrl;
    private String sourceUrl;

    public News(String newsHeadlines, String newsTime, String newsImageUrl, String sourceUrl) {
        this.newsHeadlines = newsHeadlines;
        this.newsTime = newsTime;
        this.newsImageUrl = newsImageUrl;
        this.sourceUrl = sourceUrl;
    }

    public String getNewsHeadlines() {
        return newsHeadlines;
    }

    public void setNewsHeadlines(String newsHeadlines) {
        this.newsHeadlines = newsHeadlines;
    }

    public String getNewsTime() {
        return newsTime;
    }

    public void setNewsTime(String newsTime) {
        this.newsTime = newsTime;
    }

    public String getNewsImageUrl() {
        return newsImageUrl;
    }

    public void setNewsImageUrl(String newsImageUrl) {
        this.newsImageUrl = newsImageUrl;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }
}
