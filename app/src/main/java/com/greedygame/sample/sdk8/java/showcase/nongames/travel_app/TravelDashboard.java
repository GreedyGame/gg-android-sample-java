package com.greedygame.sample.sdk8.java.showcase.nongames.travel_app;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.ColorUtils;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.Slide;
import androidx.viewpager2.widget.ViewPager2;

import com.greedygame.android.core.campaign.CampaignStateListener;
import com.greedygame.sample.sdk8.java.BaseActivity;
import com.greedygame.sample.sdk8.java.R;
import com.greedygame.sample.sdk8.java.showcase.nongames.travel_app.adapters.recyclerview.NewPlacesAdapter;
import com.greedygame.sample.sdk8.java.showcase.nongames.travel_app.adapters.viewpager.OnPageClick;
import com.greedygame.sample.sdk8.java.showcase.nongames.travel_app.adapters.viewpager.PlacesPagerAdapter;
import com.greedygame.sample.sdk8.java.showcase.nongames.travel_app.fragment.PlacesDetailFragment;
import com.greedygame.sample.sdk8.java.showcase.nongames.travel_app.model.PlacesPagerItem;
import com.greedygame.sample.sdk8.java.utils.Utils;
import com.greedygame.sample.sdk8.java.utils.notimportant.Rectangle;
import com.greedygame.sample.sdk8.java.utils.notimportant.SharedPrefManager;
import com.greedygame.sample.sdk8.java.utils.notimportant.SizeReductionPageTransformer;
import com.takusemba.spotlight.OnSpotlightListener;
import com.takusemba.spotlight.Spotlight;
import com.takusemba.spotlight.Target;
import com.takusemba.spotlight.effet.RippleEffect;
import com.takusemba.spotlight.shape.Circle;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import static android.graphics.Color.argb;

public class TravelDashboard extends BaseActivity implements OnPageClick {
    PlacesPagerAdapter placesPagerAdapter = new PlacesPagerAdapter(this);
    NewPlacesAdapter newPlacesAdapter = new NewPlacesAdapter();
    private TravelDashboardCampaignListener ggEventListener = new TravelDashboardCampaignListener();
    private int frameHolderId = 2567;
    private String AD_UNIT_4347 = "float-4347";

    ConstraintLayout root;
    ScrollView scrollView;
    ImageView tabAd,profileImage;
    ViewPager2 suggestionsPager;
    DotsIndicator dotsIndicator;
    RecyclerView newPlacesRv;
    LinearLayout linearLayout;
    Spotlight spotlight;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_dashboard);
        initViews();

    }

    @Override
    protected void onResume() {
        super.onResume();
        mBaseCampaignStateListener.receiver = ggEventListener;
    }

    private void initViews(){
        bindViews();
        setupViewPager();
        setupRecyclerview();
        FrameLayout frameHolder = new FrameLayout(this);
        frameHolder.setId(frameHolderId);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        root.addView(frameHolder,layoutParams);
        Utils.loadTextAd(tabAd,AD_UNIT_4347);
        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initCoachmarks();
            }
        });
    }

    private void bindViews(){
        root = findViewById(R.id.root);
        scrollView = findViewById(R.id.scrollView);
        tabAd = findViewById(R.id.tabAd);
        suggestionsPager  = findViewById(R.id.suggestionsPager);
        dotsIndicator = findViewById(R.id.dotsIndicator);
        newPlacesRv = findViewById(R.id.newPlacesRv);
        profileImage = findViewById(R.id.profileImage);
        linearLayout = findViewById(R.id.linearLayout);
    }

    private void setupViewPager(){
        suggestionsPager.setAdapter(placesPagerAdapter);
        placesPagerAdapter.filterData();
        suggestionsPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        suggestionsPager.setPageTransformer(new SizeReductionPageTransformer());
        dotsIndicator.setViewPager2(suggestionsPager);

    }

    private void setupRecyclerview(){
        newPlacesRv.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
        newPlacesRv.setAdapter(newPlacesAdapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(getSupportFragmentManager().getBackStackEntryCount() == 0){
            getSupportFragmentManager().popBackStackImmediate();
            return;
        }
        super.onBackPressed();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus && SharedPrefManager.isShouldShowCoachMarks()){
            initCoachmarks();
        }
    }

    private void initCoachmarks(){
        scrollView.scrollTo(0,0);
        final Target textAdTarget = getTextAdTarget();
        final Target pagerAdTarget = getPagerAdTarget();
        final Target restartCoachmarksTarget  = getRestartCoachmarksTarget();
        profileImage.postDelayed(new Runnable() {
            @Override
            public void run() {
                spotlight = new Spotlight.Builder(TravelDashboard.this)
                        .setTargets(textAdTarget,pagerAdTarget,restartCoachmarksTarget)
                        .setOnSpotlightListener(new OnSpotlightListener() {
                            @Override
                            public void onStarted() {

                            }

                            @Override
                            public void onEnded() {
                                SharedPrefManager.setShouldShowCoachMarks(false);
                            }
                        })
                .build();
                spotlight.start();
            }
        },1000);
    }


    private Target getTextAdTarget(){
        int[] viewPosition = Utils.getCenterCoordinates(linearLayout);
        return new Target.Builder()
                .setAnchor(viewPosition[0],viewPosition[1])
                .setShape(new com.greedygame.sample.sdk8.java.utils.notimportant.Rectangle(linearLayout,10))
                .setOverlay(LayoutInflater.from(this).inflate(R.layout.coach_marks_textad_target,null))
                .build();

    }

    private Target getPagerAdTarget(){
        int[] viewPosition = Utils.getCenterCoordinates(suggestionsPager);
        return new Target.Builder()
                .setAnchor(viewPosition[0],viewPosition[1])
                .setShape(new Rectangle(suggestionsPager,50))
                .setOverlay(LayoutInflater.from(this).inflate(R.layout.coach_marks_pager_target,null))
                .build();
    }
    private Target  getRestartCoachmarksTarget(){
        int[] viewPosition = Utils.getCenterCoordinates(profileImage);
        int radius = profileImage.getWidth()/2+50;
        return new Target.Builder()
                .setAnchor(viewPosition[0],viewPosition[1])
                .setShape(new Circle(radius,1000, new LinearInterpolator()))
                .setOverlay(LayoutInflater.from(this).inflate(R.layout.coach_marks_restart_target,null))
                .setEffect(new RippleEffect(100f, 200f, Color.argb(30, 124, 255, 90),1000,new AccelerateDecelerateInterpolator(),RippleEffect.DEFAULT_REPEAT_MODE))
                .build();
    }



    @Override
    public void onClick(PlacesPagerItem item) {
        PlacesDetailFragment fragment =  PlacesDetailFragment.newInstance(item);
        fragment.setEnterTransition(new Slide(Gravity.BOTTOM));
        getSupportFragmentManager()
                .beginTransaction().replace(frameHolderId,fragment).addToBackStack("").commit();

    }

    public void showNext(View view) {
        spotlight.next();
    }

    /**
     * TravelDashboardCampaignListener listens to event from SDK via BaseCampaignListener and filters data
     * to the list with or without ads if campaign is available or not available respectively.
     */
    private class TravelDashboardCampaignListener implements CampaignStateListener {
        void callFilters(){
            Utils.loadTextAd(tabAd,AD_UNIT_4347);
            placesPagerAdapter.filterData();
            newPlacesAdapter.filterData();
        }
        @Override
        public void onUnavailable() {
            callFilters();
        }

        @Override
        public void onAvailable(String p0) {
            callFilters();
        }

        @Override
        public void onError(String p0) {
            callFilters();
        }

    }
}
