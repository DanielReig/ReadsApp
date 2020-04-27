package com.example.readsapp.models;

import java.util.Date;

public class Challenge {
    private int days;
    private long numPages;
    private long pagesPerDay;
    private long pagesRead;
    private double percentageRead;
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

    public double getPercentageRead() {
        return percentageRead;
    }

    public void setPercentageRead(double percentageRead) {
        this.percentageRead = percentageRead;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void increaseReading() {
        if(isActive) {
            pagesRead += pagesPerDay;
            percentageRead += ((pagesPerDay*100)/numPages);
            if(pagesRead >= numPages) {
                isActive = false;
                pagesRead = numPages;
                percentageRead = 100;
            }
        }
    }
}
