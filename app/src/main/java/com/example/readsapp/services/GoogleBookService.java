package com.example.readsapp.services;

import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;

import com.example.readsapp.models.Book;
import com.example.readsapp.models.BookList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class GoogleBookService {

    public Book searchBookById(String id) {
        String urlString = "https://www.googleapis.com/books/v1/volumes/" + id;
        Book book = new Book();

        try {
            URL url = new URL(urlString);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            Gson gson = new Gson();
            book= gson.fromJson(reader, Book.class);
            reader.close();

            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return book;
    }

    public BookList searchBookByTitle(String title){
        String urlString = "https://www.googleapis.com/books/v1/volumes?q=" + title;

        BookList bookList = new BookList();

        try {
            URL url = new URL(urlString);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            Gson gson = new Gson();

            bookList = gson.fromJson(reader,BookList.class);

            reader.close();
            connection.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return bookList;
    }

    public  BookList getBooksBySubject(String subject){
        String urlString = "https://www.googleapis.com/books/v1/volumes?q=subject:" + subject;

        BookList bookList = new BookList();

        try {
            URL url = new URL(urlString);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            Gson gson = new Gson();

            bookList = gson.fromJson(reader,BookList.class);

            reader.close();
            connection.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return bookList;
    }

    public void setBookThumbnail(Book book, ImageView imageView){
        Handler uiHandler  = new Handler(Looper.getMainLooper());
        uiHandler.post(new Runnable() {
            @Override
            public void run() {
                String thumbnail ="";
                if(book != null){
                    if(book.getVolumeInfo() != null){
                        if(book.getVolumeInfo().getImageLinks() != null){
                            if (book.getVolumeInfo().getImageLinks().getThumbnail() != null){
                                thumbnail = book.getVolumeInfo().getImageLinks().getThumbnail();
                                Picasso.get().load(thumbnail).into(imageView);
                            }
                        }
                    }
                }
            }
        });
    }

    public void setBookThumbnailAdapter(String book, ImageView imageView){
        Handler uiHandler  = new Handler(Looper.getMainLooper());
        uiHandler.post(new Runnable() {
            @Override
            public void run() {
                String thumbnail ="";
                if(book != null){
                    Picasso.get().load(book).into(imageView);

                }
            }
        });
    }
}
