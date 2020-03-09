package com.example.readsapp.services;

import android.net.Uri;

import com.example.readsapp.models.Book;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class OpenLibraryService {
    public Book searchBook(String title){
        return null;
    }

    public List<Book> getBooksBySubject(String subject){
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
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
