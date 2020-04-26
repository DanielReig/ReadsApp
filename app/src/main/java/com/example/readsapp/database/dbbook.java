package com.example.readsapp.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "MyBooks")
public class dbbook {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_ID")
    private int id;

    @ColumnInfo(name = "book")
    private String book;

    @NonNull
    @ColumnInfo(name = "listBook")
    private String list;

    @ColumnInfo(name = "Date")
    private String date;

    @ColumnInfo(name = "challenge")
    private String challenge;

    public dbbook(){}

    public dbbook(String b,String s){
        this.book = b;
        this.list = s;
        this.date = "";
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getList() {
        return list;
    }

    public void setList(String list) {
        this.list = list;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getChallenge() {
        return challenge;
    }
    public void setChallenge(String challenge) {
        this.challenge = challenge;
    }
}
