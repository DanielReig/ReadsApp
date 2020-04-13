package com.example.readsapp.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

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

}
