package com.example.readsapp.services;

import android.net.Uri;
import android.util.Log;

import com.example.readsapp.models.Book;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class OpenLibraryService {
    public Book searchBook(String title){
        return null;
    }



    public List<Book> getBooksBySubject(String subject){

        List<Book> books = new ArrayList<>();

        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https");
        builder.authority("openlibrary.org");
        builder.appendPath("subjects");
        builder.appendPath(subject+ ".json");
        try {
            URL url = new URL(builder.build().toString());
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            int cod = connection.getResponseCode();
            if(cod == HttpURLConnection.HTTP_OK){
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                Gson gson = new Gson();
                //DESERIALIZAR LISTA
                //books =  gson.fromJson(reader,Book.class);
                reader.close();
            }

            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return books;
    }


    public  Book getBookByISBN(String ISBN){
        Book book = new Book();

        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https");
        builder.authority("openlibrary.org");
        builder.appendPath("api");
        builder.appendPath("books");
        builder.appendQueryParameter("bibkeys",ISBN);
        builder.appendQueryParameter("jscmd","data");
        try {
            URL url = new URL(builder.build().toString());
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            int cod = connection.getResponseCode();
            if(cod == HttpURLConnection.HTTP_OK){
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                Gson gson = new Gson();
                book = gson.fromJson(reader,Book.class);
                reader.close();
            }

            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return book;
    }
}
