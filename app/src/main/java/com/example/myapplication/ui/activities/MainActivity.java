package com.example.myapplication.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import com.example.myapplication.R;
import com.example.myapplication.ui.fragments.MovieListFragment;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.sequenia.app_bar_provider.AppBarProvider;
import com.sequenia.app_bar_provider.AppBarProviderImp;
import com.sequenia.app_bar_provider.AppBarSettings;
import com.sequenia.app_bar_provider.AppBarViews;

public class MainActivity extends AppCompatActivity implements AppBarProvider, AppBarViews {

    private AppBarProviderImp appBarProviderImp;
    private AppBarLayout appBar;
    private androidx.appcompat.widget.Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private ViewGroup collapsingContent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initToolbar();
    }


    @Override
    public AppBarProviderImp getAppBarProviderImp() {
        return appBarProviderImp;
    }

    @Override
    public AppBarLayout getAppBar() {
        return appBar;
    }

    @Override
    public androidx.appcompat.widget.Toolbar getToolbar() {
        return toolbar;
    }

    @Override
    public ViewGroup getCollapsingContent() {
        return collapsingContent;
    }

    @Override
    public CollapsingToolbarLayout getCollapsingToolbarLayout() {
        return collapsingToolbarLayout;
    }

    private void initViews() {
        View root = getWindow().getDecorView();
        appBar = root.findViewById(R.id.app_bar);
        toolbar = root.findViewById(R.id.toolbar);
        collapsingContent = root.findViewById(R.id.collapsing_content);
        collapsingToolbarLayout = root.findViewById(R.id.collapsing_toolbar);
    }

    private void initToolbar() {
        appBarProviderImp = new AppBarProviderImp(this);
        setSupportActionBar(appBarProviderImp.getToolbar());
//        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);

        toolbar.setNavigationOnClickListener(view -> onBackPressed());
    }

}
