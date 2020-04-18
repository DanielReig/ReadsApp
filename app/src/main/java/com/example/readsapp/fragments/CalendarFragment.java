package com.example.readsapp.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.readsapp.R;
import com.example.readsapp.database.BookDatabase;
import com.example.readsapp.database.dbbook;
import com.example.readsapp.models.Book;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class CalendarFragment extends Fragment {

    private CalendarView calendar;
    private BookDatabase database;
    private List<String> listTitle;
    private List<String> listbook;
    private ArrayAdapter<String> adapter;
    private int select;
    private AdapterView.OnItemClickListener listener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = BookDatabase.getInstance(getContext());
        listTitle = new ArrayList<String>();
        listbook = new ArrayList<String>();
        getBooks();
        adapter = new ArrayAdapter<String>(getContext(),R.layout.support_simple_spinner_dropdown_item,listTitle);
        
        listener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                select = position;
            }

        };
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
        //listener spinner
        //books.setOnItemClickListener(listener);
        books.setAdapter(adapter);
        dialog.setView(v)
                .setPositiveButton(R.string.okDeleteDialog, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                List<dbbook> dbbooks = database.BookDao().getdbook(listbook.get(select));
                                for(int i = 0; i < dbbooks.size(); i++){
                                    dbbook db  = dbbooks.get(i);
                                    db.setDate(d);
                                    database.BookDao().updateBook(db);
                                }
                            }
                        }).start();
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

    private void getBooks() {
        listbook.clear();
        listTitle.clear();
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<String>  list = database.BookDao().getAllBook();
                Gson gson = new Gson();
                if(list.size() > 0){
                    for(int i = 0; i < list.size(); i++){
                        Book book = gson.fromJson(list.get(i),Book.class);
                        if(list.get(i) != null){
                            listbook.add(list.get(i));
                            listTitle.add(book.getTitle());
                        }
                    }
                }
            }
        }).start();
    }
}

