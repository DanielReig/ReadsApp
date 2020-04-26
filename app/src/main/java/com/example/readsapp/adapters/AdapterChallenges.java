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
import com.example.readsapp.models.ObjetoPrueba;

import java.util.ArrayList;

public class AdapterChallenges extends RecyclerView.Adapter<AdapterChallenges.ViewHolder> {

    LayoutInflater inflater;
    //ArrayList<Challenge> mList;
    ArrayList<ObjetoPrueba> mList;

    public AdapterChallenges(Context context, ArrayList<ObjetoPrueba> mList) {
        this.inflater = LayoutInflater.from(context);
        this.mList = mList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        System.out.println("----!!!!------------------------>Starting adapter's onCreateViewHolder");
        View view = inflater.inflate(R.layout.challenge_item, parent, false);

        System.out.println("----!!!!------------------------>Finishing adapter's onCreateViewHolder");
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        System.out.println("----!!!!------------------------>Starting adapter's onBindViewHolder");
        //holder.title.setText(mList.get(position).getBook().getTitle());
        //holder.author.setText(mList.get(position).getBook().getAuthors().toString());
        //String pctg = mList.get(position).getPercentageRead() + "%";
        //holder.percentage.setText(pctg);

        /*
        GoogleBookService service = new GoogleBookService();
        if(mList.get(position).getBook().getThumbnail() != null) {
            service.setBookThumbnailAdapter(
                    mList.get(position).getBook().getThumbnail(),
                    holder.cover);
        }*/
        holder.nombre.setText(mList.get(position).getNombre());
        holder.fecha.setText(mList.get(position).getFecha());
        holder.img.setImageResource(mList.get(position).getImgID());
        System.out.println("----!!!!------------------------>Finishing adapter's onBindViewHolder");
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        //public TextView title, author, percentage;
        //public ImageView cover;
        public TextView nombre, fecha;
        public ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //title = itemView.findViewById(R.id.current_challenge_booktitle);
            //author = itemView.findViewById(R.id.current_challenge_bookauthor);
            //percentage = itemView.findViewById(R.id.current_challenge_percentage);
            //cover = itemView.findViewById(R.id.current_challenge_bookcover);
            nombre = itemView.findViewById(R.id.current_challenge_booktitle);
            fecha = itemView.findViewById(R.id.current_challenge_bookauthor);
            img = itemView.findViewById(R.id.current_challenge_bookcover);
        }
    }
}
