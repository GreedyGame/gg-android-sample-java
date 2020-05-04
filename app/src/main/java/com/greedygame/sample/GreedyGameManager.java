package com.greedygame.sample;

import android.content.Context;

import com.greedygame.core.AppConfig;
import com.greedygame.core.GreedyGameAds;
import com.greedygame.core.interfaces.GreedyGameAdsEventsListener;

public class GreedyGameManager {
    public static boolean isSdkInitialized() {
        return GreedyGameAds.isSdkInitialized();
    }

    public static void init(Context context, GreedyGameAdsEventsListener listener) {
        if (isSdkInitialized())
            return;
        AppConfig appConfig = new AppConfig.Builder(context)
                .withAppId("1234")
                .build();
        GreedyGameAds.initWith(appConfig, listener);
    }
}