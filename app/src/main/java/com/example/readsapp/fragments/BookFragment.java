package com.example.readsapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.readsapp.R;

public class BookFragment extends Fragment {
    private boolean newBook = false;
    public  BookFragment(boolean n){
        newBook = n;
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
        return v;
    }
}
