package com.example.readsapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.readsapp.R;
import com.example.readsapp.interfaz.ChallengeItem;
import com.example.readsapp.services.GoogleBookService;

import java.util.ArrayList;

public class AdapterChallenges extends RecyclerView.Adapter<AdapterChallenges.ViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<ChallengeItem> mList;

    public AdapterChallenges(Context context, ArrayList<ChallengeItem> mList) {
        this.inflater = LayoutInflater.from(context);
        this.mList = mList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.challenge_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(mList.get(position).getTitle());
        String pctg = mList.get(position).getPercentage() + "%";
        holder.percentage.setText(pctg);

        GoogleBookService service = new GoogleBookService();
        if(mList.get(position).getUrl() != null) {
            service.setBookThumbnailAdapter(mList.get(position).getUrl(), holder.cover);
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title, percentage;
        public ImageView cover;
        public Button button;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.challenge_booktitle);
            percentage = itemView.findViewById(R.id.challenge_percentage);
            cover = itemView.findViewById(R.id.challenge_bookcover);
            button = itemView.findViewById(R.id.challenge_increase);
        }
    }
}
