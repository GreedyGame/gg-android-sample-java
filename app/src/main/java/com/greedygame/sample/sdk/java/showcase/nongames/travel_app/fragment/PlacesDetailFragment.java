package com.greedygame.sample.sdk.java.showcase.nongames.travel_app.fragment;

import android.animation.ValueAnimator;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.greedygame.core.adview.GGAdview;
import com.greedygame.core.adview.interfaces.AdLoadCallback;
import com.greedygame.core.adview.modals.AdRequestErrors;
import com.greedygame.sample.BaseActivity;
import com.greedygame.sample.sdk8.java.R;
import com.greedygame.sample.sdk.java.showcase.nongames.travel_app.model.PlacesPagerItem;
import com.greedygame.sample.sdk.java.showcase.nongames.utils.Utils;

import org.jetbrains.annotations.NotNull;

import kotlin.jvm.JvmStatic;

public class PlacesDetailFragment extends Fragment implements AdLoadCallback {
    private static final String ARG_PARAM1 = "param1";
    private PlacesPagerItem param1 = null;
    private ValueAnimator alphaValueAnimator =  ValueAnimator.ofFloat(1f,0f);
    private ImageView exitButton,heroImage,ctaAdUnit;
    private GGAdview topAdUnit;
    private TextView rating,title,location;
    private ScrollView scrollview;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null && getArguments().containsKey(ARG_PARAM1)){
           param1 =  getArguments().getParcelable(ARG_PARAM1);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_place_detail,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindView(view);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getActivity()!= null)
                    getActivity().onBackPressed();
            }
        });

//        topAdUnit.loadAd(this);
        GGAdview bannerUnit = view.findViewById(R.id.bannerUnit);
        bannerUnit.loadAd(this);
        if(param1 != null){
            title.setText(param1.title.replace("\n" ," "));
            location.setText(param1.location);
            Utils.loadImage(heroImage,param1.heroUrl);
        }

        setScrollView();
    }
    private void setScrollView(){
        final Rect scrollViewRect = new Rect();
        final VisiblityController visiblityController = new VisiblityController();
        visiblityController.update(View.GONE);
        scrollview.getHitRect(scrollViewRect);
        scrollview.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                if(ctaAdUnit.getLocalVisibleRect(scrollViewRect)){
                 visiblityController.update(View.GONE);
                }else{
                    visiblityController.update(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();
        alphaValueAnimator.cancel();
    }

    private void bindView(View view){
        exitButton = view.findViewById(R.id.exitButton);
        heroImage = view.findViewById(R.id.heroImage);
        ctaAdUnit = view.findViewById(R.id.ctaAdUnit);
        topAdUnit = view.findViewById(R.id.topAdUnit);

        scrollview = view.findViewById(R.id.scrollView);

        rating = view.findViewById(R.id.rating);
        title = view.findViewById(R.id.title);
        location = view.findViewById(R.id.location);
    }

    @Override
    public void onAdLoadFailed(@NotNull AdRequestErrors adRequestErrors) {
        Toast.makeText(getContext(),"AdLoad Failed",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAdLoaded() {
        Toast.makeText(getContext(),"AdLoaded",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onReadyForRefresh() {
        Toast.makeText(getContext(),"Ready for refresh",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUiiClosed() {
        Toast.makeText(getContext(),"Uii Closed",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUiiOpened() {
        Toast.makeText(getContext(),"Uii Opened",Toast.LENGTH_SHORT).show();
    }

    //Not Important for sdk integration
    class VisiblityController{
        private int currentVisiblity = -999;

        void update(int newVisibility){
            if(newVisibility != currentVisiblity){
                currentVisiblity = newVisibility;
                onChangeCallback();
            }
        }

        void onChangeCallback(){
            if(currentVisiblity == View.VISIBLE){
                topAdUnit.animate().translationY(0f).setDuration(1000);
            }
            else{
                topAdUnit.animate().translationY(-1000f).setDuration(1000);
            }
        }
    }

    @JvmStatic
    public static PlacesDetailFragment newInstance(PlacesPagerItem item){
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1,item);
        PlacesDetailFragment instance = new PlacesDetailFragment();
        instance.setArguments(args);
        return instance;
    }
}
