package com.example.myapplication.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.application.App;
import com.example.myapplication.entities.Header;
import com.example.myapplication.entities.Movie;
import com.example.myapplication.ui.view_holders.GenreViewHolder;
import com.example.myapplication.ui.view_holders.HeaderViewHolder;
import com.example.myapplication.ui.view_holders.MovieViewHolder;
import com.example.myapplication.wrappers.CheckableGenre;

import java.util.ArrayList;
import java.util.List;

public class MovieListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int MOVIE_TYPE = 0;
    private static final int GENRE_TYPE = 1;
    private static final int HEADER_TYPE = 2;
    private LayoutInflater inflater;
    private List<Object> mContent;
    private GenreViewHolder.GenreViewHolderListener genreViewHolderListener;
    private MovieViewHolder.MovieViewHolderListener movieViewHolderListener;

    public MovieListAdapter(List<Object> content) {
        mContent = content;
        inflater = (LayoutInflater) App.getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setGenreViewHolderListener(GenreViewHolder.GenreViewHolderListener listener) {
        this.genreViewHolderListener = listener;
    }

    public void setMovieViewHolderListener(MovieViewHolder.MovieViewHolderListener listener) {
        this.movieViewHolderListener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        switch (viewType) {
            case MOVIE_TYPE:
                return new MovieViewHolder(inflater, viewGroup);
            case GENRE_TYPE:
                return new GenreViewHolder(inflater, viewGroup);
            case HEADER_TYPE:
                return new HeaderViewHolder(inflater, viewGroup);
        }
        throw new Error("View holder type does not exist");
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Object item = mContent.get(position);
        switch (holder.getItemViewType()) {
            case MOVIE_TYPE:
                ((MovieViewHolder) holder).bind((Movie) item, movieViewHolderListener);
                break;
            case GENRE_TYPE:
                ((GenreViewHolder) holder).bind((CheckableGenre) item, genreViewHolderListener);
                break;
            case HEADER_TYPE:
                ((HeaderViewHolder) holder).bind((Header) item);
        }
    }

    @Override
    public int getItemViewType(int position) {
        Object item = mContent.get(position);

        if (item instanceof Movie) {
            return MOVIE_TYPE;
        }

        if (item instanceof CheckableGenre) {
            return GENRE_TYPE;
        }

        if (item instanceof Header) {
            return HEADER_TYPE;
        }

        return -1;
    }

    @Override
    public int getItemCount() {
        return mContent != null ? mContent.size() : 0;
    }

    public void setItems(List<Object> items) {
        this.mContent = new ArrayList<>();
        if (items != null) {
            this.mContent.addAll(items);
        }
        notifyDataSetChanged();
    }

    public void setSelectedGenre(CheckableGenre genre) {
        notifyDataSetChanged();
    }
}
