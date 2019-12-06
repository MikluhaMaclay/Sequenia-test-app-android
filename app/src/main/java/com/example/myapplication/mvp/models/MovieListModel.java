package com.example.myapplication.mvp.models;

import com.example.myapplication.entities.Movie;

import java.util.List;

public interface MovieListModel {

    void getMovies(MoviesCallback callback);

    interface MoviesCallback {
        void onResponse(List<Movie> items);

        void onFailure(String error);
    }
}
