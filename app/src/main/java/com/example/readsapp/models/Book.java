package com.example.readsapp.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Book {

    private int val;
    private boolean read;
    private boolean reading;
    private boolean pending;
    private List<BookList> listed;
    private int pagesRead;

    /** Provided by API**/
    private List<String> identifiers;
    private List<String> authors;
    private List<String> subjects;
    private List<String> publishers;
    private Date publishDate;
    private List<String> covers;
    private int pages;

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public boolean isReading() {
        return reading;
    }

    public void setReading(boolean reading) {
        this.reading = reading;
    }

    public boolean isPending() {
        return pending;
    }

    public void setPending(boolean pending) {
        this.pending = pending;
    }

    public List<BookList> getListed() {
        return listed;
    }

    public void setListed(List<BookList> listed) {
        this.listed = listed;
    }

    public int getPagesRead() {
        return pagesRead;
    }

    public void setPagesRead(int pagesRead) {
        this.pagesRead = pagesRead;
    }

    public List<String> getIdentifiers() {
        return identifiers;
    }

    public void setIdentifiers(List<String> identifiers) {
        this.identifiers = identifiers;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }

    public List<String> getPublishers() {
        return publishers;
    }

    public void setPublishers(List<String> publishers) {
        this.publishers = publishers;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public List<String> getCovers() {
        return covers;
    }

    public void setCovers(List<String> covers) {
        this.covers = covers;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public void bookDefault(){
        this.val = 4;
        this.read = false;
        this.pending = true;
        this.reading = false;
        this.listed = new ArrayList<>();
        this.pagesRead = 50;
        this.identifiers = new ArrayList<>();
        this.authors = new ArrayList<>();
        this.authors.add("Nicola Yoon");
        this.subjects = new ArrayList<>();
        this.subjects.add("Fiction");
        this.publishers = new ArrayList<>();
        this.publishers.add("Delacorte Pr");
        this.publishDate = new Date("2016-11");
        this.covers = new ArrayList<>();
        this.covers.add("https://covers.openlibrary.org/b/id/8918190-M.jpg");
        this.pages= 384;
    }
}
