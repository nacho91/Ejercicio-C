package com.nacho91.ejercicioc;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nacho91.ejercicioc.api.EjercicioCApi;
import com.nacho91.ejercicioc.api.RxApiCallAdapterFactory;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ignacio on 1/5/2017.
 */

public class EjercicioCApplication extends Application {

    private EjercicioCApi api;

    @Override
    public void onCreate() {
        super.onCreate();

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        if(BuildConfig.DEBUG)
            httpClient.interceptors().add(logging);

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .client(httpClient.build())
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxApiCallAdapterFactory.create())
                .build();

        api = retrofit.create(EjercicioCApi.class);
    }

    public EjercicioCApi getApi(){
        return api;
    }
}
