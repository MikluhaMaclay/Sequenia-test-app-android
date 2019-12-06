package com.example.myapplication.network;

import com.example.myapplication.entities.responses.MovieListResponse;

import retrofit2.Call;

import retrofit2.http.GET;

public interface MoviesApiService {
    @GET("films.json")
    Call<MovieListResponse> getMovieList();
}
