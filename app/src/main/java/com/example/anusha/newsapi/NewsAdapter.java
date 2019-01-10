package com.example.anusha.newsapi;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

//import com.squareup.picasso.Picasso;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    Context contex;
    ArrayList<NewsModel> newsModelss;

    public NewsAdapter(MainActivity mainActivity, ArrayList<NewsModel> newsModels) {
        this.contex=mainActivity;
        this.newsModelss=newsModels;
    }

    /*public NewsAdapter(Context applicationContext, ArrayList<NewsModel> newsModels) {
        this.contex=applicationContext;
        this.newsModelss=newsModels;
    }*/

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(contex).inflate(R.layout.row,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
    // holder.tv.setText(position);
     //holder.imv.setImageResource(position);
        NewsModel nm=newsModelss.get(position);
        holder.tv.setText(nm.getTitle());
        //Picasso.with(contex).load(nm.getBookImage()).into(holder.imv);
        Glide.with(contex)
                .load(nm.getBookImage())
                //.placeholder(R.mipmap.ic_launcher_round)
                .into(holder.imv);
        holder.imv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(contex,NewsdetailsActivity.class);
                String[] one=new String[3];
                one[0]=newsModelss.get(position).getTitle();
                one[1]=newsModelss.get(position).getDescription();
                one[2]=newsModelss.get(position).getBookImage();
                i.putExtra("datas",one);
                contex.startActivity(i);
            }
        });


    }

    @Override
    public int getItemCount() {

        return (newsModelss == null) ? 0: newsModelss.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv;
        ImageView imv;
        public ViewHolder(View itemView) {
            super(itemView);
            tv=itemView.findViewById(R.id.textView);
            imv=itemView.findViewById(R.id.imageView);
        }
    }
}
