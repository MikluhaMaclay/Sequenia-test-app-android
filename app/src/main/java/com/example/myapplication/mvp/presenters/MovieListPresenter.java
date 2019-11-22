package com.example.myapplication.mvp.presenters;

import android.view.View;

import com.arellomobile.mvp.InjectViewState;
import com.example.myapplication.R;
import com.example.myapplication.application.App;
import com.example.myapplication.entities.Genre;
import com.example.myapplication.entities.Header;
import com.example.myapplication.entities.Movie;
import com.example.myapplication.mvp.models.MovieListModel;

import com.arellomobile.mvp.MvpPresenter;
import com.example.myapplication.mvp.views.MovieListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@InjectViewState
public class MovieListPresenter extends MvpPresenter<MovieListView> {

    private MovieListModel movieListModel;
    public List<Genre> genres;
    public List<Movie> movies;
    public Genre selectedGenre;


    public void setMovieListModel(MovieListModel movieListModel) {
        this.movieListModel = movieListModel;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        loadMovies();
    }

    private void loadMovies() {

        getViewState().setLoader(true);
        getViewState().setContentVisibility(false);
        getViewState().setTryAgain(false);
        movieListModel.getMovies(
                new MovieListModel.MoviesCallback() {
                    @Override
                    public void onResponse(List<Movie> movies) {
                        onMoviesLoadSuccess(movies);
                    }

                    @Override
                    public void onFailure(String error) {
                        onLoadApplicationsError(error);
                    }
                }
        );
    }

    ;

    private void onMoviesLoadSuccess(List<Movie> movies) {
        getViewState().setLoader(false);
        getViewState().setContentVisibility(true);

        List<Genre> genreList = new ArrayList<>();
        for (Movie movie : movies
        ) {
            List<String> genres = movie.getGenres();
            for (String genre : genres
            ) {
                Genre genre2 = new Genre(genre);
                if (!genreList.contains(genre2)) {
                    genreList.add(genre2);
                }
            }
        }

        this.genres = genreList;
        this.movies = movies;

        prepareContent(genres, movies);
    }

    private void prepareContent(List<Genre> genres, List<Movie> movies) {
        List<Object> content = new ArrayList<>();
        content.add(new Header(App.getContext().getString(R.string.genres)));
        content.addAll(genres);
        content.add(new Header(App.getContext().getString(R.string.films)));
        List<Movie> sortedMovies = movies;
        if(sortedMovies.size() > 0) {
            Collections.sort(sortedMovies, new Comparator<Movie>() {
                @Override
                public int compare(Movie movie, Movie t1) {
                    return movie.getLocalizedName().compareToIgnoreCase(t1.getLocalizedName());
                }
            });
        }
        content.addAll(sortedMovies);

        getViewState().showContent(content);
    }

    public void onGenreClick(Genre genre) {
        String genreName = genre.getName();


        if (selectedGenre != null && selectedGenre == genre) {
            selectedGenre = null;
            getViewState().setSelectedGenre(selectedGenre);

            prepareContent(genres, movies);
            return;
        }

        List<Movie> filteredMovies = new ArrayList<>();
        for (Movie movie : movies
        ) {
            List<String> movieGenres = movie.getGenres();

            if (movieGenres.contains(genreName)) {
                filteredMovies.add(movie);
            }
        }
        selectedGenre = genre;
        getViewState().setSelectedGenre(selectedGenre);
        prepareContent(genres, filteredMovies);
    }

    public void onMovieClick(View view, Movie movie) {

    }

    public void onTryAgainClick() {
        loadMovies();
    }


    private void onLoadApplicationsError(String error) {
        getViewState().setLoader(false);
        getViewState().setTryAgain(true);
        getViewState().showErrorToast(App.getContext().getString(R.string.error_load));
    }
}
