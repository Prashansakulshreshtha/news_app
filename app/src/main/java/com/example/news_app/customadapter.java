package com.example.news_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.news_app.models.newsheadlines;
import com.squareup.picasso.Picasso;

import java.util.List;

public class customadapter extends RecyclerView.Adapter<customviewholder> {

    private Context context;
    private List<newsheadlines> headlines;
    private selectlistener listener;

    public customadapter(Context context, List<newsheadlines> headlines, selectlistener listener) {
        this.context = context;
        this.headlines = headlines;
        this.listener = listener;
    }

    @NonNull
    @Override
    public customviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new customviewholder(LayoutInflater.from(context).inflate(R.layout.headline_list_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull customviewholder holder, int position) {
        holder.text_title.setText(headlines.get(position).getTitle());
        holder.text_source.setText(headlines.get(position).getSource().getName());
        if(headlines.get(position).getUrlToImage()!=null){
            Picasso.get().load(headlines.get(position).getUrlToImage()).into(holder.img_headline);
        }
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onNewsClicked(headlines.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return headlines.size();
    }
}
