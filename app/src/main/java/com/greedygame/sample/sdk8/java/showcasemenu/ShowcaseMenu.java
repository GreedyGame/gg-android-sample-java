package com.greedygame.sample.sdk8.java.showcasemenu;

import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.greedygame.android.core.campaign.CampaignStateListener;
import com.greedygame.sample.sdk8.java.BaseActivity;
import com.greedygame.sample.sdk8.java.R;
import com.greedygame.sample.sdk8.java.showcase.nongames.travel_app.TravelDashboard;
import com.greedygame.sample.sdk8.java.utils.notimportant.SizeReductionPageTransformer;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

public class ShowcaseMenu extends BaseActivity {

    LinearLayout buttonBar;
    ProgressBar loader;
    Button knowMoreButton,seeDemoButton;
    DotsIndicator dotsIndicator;
    ViewPager2 contentViewPager;

    private ShowcaseListener ggEventListener = new ShowcaseListener();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showcase_menu);
        bindViews();
        initViewPager();
        setClickListeners();
        //Registering the event receiver for this class to the BaseClass
        if(isGreedyGameAgentInitialised) {
            hideLoader();
        }
        else {
            showLoader();
            initAds();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mBaseCampaignStateListener.receiver = ggEventListener;
    }

    private void bindViews(){
        buttonBar = findViewById(R.id.buttonBar);
        loader = findViewById(R.id.loader);
        knowMoreButton = findViewById(R.id.knowMoreButton);
        seeDemoButton = findViewById(R.id.seeDemoButton);
        dotsIndicator = findViewById(R.id.dots_indicator);
        contentViewPager = findViewById(R.id.contentViewPager);

    }

    private void setClickListeners(){
        seeDemoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), TravelDashboard.class));
                finish();
            }
        });

        knowMoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webIntent = new Intent(Intent.ACTION_VIEW);
                webIntent.setData(Uri.parse("https://www.greedygame.com"));
                startActivity(webIntent);
            }
        });
    }

    private void showLoader(){
        buttonBar.setVisibility(View.GONE);
        loader.setVisibility(View.VISIBLE);
    }
    private void hideLoader(){
        buttonBar.setVisibility(View.VISIBLE);
        loader.setVisibility(View.GONE);
    }
    private void initViewPager(){

        contentViewPager.setAdapter( new ShowcaseViewPagerAdapter());
        contentViewPager.setPageTransformer(new SizeReductionPageTransformer());
        dotsIndicator.setViewPager2(contentViewPager);

    }

    class ShowcaseListener implements CampaignStateListener
    {
        @Override
        public void onUnavailable() {
            hideLoader();
        }

        @Override
        public void onAvailable(String p0) {
            hideLoader();

        }

        @Override
        public void  onError(String p0) {
            loader.setVisibility(View.GONE);
        }

    }
}
