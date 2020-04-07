package com.example.readsapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.readsapp.R;
import com.example.readsapp.interfaz.bookItem;

import java.util.ArrayList;

public class AdapterDiscover extends RecyclerView.Adapter<AdapterDiscover.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClicked(int position);
    }

    private Context context;
    private ArrayList<bookItem> data;
    private OnItemClickListener listener;


    public AdapterDiscover(Context context, ArrayList<bookItem> data, OnItemClickListener listener) {
        this.context = context;
        this.data = data;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_bookitem_discover, parent, false);
        ViewHolder holder = new ViewHolder(view, listener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDiscover.ViewHolder holder, int position) {
        holder.tv.setText(data.get(position).getText());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        View v;
        TextView tv;
        public ViewHolder(View view, final OnItemClickListener listener) {
            super(view);
            v = view;
            tv = view.findViewById(R.id.tvBooklist);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClicked(getAdapterPosition());
                }
            });
        }
    }
}
