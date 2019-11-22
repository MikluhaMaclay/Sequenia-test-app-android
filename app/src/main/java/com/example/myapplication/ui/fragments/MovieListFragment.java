package com.example.myapplication.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.myapplication.R;
import com.example.myapplication.application.App;
import com.example.myapplication.entities.Genre;
import com.example.myapplication.entities.Movie;
import com.example.myapplication.mvp.models.MovieListModelProd;
import com.example.myapplication.mvp.presenters.MovieListPresenter;
import com.example.myapplication.mvp.views.MovieListView;
import com.example.myapplication.ui.adapters.MovieListAdapter;
import com.example.myapplication.ui.view_holders.GenreViewHolder;
import com.example.myapplication.ui.view_holders.MovieViewHolder;
import com.google.android.material.snackbar.Snackbar;
import com.sequenia.app_bar_provider.AppBarProvider;
import com.sequenia.app_bar_provider.AppBarSettings;

import java.util.ArrayList;
import java.util.List;

public class MovieListFragment extends MvpAppCompatFragment implements MovieListView,
        GenreViewHolder.GenreViewHolderListener,
        MovieViewHolder.MovieViewHolderListener, AppBarSettings {

    @InjectPresenter
    MovieListPresenter presenter;
    MovieListAdapter adapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private LinearLayout loader;
    private LinearLayout tryAgain;
    private Button tryAgainButton;
    private AppBarProvider appBarProvider;


    @ProvidePresenter
    MovieListPresenter provideMovieListPresenter() {
        MovieListPresenter presenter = new MovieListPresenter();
        presenter.setMovieListModel(new MovieListModelProd());
        return presenter;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof AppBarProvider) {
            appBarProvider = (AppBarProvider) context;
        } else {
            throw new RuntimeException("Activity must implement AppBarProvider!");
        }
    }


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.movie_list, container, false);
        initAdapter(view);
        loader = view.findViewById(R.id.loader);
        tryAgain = view.findViewById(R.id.try_again);
        tryAgainButton = view.findViewById(R.id.try_again_button);
        tryAgainButton.setOnClickListener((View v) -> presenter.onTryAgainClick());
        appBarProvider.setAppBarSettings(this);
        ((TextView) appBarProvider.setCustomToolbarView(R.layout.movie_title_toolbar))
                .setText(App.getContext().getString(R.string.app_name));
        return view;
    }


    private void initAdapter(View view) {
        List<Object> list = new ArrayList<>();

        layoutManager = new LinearLayoutManager(App.getContext());
        adapter = new MovieListAdapter(list);
        adapter.setGenreViewHolderListener(this);
        adapter.setMovieViewHolderListener(this);
        recyclerView = view.findViewById(R.id.movies_recycler_view);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onGenreClick(Genre genre) {
        presenter.onGenreClick(genre);

    }

    @Override
    public void onMovieClick(View view, Movie movie) {
        presenter.onMovieClick(view, movie);

        Bundle bundle = new Bundle();
        bundle.putParcelable("movie", movie);

        Navigation.findNavController(view).navigate(R.id.detailsFragment, bundle);

    }

    @Override
    public void showErrorToast(String message) {
        Snackbar snackbar = Snackbar.make(getView(), message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    @Override
    public void setLoader(boolean state) {
        if (state) {
            loader.setVisibility(View.VISIBLE);
        } else {
            loader.setVisibility(View.GONE);
        }
    }

    @Override
    public void setTryAgain(boolean state) {
        if(state) {
            tryAgain.setVisibility(View.VISIBLE);
        } else {
            tryAgain.setVisibility(View.GONE);
        }
    }

    @Override
    public void setContentVisibility(boolean state) {
        if (state) {
            recyclerView.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.GONE);
        }
    }

    @Override
    public void showContent(List<Object> content) {
        adapter.setItems(content);
    }

    @Override
    public void setSelectedGenre(Genre genre) {
        adapter.setSelectedGenre(genre);
    }


}
