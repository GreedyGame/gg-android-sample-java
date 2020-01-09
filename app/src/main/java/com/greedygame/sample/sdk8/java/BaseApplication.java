package com.greedygame.sample.sdk8.java;

import android.app.Application;
import android.content.Context;

public class BaseApplication extends Application {
    public static Context appContext = null;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
    }
}
