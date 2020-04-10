package com.example.readsapp.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.readsapp.R;
import com.example.readsapp.adapters.AdapterList;
import com.example.readsapp.database.BookDatabase;
import com.example.readsapp.interfaz.Item;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ListsFragment extends Fragment {
    private ArrayList<Item> list;
    private RecyclerView rv;
    private RecyclerView.LayoutManager manager ;
    private AdapterList adapter;
    private FloatingActionButton add;
    private BookDatabase database;

    public ListsFragment(){}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = BookDatabase.getInstance(getContext());
        list = generateData();
        adapter = new AdapterList(getContext(), list, new AdapterList.OnItemClickListener() {
            @Override
            public void onItemClicked(int position) {
                ListBookFragment fragment = new ListBookFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container,fragment);
                transaction.commit();
            }

            @Override
            public void onItemLongClickListener(int position) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                dialog.setMessage(R.string.deleteOneDialogTitle).setPositiveButton(R.string.okDeleteDialog, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                database.BookDao().deleteList(list.get(position).getText());
                                adapter.removeItem(position);
                                adapter.notifyDataSetChanged();
                            }
                        }).run();
                    }
                }).setNegativeButton(R.string.cancelDeleteDialog, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                dialog.create().show();
            }
        });

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
                View view = getLayoutInflater().inflate(R.layout.add_list, null);
                //cojo la referencia del text para guardarme su valor cuando el usuario cree la nueva lista
                EditText t = view.findViewById(R.id.et_CreateList);
                AddItemList(t, view);
            }
        });

        return v;
    }

    private void AddItemList(EditText text, View view){
        AlertDialog.Builder dialog = new AlertDialog.Builder(getContext())
                .setPositiveButton(R.string.name_create, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (!text.getText().toString().isEmpty()) {
                            list.add(new Item(text.getText().toString(), android.R.drawable.ic_menu_sort_by_size));
                            database.BookDao().addlist(text.getText().toString());
                            adapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(getContext(), R.string.create_entry_msg, Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton(R.string.cancelDeleteDialog, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        dialog.setView(view).create().show();
    }

    private ArrayList<Item> generateData() {
        ArrayList<Item> result = new ArrayList<>();
        ArrayList<String> s = database.BookDao().getlist();
        for(int i = 0; i < s.size(); i++){
            result.add(new Item(s.get(i), android.R.drawable.ic_menu_sort_by_size));
        }
        return result;
    }
}
