package com.example.readsapp.fragments;

import android.content.Context;
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
import com.example.readsapp.activities.MainActivity;
import com.example.readsapp.adapters.AdapterList;
import com.example.readsapp.interfaz.Item;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import com.example.readsapp.activities.MainActivity;

public class ListsFragment extends Fragment {
    private ArrayList<Item> list;
    private RecyclerView rv;
    private RecyclerView.LayoutManager manager ;
    private AdapterList adapter;

    public ListsFragment(){}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list = generateData();
        adapter = new AdapterList(getContext(), list, new AdapterList.OnItemClickListener() {
            @Override
            public void onItemClicked(int position) {
                if (position != 0){
                    ListBookFragment fragment = new ListBookFragment();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container,fragment);
                    transaction.commit();
                }
            }
        });

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_lists, container,false);
        this.rv = v.findViewById(R.id.recyclerlist);
        if(getContext() != null){
            manager = new LinearLayoutManager(getContext());
            this.rv.setLayoutManager(manager);
        }
        this.rv.setAdapter(adapter);
        return v;
    }

    private ArrayList<Item> generateData() {
        ArrayList<Item> result = new ArrayList<>();
        result.add(new Item("Add", android.R.drawable.ic_menu_add));
        result.add(new Item("Reading", android.R.drawable.ic_menu_sort_by_size));
        return result;
    }
}
