package com.example.myapplication.mvp.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.myapplication.wrappers.CheckableGenre;

import java.util.List;

public interface MovieListView extends MvpView {
    @StateStrategyType(AddToEndStrategy.class)
    void setLoader(boolean state);

    @StateStrategyType(AddToEndStrategy.class)
    void setContentVisibility(boolean state);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showErrorToast(String message);

    @StateStrategyType(AddToEndStrategy.class)
    void showContent(List<Object> content);

    @StateStrategyType(AddToEndStrategy.class)
    void setSelectedGenre(CheckableGenre genre);

    @StateStrategyType(AddToEndStrategy.class)
    void setTryAgain(boolean state);
}
