package com.example.myapplication.mvp.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.myapplication.entities.Genre;

import java.util.List;

public interface MovieListView extends MvpView {

    void setLoader(boolean state);

    void setContentVisibility(boolean state);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showErrorToast(String message);

    void showContent(List<Object> content);

    void setSelectedGenre(Genre genre);
    void setTryAgain(boolean state);
}
