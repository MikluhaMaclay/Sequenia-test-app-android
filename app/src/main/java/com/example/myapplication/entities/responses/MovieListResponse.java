package com.example.myapplication.entities.responses;

import com.example.myapplication.entities.Movie;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieListResponse {
    @SerializedName("films")
    private List<Movie> movies;

    public List<Movie> getMovies() {
        return movies;
    }
}
