package com.example.readsapp.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.readsapp.R;
import com.example.readsapp.database.BookDatabase;
import com.example.readsapp.database.dbbook;
import com.example.readsapp.interfaz.Item;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class CalendarFragment extends Fragment {

    private CalendarView calendar;
    private BookDatabase database;
    private ArrayList<String> listBook;
    private ArrayAdapter<String> adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = BookDatabase.getInstance(getContext());
        listBook = getBooks();
        SimpleAdapter ad = new SimpleAdapter(getContext(),R.layout.support_simple_spinner_dropdown_item,);
        //adapter = new ArrayAdapter<String>(getContext(),R.layout.support_simple_spinner_dropdown_item,listBook);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_calender, container, false);
        calendar = v.findViewById(R.id.calendarView);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String day = String.valueOf(dayOfMonth) + "/" + String.valueOf(month) + "/" +
                        String.valueOf(year);
                selectedDay(day);
            }
        });
        return v;
    }

    private void selectedDay(String d){
        AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.dialog_calendar_date,null);
        TextView date = v.findViewById(R.id.tvDate);
        date.setText(d);
        Spinner books = v.findViewById(R.id.spinCal);
        //books.setAdapter(adapter);
        dialog.setView(v)
                .setPositiveButton(R.string.okDeleteDialog, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton(R.string.cancelDeleteDialog, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        dialog.create().show();
    }

    private ArrayList<String> getBooks() {
        ArrayList<String> result = new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<String>  list = database.BookDao().getAllBook();
                if(list.size() > 0){
                    for(int i = 0; i < list.size(); i++){
                        result.add(list.get(i));
                    }
                }
            }
        }).start();
        return result;
    }
}

