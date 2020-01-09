package com.greedygame.sample.sdk8.java;


import android.os.CountDownTimer;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.greedygame.android.agent.GreedyGameAgent;
import com.greedygame.android.core.campaign.CampaignStateListener;

import java.util.Arrays;

public class BaseActivity extends AppCompatActivity {
    static private CountDownTimer mRefreshTimer = null;
    protected BaseCampaignListener mBaseCampaignStateListener = new BaseCampaignListener();
    public static boolean isGreedyGameAgentInitialised = false;
    public static GreedyGameAgent mGreedyGameAgent = null;
    static final String TAG = "GG-SAMPLE";

    public void initAds(){
        mGreedyGameAgent = new GreedyGameAgent.Builder(this)
                .setGameId("66081223")
                //You can also use addUnitId(unitId:String)
                .addUnitList(Arrays.asList("float-4343",
                        "float-4346",
                        "float-4347",
                        "float-4348"))
                .enableAdmob(true)
                .withAgentListener(mBaseCampaignStateListener)
                .build();
        mGreedyGameAgent.init();

    }

    static private void startRefreshTimer(){
        if(mRefreshTimer == null) {
            mRefreshTimer = new  CountDownTimer(70000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {

                }

                @Override
                public void onFinish() {
                    Log.d(TAG, "Countdown timer complete. Refreshing and Reloading");
                    mGreedyGameAgent.startEventRefresh();
                    this.start();
                }

            }.start();
        }
    }

    public class BaseCampaignListener implements CampaignStateListener {

        public CampaignStateListener receiver = null;
        @Override
        public void onUnavailable() {
            startRefreshTimer();
            receiver.onUnavailable();
            isGreedyGameAgentInitialised = true;
        }

        @Override
        public void onAvailable(String p0) {
            receiver.onAvailable(p0);
            startRefreshTimer();
            isGreedyGameAgentInitialised = true;
        }

        public void onError(String p0) {
            receiver.onError(p0);
            startRefreshTimer();
            Toast.makeText(BaseApplication.appContext,p0, Toast.LENGTH_SHORT).show();;
            isGreedyGameAgentInitialised = true;
        }

    }
}
