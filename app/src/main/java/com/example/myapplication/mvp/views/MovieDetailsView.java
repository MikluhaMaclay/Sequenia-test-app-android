package com.example.myapplication.mvp.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.myapplication.entities.Movie;

public interface MovieDetailsView extends MvpView {
    @StateStrategyType(AddToEndStrategy.class)
    void setContent(Movie movie);
}
