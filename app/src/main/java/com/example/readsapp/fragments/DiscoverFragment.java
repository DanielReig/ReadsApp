package com.example.readsapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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
    private Button btnFiccion, btnHistory, btnMistery, btnAdventure, btnClassics, btnFantasy, btnHorror, btnBiography;
    private BookList books;
    private RecyclerView.LayoutManager managerb;
    private AdapterDiscover adapterb;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        listbook = new ArrayList<bookItem>();
        adapterb = new AdapterDiscover(getContext(), listbook, new AdapterDiscover.OnItemClickListener() {
            @Override
            public void onItemClicked(int position) {
                BookFragment fragment = new BookFragment("", books.getItems().get(position));
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                transaction.replace(R.id.fragment_container, fragment);
                transaction.commit();
            }
        });
        return inflater.inflate(R.layout.fragment_discover, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        OnClickSubject("Fiction");
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = v.getId();
                String subject = "";
                if (id == R.id.btnFiction) subject = "Fiction";
                if (id == R.id.btnHistory) subject = "History";
                if (id == R.id.btnThriller) subject = "Mystery";
                if (id == R.id.btnAdventure) subject = "Adventure";
                if (id == R.id.btnClassic) subject = "Classic";
                if (id == R.id.btnFantasy) subject = "Fantasy";
                if (id == R.id.btnHorror) subject = "Horror";
                if (id == R.id.btnBio) subject = "Biography";
                OnClickSubject(subject);
            }
        };

        this.rvb = getView().findViewById(R.id.rvDiscover);

        btnFiccion = getView().findViewById(R.id.btnFiction);
        btnHistory = getView().findViewById(R.id.btnHistory);
        btnMistery = getView().findViewById(R.id.btnThriller);
        btnAdventure = getView().findViewById(R.id.btnAdventure);
        btnClassics = getView().findViewById(R.id.btnClassic);
        btnFantasy = getView().findViewById(R.id.btnFantasy);
        btnHorror = getView().findViewById(R.id.btnHorror);
        btnBiography = getView().findViewById(R.id.btnBio);

        btnFiccion.setOnClickListener(onClickListener);
        btnHistory.setOnClickListener(onClickListener);
        btnMistery.setOnClickListener(onClickListener);
        btnAdventure.setOnClickListener(onClickListener);
        btnClassics.setOnClickListener(onClickListener);
        btnFantasy.setOnClickListener(onClickListener);
        btnHorror.setOnClickListener(onClickListener);
        btnBiography.setOnClickListener(onClickListener);

        if (getContext() != null) {
            managerb = new GridLayoutManager(getContext(), 3);
            this.rvb.setLayoutManager(managerb);
            this.rvb.setAdapter(adapterb);
        }
        super.onViewCreated(view, savedInstanceState);
    }

    public void OnClickSubject(String subject) {
        listbook.clear();
        new Thread(new Runnable() {
            @Override
            public void run() {

                GoogleBookService googleBookService = new GoogleBookService();
                books = googleBookService.getBooksBySubject(subject);
                for (int i = 0; i < books.getItems().size(); i++) {
                    bookItem book = new bookItem();
                    if ((books.getItems().get(i).getVolumeInfo() != null) && (books.getItems().get(i).getVolumeInfo().getImageLinks() != null)) {
                        book.setUrl(books.getItems().get(i).getImageLinks().getThumbnail());
                    }
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
