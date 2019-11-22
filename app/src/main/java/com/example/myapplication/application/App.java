package com.example.myapplication.application;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

/**
 * Класс-Application
 */
public class App extends Application {

    @SuppressLint("StaticFieldLeak")
    private static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

}
