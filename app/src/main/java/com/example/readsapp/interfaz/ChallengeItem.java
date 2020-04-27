package com.example.readsapp.interfaz;

import android.widget.ImageView;

public class ChallengeItem {
    private String title;
    private ImageView cover;
    private String url;
    private double percentage;

    public ChallengeItem() {}

    public ChallengeItem(String title, ImageView cover, String url, double percentage) {
        this.title = title;
        this.cover = cover;
        this.url = url;
        this.percentage = percentage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ImageView getCover() {
        return cover;
    }

    public void setCover(ImageView cover) {
        this.cover = cover;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }
}
