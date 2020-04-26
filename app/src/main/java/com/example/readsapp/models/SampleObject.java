package com.example.readsapp.models;

public class SampleObject {
    private String title;
    private String author;
    private int coverID;
    private long numPages;
    private int days;
    private long pagesPerDay;
    private long pagesRead;
    private int percentageRead;
    private boolean isActive;

    public SampleObject() {}

    public SampleObject(String title, String author, int coverID, long numPages, int days) {
        this.title = title;
        this.author = author;
        this.coverID = coverID;
        this.numPages = numPages;
        this.days = days;
        pagesPerDay = numPages/days;
        pagesRead = 0;
        percentageRead = 0;
        isActive = true;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getCoverID() {
        return coverID;
    }

    public void setCoverID(int coverID) {
        this.coverID = coverID;
    }

    public long getNumPages() {
        return numPages;
    }

    public void setNumPages(long numPages) {
        this.numPages = numPages;
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
}
