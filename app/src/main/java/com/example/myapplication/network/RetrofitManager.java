package com.example.myapplication.network;

import com.example.myapplication.BuildConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;


/**
 * Класс для работы с {@link Retrofit Retrofit`ом}
 */
public class RetrofitManager {

    private static final String SERVER_URL = BuildConfig.SERVER_URL;

    private static final int TIMEOUT_IN_MILLISECONDS = 60_000;
    private static MoviesApiService moviesApiService;

    private static Retrofit createRetrofit(String url) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        return new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(RetrofitManager.getOkHttpClient())
                .build();
    }

    public static MoviesApiService getMoviesApiService() {
        return moviesApiService == null ? moviesApiService =
                RetrofitManager.createRetrofit(SERVER_URL).create(MoviesApiService.class)
                : moviesApiService;
    }

    private static OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.addNetworkInterceptor(getApplicationJsonInterceptor());

        okHttpClient.connectTimeout(TIMEOUT_IN_MILLISECONDS, TimeUnit.MILLISECONDS);
        okHttpClient.readTimeout(TIMEOUT_IN_MILLISECONDS, TimeUnit.MILLISECONDS);
        okHttpClient.writeTimeout(TIMEOUT_IN_MILLISECONDS, TimeUnit.MILLISECONDS);

        return okHttpClient.build();
    }

    private static Interceptor getApplicationJsonInterceptor() {
        return new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request.Builder builder = original.newBuilder();
                builder.header("Content-Type", "application/json");

                builder.method(original.method(), original.body()).build();
                return chain.proceed(builder.build());
            }
        };
    }
}
