package com.example.readsapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.readsapp.database.dbUser;
import com.example.readsapp.R;
import com.example.readsapp.database.UserDatabase;

public class SettingsFragment extends Fragment {

    private UserDatabase userDatabase;
    private RadioGroup method;
    private RadioButton title;
    private RadioButton ISBN;
    private String methodSarch = "title";
    private Button saveData;
    private EditText nameuser;
    private EditText ageuser;
    private String name;
    private String age;
    private boolean nouser;

    public SettingsFragment(){}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userDatabase = UserDatabase.getInstance(getContext());
        SelectedButton();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.setting, container, false);
        method = v.findViewById(R.id.rgmethod);
        title= v.findViewById(R.id.rbtitle);
        ISBN = v.findViewById(R.id.rbisbn);
        nameuser = v.findViewById(R.id.editNameUser);
        ageuser = v.findViewById(R.id.editAgeUser);
        saveData = v.findViewById(R.id.bSaveDataUser);
        nameuser.setText(name);
        ageuser.setText(age);
        if( methodSarch == null || methodSarch.compareTo("title") == 0){
            method.check(R.id.rbtitle);
        }else{
            method.check(R.id.rbisbn);
        }

        method.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(title.isChecked()){
                    methodSarch = "title";
                }else{
                    methodSarch = "ISBN";
                }
                changeMethod();
            }
        });
        saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveDataUser();
            }
        });

        return v;
    }

    private void SaveDataUser(){
        String name = nameuser.getText().toString();
        String age = ageuser.getText().toString();
        if(!name.equals("") && !age.equals("")){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    dbUser userold = userDatabase.UserDao().getUser();
                    if(userold == null){
                        userold = new dbUser(name, Integer.valueOf(age));
                    }
                    dbUser usernew = new dbUser(name, Integer.valueOf(age));
                    usernew.setSearch(methodSarch);
                    usernew.setImage(userold.getImage());
                    userDatabase.UserDao().deleteUser(userold);
                    userDatabase.UserDao().addUser(usernew);
                }
            }).start();
            ProfileFragment settings = new ProfileFragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, settings);
            transaction.commit();
        }else{
            Toast.makeText(getContext(), R.string.No_user, Toast.LENGTH_LONG).show();
        }


    }

    private void changeMethod(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                dbUser userold = userDatabase.UserDao().getUser();
                if(userold == null){
                    userold = new dbUser();
                }
                dbUser usernew = new dbUser(userold.getName(),userold.getAge());
                usernew.setSearch(methodSarch);
                usernew.setImage(userold.getImage());
                userDatabase.UserDao().deleteUser(userold);
                userDatabase.UserDao().addUser(usernew);
            }
        }).start();
    }

    private void SelectedButton() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                dbUser userold = userDatabase.UserDao().getUser();
                if (userold == null) {
                    methodSarch = "title";
                    name = "";
                    age = "";
                } else {
                    if (userold.getName() == null) {
                        methodSarch = userold.getSearch();
                        name = "";
                        age = "";
                    } else {
                        methodSarch = userold.getSearch();
                        name = userold.getName();
                        age = String.valueOf(userold.getAge());
                    }
                }
            }
        }).start();
    }
}
