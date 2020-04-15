package com.example.readsapp.database;

import android.net.Uri;
import android.widget.ImageView;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.sql.Blob;

@Dao
public interface DaoUser {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addUser(dbUser book);

    @Delete
    void deleteUser(dbUser book);

    @Query("SELECT search FROM MyUser")
    String getSearch();

    @Query("SELECT age FROM MyUser")
    int getAge();

    @Query("SELECT name FROM MyUser")
    String getNameUser();

    @Query("SELECT * FROM MyUser")
    dbUser getUser();

    @Update
    void UpdateUser(dbUser user);

    @Query("SELECT image FROM MyUser")
    String getImageUser();

}
