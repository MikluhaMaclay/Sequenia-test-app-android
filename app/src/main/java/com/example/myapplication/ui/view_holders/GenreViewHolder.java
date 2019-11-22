package com.example.myapplication.ui.view_holders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.application.App;
import com.example.myapplication.entities.Genre;

public class GenreViewHolder extends RecyclerView.ViewHolder {

    private TextView genreTitle;

    public GenreViewHolder(LayoutInflater inflater, ViewGroup parent) {
        super(inflater.inflate(R.layout.item_genre, parent, false));

        genreTitle = itemView.findViewById(R.id.genre_title);
    }

    public void bind(final Genre genre, final GenreViewHolderListener listener, Genre selectedGenre) {
        if (listener == null) {
            return;
        }

        if (selectedGenre != null && selectedGenre.getName().equals(genre.getName())) {
            itemView.setBackgroundResource(R.color.colorPrimary);
            genreTitle.setTextColor(App.getContext().getColor(R.color.white));
        } else {
            itemView.setBackgroundResource(R.color.white);
            genreTitle.setTextColor(App.getContext().getColor(R.color.colorPrimaryDark));
        }

        genreTitle.setText(genre.getDisplayName());
        itemView.setOnClickListener((View v) -> listener.onGenreClick(genre));

    }

    public interface GenreViewHolderListener {
        void onGenreClick(Genre genre);
    }

}
