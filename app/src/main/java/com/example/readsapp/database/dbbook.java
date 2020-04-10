package com.example.readsapp.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.readsapp.models.Book;

import java.util.ArrayList;

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

    public dbbook(){}

    public dbbook(String b,String s){
        this.book = b;
        this.list = s;
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
}
