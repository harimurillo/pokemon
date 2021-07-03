package com.example.pokemon;

import android.app.Application;

import com.example.pokemon.repository.RestClient;


public class App extends Application {

    private static App mInstance;

    private static RestClient restClient;
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;


        restClient = new RestClient();
    }

    public static RestClient getRestClient() {
        return restClient;
    }


    public static synchronized App getCtx() {
        return mInstance;
    }
}
