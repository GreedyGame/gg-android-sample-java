package com.greedygame.sample;

import android.content.Context;

import androidx.multidex.MultiDexApplication;

public class BaseApplication extends MultiDexApplication {
    public static Context appContext = null;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
    }

}
