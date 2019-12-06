package com.example.myapplication.ui.view_holders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.application.App;
import com.example.myapplication.wrappers.CheckableGenre;

public class GenreViewHolder extends RecyclerView.ViewHolder {

    private TextView genreTitle;

    public GenreViewHolder(LayoutInflater inflater, ViewGroup parent) {
        super(inflater.inflate(R.layout.item_genre, parent, false));

        genreTitle = itemView.findViewById(R.id.genre_title);
    }

    public void bind(final CheckableGenre genre, final GenreViewHolderListener listener) {
        if (genre.isChecked()) {
            itemView.setBackgroundResource(R.color.colorPrimary);
            genreTitle.setTextColor(App.getContext().getColor(R.color.white));
        } else {
            itemView.setBackgroundResource(R.color.white);
            genreTitle.setTextColor(App.getContext().getColor(R.color.colorPrimaryDark));
        }

        genreTitle.setText(genre.getItem().getDisplayName());
        itemView.setOnClickListener((View v) -> listener.onGenreClick(genre));
    }

    public interface GenreViewHolderListener {
        void onGenreClick(CheckableGenre genre);
    }
}
