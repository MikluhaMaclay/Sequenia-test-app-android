package com.example.myapplication.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.myapplication.R;
import com.example.myapplication.entities.Movie;
import com.example.myapplication.mvp.presenters.MovieDetailsPresenter;
import com.example.myapplication.mvp.views.MovieDetailsView;
import com.sequenia.app_bar_provider.AppBarProvider;
import com.sequenia.app_bar_provider.AppBarSettings;
import com.squareup.picasso.Picasso;

public class MovieDetailsFragment extends MvpAppCompatFragment implements AppBarSettings,
        MovieDetailsView {

    @InjectPresenter
    MovieDetailsPresenter presenter;

    private TextView titleView;
    private TextView yearView;
    private TextView ratingView;
    private ImageView imageView;
    private TextView descriptionView;
    private AppBarProvider appBarProvider;

    @ProvidePresenter
    MovieDetailsPresenter provideDetailsPresenter() {
        Bundle bundle = getArguments();
        if (bundle == null) {
            throw new Error("Bundle must exist");
        }
        return new MovieDetailsPresenter(getArguments());
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
        return inflater.inflate(R.layout.movie_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        titleView = view.findViewById(R.id.movie_title);
        yearView = view.findViewById(R.id.movie_year);
        ratingView = view.findViewById(R.id.movie_rating);
        imageView = view.findViewById(R.id.movie_image);
        descriptionView = view.findViewById(R.id.movie_description);
        appBarProvider.setAppBarSettings(this);
    }

    @Override
    public boolean isBackButtonVisible() {
        return true;
    }

    @Override
    public void setContent(Movie movie) {
        titleView.setText(movie.getName());
        yearView.setText(movie.getYear());
        ratingView.setText(movie.getRating());
        descriptionView.setText(movie.getDescription());
        String imageUrl = movie.getImageUrl();
        if (!imageUrl.isEmpty()) {
            Picasso.get().load(movie.getImageUrl()).into(imageView);
        }
        ((TextView) appBarProvider.setCustomToolbarView(R.layout.movie_title_toolbar))
                .setText(movie.getLocalizedName());
    }
}
