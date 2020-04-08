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
    private Book book;

    @NonNull
    @ColumnInfo(name = "listBook")
    private ArrayList<String> list;

    public dbbook(Book b, ArrayList<String> a){
        this.book = b;
        this.list = a;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public ArrayList<String> getList() {
        return list;
    }

    public void setList(@NonNull ArrayList<String> list) {
        this.list = list;
    }
}
