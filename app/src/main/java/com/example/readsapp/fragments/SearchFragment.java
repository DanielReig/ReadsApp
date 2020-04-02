package com.example.readsapp.fragments;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.readsapp.R;
import com.example.readsapp.adapters.AdapterListSearch;
import com.example.readsapp.interfaz.bookItem;
import com.example.readsapp.models.Book;
import com.example.readsapp.models.BookList;
import com.example.readsapp.services.GoogleBookService;

import java.util.ArrayList;

public class SearchFragment extends Fragment {
    private ArrayList<bookItem> listbook;
    private RecyclerView rvb;
    private BookList books;
    private EditText searchtext;
    private RecyclerView.LayoutManager managerb ;
    private AdapterListSearch adapterb;

    public SearchFragment(){}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listbook = generateData();
        adapterb = new AdapterListSearch(getContext(), listbook, new AdapterListSearch.OnItemClickListener() {
            @Override
            public void onItemClicked(int position) {
                BookFragment fragment = new BookFragment(true, books.getItems().get(position));
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                transaction.replace(R.id.fragment_container,fragment);
                transaction.commit();
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_search, container, false);
        this.rvb = v.findViewById(R.id.rv_search);
        ImageButton i = v.findViewById(R.id.ibSearch);
        i.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnClickSearch();
            }
        });
        this.searchtext = v.findViewById(R.id.search);
        if(getContext() != null){
            managerb = new LinearLayoutManager(getContext());
            this.rvb.setLayoutManager(managerb);
            this.rvb.setAdapter(adapterb);
        }
        return v;
    }

    public void OnClickSearch(){
        listbook.clear();
        new Thread(new Runnable() {
            @Override
            public void run() {
                GoogleBookService googleBookService = new GoogleBookService();
                books = googleBookService.searchBookByTitle(searchtext.getText().toString());
                for(int i = 0; i < books.getItems().size(); i++){
                    bookItem book = new bookItem();
                    book.setImage(new ImageView(getContext()));
                    googleBookService.setBookThumbnail(books.getItems().get(i),book.getImage());
                    book.setText(books.getItems().get(i).getVolumeInfo().getTitle());
                    listbook.add(book);
                }
                /*de esta manera se actualiza la interfaz*/
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapterb.notifyDataSetChanged();
                    }
                });
            }
        }).start();

    }
    private ArrayList<bookItem> generateData() {
        ArrayList<bookItem> result = new ArrayList<>();
        result.add(new bookItem(" Moby Dick",null));
        result.add(new bookItem(" El conde dracula",null));
        return result;
    }
}
