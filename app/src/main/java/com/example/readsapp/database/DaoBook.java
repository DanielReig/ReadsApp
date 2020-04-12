package com.example.readsapp.database;

import androidx.annotation.ArrayRes;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.readsapp.models.Book;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface DaoBook {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addBook(dbbook book);

    //the book is null
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addlist(dbbook list);

    @Delete
    void deleteBook(dbbook book);

    @Delete
    void deleteList(dbbook list);

    @Query("SELECT * FROM MyBooks WHERE listBook " + "= :text")
    List<dbbook> getBooks(String text);

    @Query("SELECT listBook FROM MyBooks WHERE book IS NULL OR book = ';'")
    List<String> getlist();
}
