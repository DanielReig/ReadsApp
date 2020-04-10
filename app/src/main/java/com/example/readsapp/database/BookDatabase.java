package com.example.readsapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {dbbook.class}, version = 1,exportSchema = false)
public abstract class BookDatabase extends  RoomDatabase{
    private static BookDatabase BookDatabase;

    public synchronized static BookDatabase getInstance(Context context){
        if(BookDatabase == null) {
            BookDatabase = Room
                    .databaseBuilder(context, BookDatabase.class, "MyBooks").build();
        }
        return BookDatabase;
    }

    public abstract DaoBook BookDao();
}
