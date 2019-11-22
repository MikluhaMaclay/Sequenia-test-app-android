package com.example.myapplication.mvp.views;

import com.arellomobile.mvp.MvpView;
import com.example.myapplication.entities.Movie;

public interface MovieDetailsView extends MvpView {

    void setContent(Movie movie);
}
