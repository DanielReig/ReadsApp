package com.example.readsapp.database;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.readsapp.models.Book;

import java.util.List;

public interface DaoBook {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addBook(dbbook book);

    @Delete
    void deleteBook(dbbook book);

    @Query("SELECT * FROM MyBooks WHERE listBook " + "= :text")
    List<Book> getBooks(String text);

    @Query("SELECT listBook FROM MyBooks")
    List<String> getlist(String text);
}
