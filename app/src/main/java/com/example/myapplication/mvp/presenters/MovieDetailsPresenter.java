package com.example.myapplication.mvp.presenters;

import android.os.Bundle;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.myapplication.entities.Movie;
import com.example.myapplication.mvp.views.MovieDetailsView;

@InjectViewState
public class MovieDetailsPresenter extends MvpPresenter<MovieDetailsView> {
    private Movie movie;

    public MovieDetailsPresenter(Bundle bundle) {
        movie = bundle.getParcelable("movie");
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        setContent(movie);
    }

    private void setContent(Movie movie) {
        getViewState().setContent(movie);
    }
}
