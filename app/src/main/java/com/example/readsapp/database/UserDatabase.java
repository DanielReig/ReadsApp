package com.example.readsapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {dbUser.class}, version = 1,exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {
    private static UserDatabase UserDatabase;

    public synchronized static UserDatabase getInstance(Context context){
        if(UserDatabase == null) {
            UserDatabase = Room
                    .databaseBuilder(context, UserDatabase.class, "MyUser").build();
        }
        return UserDatabase;
    }

    public abstract DaoUser UserDao();
}
