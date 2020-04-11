package com.example.readsapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.readsapp.R;
import com.example.readsapp.interfaz.bookItem;

import java.util.ArrayList;
import java.util.List;
import com.example.readsapp.services.GoogleBookService;
public class AdapterListSearch extends RecyclerView.Adapter<AdapterListSearch.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClicked(int position);
    }

    private Context context;
    private ArrayList<bookItem> data;
    private OnItemClickListener listener;

    public AdapterListSearch(Context context,ArrayList<bookItem> data, AdapterListSearch.OnItemClickListener listener) {
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
                .inflate(R.layout.recycler_bookitem, parent, false);
        AdapterListSearch.ViewHolder holder = new AdapterListSearch.ViewHolder(view, listener);
        return holder;
    }

    /**
     * Updates the information displayed on the ViewHolder according to the given position.
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv.setText(data.get(position).getText());
        data.get(position).setImage(holder.im);
        GoogleBookService googleBookServicee = new GoogleBookService();

        if(data.get(position).getUrl() != null) {
            googleBookServicee.setBookThumbnailAdapter(data.get(position).getUrl(), holder.im);
        }
//        holder.tv.setCompoundDrawablesWithIntrinsicBounds(
//                context.getResources().getDrawable(data.get(position).getImage(),null),
//                null, null, null);
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
        ImageView im;
        public ViewHolder(View view, final AdapterListSearch.OnItemClickListener listener) {
            super(view);
            v = view;
            im = view.findViewById(R.id.ivbooklist);
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
