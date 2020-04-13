package com.example.readsapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.readsapp.R;
import com.example.readsapp.database.UserDatabase;
import com.example.readsapp.database.dbUser;

public class ProfileFragment extends Fragment {

    private UserDatabase userDatabase;
    private TextView name;
    private String nameUser;
    private int ageUser;
    private TextView age;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userDatabase = UserDatabase.getInstance(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        name = v.findViewById(R.id.tvNameUser);
        age = v.findViewById(R.id.tvage);
        Button b = v.findViewById(R.id.bcalendarProfile);
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CalendarFragment somefragment = new CalendarFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, somefragment);
                transaction.commit();
            }
        });

        Button s = v.findViewById(R.id.bsetting);
        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SettingsFragment settings = new SettingsFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, settings);
                transaction.commit();
            }
        });

        DataUser();
        return v;

    }

    private void DataUser(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                dbUser userold = userDatabase.UserDao().getUser();
                if(userold == null){
                    userold = new dbUser();
                }
                nameUser = userold.getName();
                ageUser = userold.getAge();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        name.setText(nameUser);
                        age.setText(String.valueOf(ageUser));
                    }
                });
            }
        }).start();
    }

}
