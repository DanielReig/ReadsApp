package com.example.readsapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.readsapp.R;
import com.example.readsapp.models.Book;

public class BookFragment extends Fragment {
    private boolean newBook = false;
    private Book book;
    private TextView author;
    private TextView edit;
    private TextView year;
    private TextView title;
    private TextView topic;
    private TextView summary;

    public  BookFragment(boolean n, Book b){
        newBook = n;
        book = b;
    }
/*
    public BookFragment BookFragmentNew(boolean n){
        BookFragment b = new BookFragment();
        newBook = n;
        return b;
    }
*/
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_book, container, false);
        if(newBook){
           Button b =  v.findViewById(R.id.bCalen);
           b.setText("Add");
        }
        author = v.findViewById(R.id.tvauthor);
        title = v.findViewById(R.id.tvnamebook);
        edit = v.findViewById(R.id.tvedition);
        year = v.findViewById(R.id.tvyear);
        topic = v.findViewById(R.id.tvtopic);
        summary = v.findViewById(R.id.tvsumnary);
        InfBook();

        return v;
    }

    private void InfBook(){
        if(book.getVolumeInfo().getAuthors().size() > 1){
            String s = "";
            for(int i =0; i < book.getVolumeInfo().getAuthors().size();i++){
                s+= ", " + book.getVolumeInfo().getAuthors().get(i);
            }
            author.setText(s);
        }else{
            author.setText(book.getVolumeInfo().getAuthors().get(0));
        }

        title.setText(book.getVolumeInfo().getTitle());
        year.setText(book.getVolumeInfo().getPublishedDate());
        edit.setText(book.getVolumeInfo().getPublisher());
    }
}
