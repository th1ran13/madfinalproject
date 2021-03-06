package com.example.madproject1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {


    Context context;
    List<Movie> mData;
    MovieItemClickListner movieItemClickListner;


    public MovieAdapter(Context context, List<Movie> mData , MovieItemClickListner listner) {
        this.context = context;
        this.mData = mData;
        movieItemClickListner = listner;
    }

    @NonNull

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_movie , parent , false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull  /*MovieAdapter.*/ MyViewHolder holder, int position) {

        holder.TvTitle.setText(mData.get(position).getTitle());
        holder.imgMovie.setImageResource(mData.get(position).getThumbnail());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView TvTitle;
        private ImageView imgMovie;

        public MyViewHolder(@NonNull  View itemView) {
            super(itemView);

            TvTitle = itemView.findViewById(R.id.item_movie_title);
            imgMovie = itemView.findViewById(R.id.item_movie_img);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    movieItemClickListner.onMovieClick(mData.get(getAdapterPosition()) , imgMovie);
                }
            });

        }
    }
}
