package com.greedygame.sample.sdk.java.showcase.nongames.showcasemenu;

import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.greedygame.core.GreedyGameAds;
import com.greedygame.core.interfaces.GreedyGameAdsEventsListener;
import com.greedygame.core.models.general.InitErrors;
import com.greedygame.sample.BaseActivity;
import com.greedygame.sample.BaseApplication;
import com.greedygame.sample.GreedyGameManager;
import com.greedygame.sample.sdk8.java.R;
import com.greedygame.sample.sdk.java.showcase.nongames.travel_app.TravelDashboard;
import com.greedygame.sample.sdk.java.showcase.nongames.utils.notimportant.SizeReductionPageTransformer;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import org.jetbrains.annotations.NotNull;

public class ShowcaseMenu extends BaseActivity implements GreedyGameAdsEventsListener {

    LinearLayout buttonBar;
    ProgressBar loader;
    Button knowMoreButton,seeDemoButton;
    DotsIndicator dotsIndicator;
    ViewPager2 contentViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showcase_menu);
        bindViews();
        initViewPager();
        setClickListeners();
        //Registering the event receiver for this class to the BaseClass
        if(GreedyGameAds.isSdkInitialized()) {
            hideLoader();
        }
        else {
            showLoader();
            GreedyGameManager.init(BaseApplication.appContext,this);
        }
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

    @Override
    public void onInitFailed(@NotNull InitErrors initErrors) {
        hideLoader();
        Toast.makeText(this,"SDK Failed",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onInitSuccess() {
        hideLoader();
        Toast.makeText(this,"Init Successful",Toast.LENGTH_SHORT).show();
    }
}
