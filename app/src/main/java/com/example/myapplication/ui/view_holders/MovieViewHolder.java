package com.example.myapplication.ui.view_holders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.entities.Movie;
import com.squareup.picasso.Picasso;

public class MovieViewHolder extends RecyclerView.ViewHolder {

    private TextView movieTitle;
    private ImageView movieImage;

    public MovieViewHolder(LayoutInflater inflater, ViewGroup parent) {
        super(inflater.inflate(R.layout.item_movie, parent, false));

        movieTitle = itemView.findViewById(R.id.movie_title);
        movieImage = itemView.findViewById(R.id.movie_image);
    }

    public void bind(final Movie movie, final MovieViewHolderListener listener) {
        if (listener == null) {
            return;
        }
        movieTitle.setText(movie.getLocalizedName());
        Picasso.get().load(movie.getImageUrl()).into(movieImage);

        itemView.setOnClickListener((View v) -> listener.onMovieClick(v, movie));

    }

    public interface MovieViewHolderListener {
        void onMovieClick(View view, Movie movie);
    }

}
