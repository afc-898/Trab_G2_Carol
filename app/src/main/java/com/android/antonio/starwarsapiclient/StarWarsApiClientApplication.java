package com.android.antonio.starwarsapiclient;

import android.app.Application;

import com.swapi.sw.StarWarsApi;



public class StarWarsApiClientApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        StarWarsApi.init();
    }
}
