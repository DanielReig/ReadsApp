package com.example.readsapp.database;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.readsapp.models.Book;

import java.util.ArrayList;
import java.util.List;

public interface DaoBook {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addBook(dbbook book);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addlist(String list);

    @Delete
    void deleteBook(dbbook book);

    @Delete
    void deleteList(String list);

    @Query("SELECT * FROM MyBooks WHERE listBook " + "= :text")
    ArrayList<Book> getBooks(String text);

    @Query("SELECT listBook FROM MyBooks")
    ArrayList<String> getlist();
}
