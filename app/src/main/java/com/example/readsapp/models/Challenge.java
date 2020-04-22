package com.example.readsapp.models;

import java.util.Date;

public class Challenge {
    private Book book;
    private Date start;
    private Date finish;
    private long pagesPerDay;
    private long pagesRead;
    private int percentageRead;
    private boolean isActive;

    public Challenge(Book book, Date start, Date finish) {
        this.book = book;
        this.start = start;
        this.finish = finish;
        pagesPerDay = (book.getVolumeInfo().getPageCount())/(daysBetween(start,finish));
        pagesRead = 0;
        percentageRead = 0;
        isActive = true;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getFinish() {
        return finish;
    }

    public void setFinish(Date finish) {
        this.finish = finish;
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
        percentageRead += pagesRead/(book.getVolumeInfo().getPageCount());
        if(pagesRead >= book.getVolumeInfo().getPageCount()) isActive = false;
    }

    private int daysBetween(Date d1, Date d2){
        return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
    }
}
