package com.example.readsapp.models;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Book {
    private String id;
    private VolumeInfo volumeInfo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public VolumeInfo getVolumeInfo() {
        return volumeInfo;
    }

    public void setVolumeInfo(VolumeInfo volumeInfo) {
        this.volumeInfo = volumeInfo;
    }

    public void setBook(Book book){
        this.id = book.getId();
        this.volumeInfo = book.getVolumeInfo();
    }

    public String getSmallThumbnail() {
        return this.volumeInfo.getImageLinks().getSmallThumbnail();
    }

    public void setSmallThumbnail(String smallThumbnail) {
        this.volumeInfo.getImageLinks().setSmallThumbnail(smallThumbnail);
    }

    public String getThumbnail() {
        return this.volumeInfo.getImageLinks().getThumbnail();
    }

    public void setThumbnail(String thumbnail) {
        this.volumeInfo.getImageLinks().setThumbnail(thumbnail);
    }

    public String getSmall() {
        return this.volumeInfo.getImageLinks().getSmall();
    }

    public void setSmall(String small) {
        this.volumeInfo.getImageLinks().setSmall(small);
    }

    public String getMedium() {
        return this.volumeInfo.getImageLinks().getMedium();
    }

    public void setMedium(String medium) {
        this.volumeInfo.getImageLinks().setMedium(medium);
    }

    public String getLarge() {
        return this.volumeInfo.getImageLinks().getLarge();
    }

    public void setLarge(String large) {
        this.volumeInfo.getImageLinks().setLarge(large);
    }

    public String getExtraLarge() {
        return this.volumeInfo.getImageLinks().getExtraLarge();
    }

    public void setExtraLarge(String extraLarge) {
        this.volumeInfo.getImageLinks().setExtraLarge(extraLarge);
    }

}
