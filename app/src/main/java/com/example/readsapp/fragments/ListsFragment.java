package com.example.readsapp.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import com.example.readsapp.activities.MainActivity;

public class ListsFragment extends Fragment {
    private ArrayList<Item> list;
    private RecyclerView rv;
    private RecyclerView.LayoutManager manager ;
    private AdapterList adapter;
    private FloatingActionButton add;

    public ListsFragment(){}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list = generateData();
        adapter = new AdapterList(getContext(), list, new AdapterList.OnItemClickListener() {
            @Override
            public void onItemClicked(int position) {
                ListBookFragment fragment = new ListBookFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container,fragment);
                transaction.commit();
            }});
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_lists, container,false);
        this.rv = v.findViewById(R.id.recyclerlist);
        this.add = v.findViewById(R.id.addList);
        if(getContext() != null){
            manager = new LinearLayoutManager(getContext());
            this.rv.setLayoutManager(manager);
        }
        this.rv.setAdapter(adapter);
        this.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                View view = getLayoutInflater().inflate(R.layout.add_list, null);
                //cojo la referencia del text para guardarme su valor cuando el usuario cree la nueva lista
                EditText t = view.findViewById(R.id.et_CreateList);
                Button create = view.findViewById(R.id.bCreateList);
                create.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!t.getText().toString().isEmpty()){
                            list.add(new Item(t.getText().toString(),android.R.drawable.ic_menu_sort_by_size));
                            adapter.notifyDataSetChanged();
                            //???-> falta quitar el dialog
                            Toast.makeText(getContext(), R.string.create_ok_msg,Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getContext(), R.string.create_entry_msg, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                dialog.setView(view).create().show();
            }
        });
        return v;
    }

    private ArrayList<Item> generateData() {
        ArrayList<Item> result = new ArrayList<>();
        result.add(new Item("Reading", android.R.drawable.ic_menu_sort_by_size));
        return result;
    }
}
