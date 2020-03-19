package com.example.readsapp.adapters;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.readsapp.R;
import com.example.readsapp.interfaz.Item;

import java.util.ArrayList;

public class AdapterList extends RecyclerView.Adapter<AdapterList.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClicked(int position);
    }

    private Context context;
    private ArrayList<Item> data;
    private OnItemClickListener listener;

    public AdapterList(Context context,ArrayList<Item> data, AdapterList.OnItemClickListener listener) {
        this.context = context;
        this.data = data;
        this.listener = listener;
    }

    /**
     * Creates a ViewHolder for the RecyclerView to represent Items.
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false);
        AdapterList.ViewHolder holder = new ViewHolder(view, listener);
        return holder;
    }

    /**
     * Updates the information displayed on the ViewHolder according to the given position.
     */
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        // Update the subViews within the holder with information from the data source
        holder.tv.setText(data.get(position).getText());
        holder.tv.setCompoundDrawablesWithIntrinsicBounds(
                context.getResources().getDrawable(data.get(position).getImage(),null),
                null, null, null);
    }

    /**
     * Returns the number of elements in the data source.
     */
    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        View v;
        // Hold a reference to a TextView to later change its text
        TextView tv;
        public ViewHolder(View view, final AdapterList.OnItemClickListener listener) {
            super(view);
            v = view;
            tv = view.findViewById(R.id.tvItem);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClicked(getAdapterPosition());
                }
            });
        }
    }
}
