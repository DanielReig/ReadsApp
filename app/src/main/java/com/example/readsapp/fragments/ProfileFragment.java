package com.example.readsapp.fragments;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.provider.FontsContract;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Size;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.readsapp.R;
import com.example.readsapp.database.UserDatabase;
import com.example.readsapp.database.dbUser;

import java.sql.Blob;

import javax.xml.transform.Result;

public class ProfileFragment extends Fragment {

    private UserDatabase userDatabase;
    private TextView name;
    private String nameUser;
    private int ageUser;
    private TextView age;
    private ImageView image;
    private String uri;

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
        image = v.findViewById(R.id.imageUser);
        Button bimag = v.findViewById(R.id.bimage);
        bimag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageProfile();
            }
        });
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
                uri = userold.getImage();
                //uri = Uri.parse(userold.getImage());
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //image.setImageURI(uri);
                        name.setText(nameUser);
                        age.setText(String.valueOf(ageUser));
                        if(uri != null || uri != ""){
                            ///??PROBLEMAS AL CARGAR LA IMAGEN
                           // Uri u = Uri.parse(uri);
                           // image.setImageURI(u);
                        }
                    }
                });
            }
        }).start();
    }

    private void ImageProfile(){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent,"seleccionar aplicacion"),10);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK){
            Uri selectimage = data.getData();
            String simg = selectimage.toString();
            image.setImageURI(selectimage);
            SaveImage(simg);
        }
    }

    private void SaveImage(String simage){
        new Thread(new Runnable() {
            @Override
            public void run() {
                dbUser userold = userDatabase.UserDao().getUser();
                if(userold == null){
                    userold = new dbUser();
                }
                dbUser usernew = new dbUser(userold.getName(),userold.getAge());
                usernew.setSearch(userold.getSearch());
                usernew.setImage(simage);
                userDatabase.UserDao().deleteUser(userold);
                userDatabase.UserDao().addUser(usernew);
            }
        }).start();
    }
}
