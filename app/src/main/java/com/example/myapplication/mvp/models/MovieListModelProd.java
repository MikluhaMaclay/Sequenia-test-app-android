package com.example.myapplication.mvp.models;

import com.example.myapplication.entities.Movie;
import com.example.myapplication.entities.responses.MovieListResponse;
import com.example.myapplication.network.RetrofitManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieListModelProd implements MovieListModel {
    @Override
    public void getMovies(final MoviesCallback callback) {
        RetrofitManager.getMoviesApiService().getMovieList()
                .enqueue(new Callback<MovieListResponse>() {
                    @Override
                    public void onResponse(Call<MovieListResponse> call, Response<MovieListResponse> response) {
                        MovieListResponse data = response.body();
                        List<Movie> movies = data.getMovies();
                        callback.onResponse(movies);
                    }

                    @Override
                    public void onFailure(Call<MovieListResponse> call, Throwable t) {
                        callback.onFailure(t.toString());
                    }
                });
    }
}
