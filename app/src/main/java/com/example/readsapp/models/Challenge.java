package com.example.readsapp.models;

import java.util.Date;

public class Challenge {
    private int days;
    private long numPages;
    private long pagesPerDay;
    private long pagesRead;
    private int percentageRead;
    private boolean isActive;

    public Challenge() {}

    public Challenge(int days, long numPages) {
        this.days = days;
        this.numPages = numPages;
        this.pagesPerDay = numPages/days;
        this.pagesRead = 0;
        this.percentageRead = 0;
        isActive = true;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public long getPagesPerDay() {
        return pagesPerDay;
    }

    public void setPagesPerDay(long pagesPerDay) {
        this.pagesPerDay = pagesPerDay;
    }

    public long getPagesRead() {
        return pagesRead;
    }

    public void setPagesRead(long pagesRead) {
        this.pagesRead = pagesRead;
    }

    public int getPercentageRead() {
        return percentageRead;
    }

    public void setPercentageRead(int percentageRead) {
        this.percentageRead = percentageRead;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void increaseReading() {
        pagesRead += pagesPerDay;
        percentageRead = ((int)pagesPerDay*100)/(int)numPages;
        if(pagesRead >= numPages) isActive = false;
    }

    private int daysBetween(Date d1, Date d2){
        return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
    }
}
