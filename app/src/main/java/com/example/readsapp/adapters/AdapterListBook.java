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

public class AdapterListBook extends RecyclerView.Adapter<AdapterListBook.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClicked(int position);
    }

    private Context context;
    private ArrayList<bookItem> data;
    private AdapterListBook.OnItemClickListener listener;

    public AdapterListBook(Context context,ArrayList<bookItem> data, AdapterListBook.OnItemClickListener listener) {
        this.context = context;
        this.data = data;
        this.listener = listener;
    }

    /**
     * Creates a ViewHolder for the RecyclerView to represent Items.
     */
    @NonNull
    @Override
    public AdapterListBook.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_bookitem, parent, false);
        AdapterListBook.ViewHolder holder = new AdapterListBook.ViewHolder(view, listener);
        return holder;
    }

    /**
     * Updates the information displayed on the ViewHolder according to the given position.
     */
    @Override
    public void onBindViewHolder(@NonNull final AdapterListBook.ViewHolder holder, int position) {

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
        public ViewHolder(View view, final AdapterListBook.OnItemClickListener listener) {
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
