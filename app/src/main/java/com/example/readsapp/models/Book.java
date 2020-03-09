package com.example.readsapp.models;

import java.util.Date;
import java.util.List;

public class Book {
    private int val;
    private boolean read;
    private boolean reading;
    private boolean pending;
    private List<BookList> listed;
    private int pagesRead;

    private String ISBN;
    private String preview;
    private String thumbnail;
    private List<String> authors;
    private String classifications;
    private List<String> subjects;
    private List<String> publishers;
    private List<String> publishPlaces;
    private Date publishDate;
}
