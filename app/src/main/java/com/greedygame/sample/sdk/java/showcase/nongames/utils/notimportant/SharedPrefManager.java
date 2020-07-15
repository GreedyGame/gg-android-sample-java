package com.greedygame.sample.sdk.java.showcase.nongames.utils.notimportant;

import android.content.Context;
import android.content.SharedPreferences;

import com.greedygame.sample.BaseApplication;

public class SharedPrefManager {
    static private SharedPreferences sharePref = BaseApplication.appContext.getSharedPreferences("GG_DEMO_SHARED_PREFRENCE", Context.MODE_PRIVATE);
    static private SharedPreferences.Editor editor = sharePref.edit();
    static private final String VALUE_KEY = "COACHMARKS";

    static public boolean isShouldShowCoachMarks() {
        return sharePref.getBoolean(VALUE_KEY,true);
    }

    static public void setShouldShowCoachMarks(boolean shouldShowCoachMarks) {
        editor.putBoolean(VALUE_KEY,shouldShowCoachMarks);
        editor.commit();
    }
}
