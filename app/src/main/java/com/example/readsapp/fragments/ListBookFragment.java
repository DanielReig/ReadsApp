package com.example.readsapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.readsapp.R;
import com.example.readsapp.adapters.AdapterListBook;
import com.example.readsapp.interfaz.bookItem;
import com.example.readsapp.models.Book;

import java.util.ArrayList;

public class ListBookFragment extends Fragment {

    private ArrayList<bookItem> listbook;
    private RecyclerView rvb;
    private Book book;
    private RecyclerView.LayoutManager managerb ;
    private AdapterListBook adapterb;

    public ListBookFragment(){}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listbook = generateData();
        adapterb = new AdapterListBook(getContext(), listbook, new AdapterListBook.OnItemClickListener() {
            @Override
            public void onItemClicked(int position) {
                BookFragment fragment = new BookFragment(false, book);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container,fragment);
                transaction.commit();
            }
        });

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_listsbook, container,false);
        this.rvb = v.findViewById(R.id.recyclerBook);
        if(getContext() != null){
            managerb = new LinearLayoutManager(getContext());
            this.rvb.setLayoutManager(managerb);
            this.rvb.setAdapter(adapterb);
        }
        return v;
    }

    private ArrayList<bookItem> generateData() {
        ArrayList<bookItem> result = new ArrayList<>();
        result.add(new bookItem(" Moby Dick", null));
        result.add(new bookItem(" El conde dracula",null));
        return result;
    }
}
