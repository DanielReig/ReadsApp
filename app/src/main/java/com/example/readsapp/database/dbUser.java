package com.example.readsapp.database;

import android.net.Uri;
import android.widget.ImageView;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Blob;

@Entity(tableName = "MyUser")
public class dbUser {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_ID")
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "age")
    private int age;

    @ColumnInfo(name = "search")
    private String search;

    @ColumnInfo(name = "image")
    private String image;

    public dbUser(){}

    public dbUser(String n, int a){
        this.name = n;
        this.age = a;
        this.search = "title";
        this.image = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
