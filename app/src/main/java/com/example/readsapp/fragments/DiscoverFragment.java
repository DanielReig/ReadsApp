package com.example.readsapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.readsapp.R;
import com.example.readsapp.adapters.AdapterDiscover;
import com.example.readsapp.interfaz.bookItem;
import com.example.readsapp.models.BookList;
import com.example.readsapp.services.GoogleBookService;

import java.util.ArrayList;

public class DiscoverFragment extends Fragment {
    private ArrayList<bookItem> listbook;
    private RecyclerView rvb;
    private Button btnFiccion, btnRomance, btnMistery;
    private BookList books;
    private RecyclerView.LayoutManager managerb ;
    private AdapterDiscover adapterb;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        listbook = new ArrayList<bookItem>();
        adapterb = new AdapterDiscover(getContext(), listbook, new AdapterDiscover.OnItemClickListener() {
            @Override
            public void onItemClicked(int position) {
                BookFragment fragment = new BookFragment(true, books.getItems().get(position));
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                transaction.replace(R.id.fragment_container,fragment);
                transaction.commit();
            }
        });
        return inflater.inflate(R.layout.fragment_discover, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = v.getId();
                String subject = "";
                if (id == R.id.btnFiction) subject = "Fiction";
                if (id == R.id.btnRomance) subject = "Romance";
                if (id == R.id.btnThriller) subject = "Thriller";
                OnClickSubject(subject);
            }
        };

        this.rvb = getView().findViewById(R.id.rvDiscover);

        btnFiccion = getView().findViewById(R.id.btnFiction);
        btnRomance = getView().findViewById(R.id.btnRomance);
        btnMistery = getView().findViewById(R.id.btnThriller);

        btnFiccion.setOnClickListener(onClickListener);
        btnRomance.setOnClickListener(onClickListener);
        btnMistery.setOnClickListener(onClickListener);

        if(getContext() != null){
            managerb = new GridLayoutManager(getContext(),3);
            this.rvb.setLayoutManager(managerb);
            this.rvb.setAdapter(adapterb);
        }
        super.onViewCreated(view, savedInstanceState);
    }

    public void OnClickSubject(String subject){
        listbook.clear();
        new Thread(new Runnable() {
            @Override
            public void run() {

                GoogleBookService googleBookService = new GoogleBookService();
                books = googleBookService.getBooksBySubject(subject);
                for(int i = 0; i < books.getItems().size(); i++){
                    bookItem book = new bookItem();
                    book.setImage(new ImageView(getContext()));
                    googleBookService.setBookThumbnail(books.getItems().get(i),book.getImage());
                    book.setText(books.getItems().get(i).getTitle());
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
}
