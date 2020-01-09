package com.greedygame.sample.sdk8.java.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.greedygame.android.agent.GreedyGameAgent;
import com.greedygame.sample.sdk8.java.BaseActivity;
import com.greedygame.sample.sdk8.java.utils.notimportant.CircularTransform;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import java.io.File;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

public class Utils {

    public static void loadAd(ImageView iv,String unitId, boolean useCircularTransform) {
        if (!BaseActivity.mGreedyGameAgent.isCampaignAvailable()) {
            //Hides is the view if a campaign is not available
            iv.setVisibility(View.GONE);
        } else {
            iv.setVisibility(View.VISIBLE);
        }
        if (!TextUtils.isEmpty(unitId)) {
            String adPath = BaseActivity.mGreedyGameAgent.getPath(unitId);
            if (!TextUtils.isEmpty(adPath)) {
                File adFile = new File(adPath);
                RequestCreator picasso = Picasso.with(iv.getContext()).load(adFile)
                        .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE);
                if (useCircularTransform) {
                    picasso.transform(new CircularTransform());
                }
                picasso.noFade().into(iv);
            } else{
                iv.setVisibility(View.GONE);
            }
        }

    }

    public static void  loadImage(ImageView iv,String url){
        if(!TextUtils.isEmpty(url)){
            Picasso.with(iv.getContext()).load(url).into(iv);
        }

    }

    public static void loadTextAd(ImageView iv,String unitId){
        if (!BaseActivity.mGreedyGameAgent.isCampaignAvailable()) {
            //Hides is the view if a campaign is not available
            iv.setVisibility(View.GONE);
        } else {
            iv.setVisibility(View.VISIBLE);
        }
        if(!TextUtils.isEmpty(unitId)) {
            String adPath = BaseActivity.mGreedyGameAgent.getPath(unitId);
            Bitmap bitmap = BitmapFactory.decodeFile(adPath);
            iv.setImageBitmap(bitmap);
        } else
            iv.setVisibility(View.GONE);
    }

    public static void loadWithRoundedCorners(ImageView iv, String unitId){

        if(!BaseActivity.mGreedyGameAgent.isCampaignAvailable()){
            iv.setVisibility(View.GONE);
        }
        else{
            iv.setVisibility(View.VISIBLE);
        }
        if(!TextUtils.isEmpty(unitId)){
            String adPath = BaseActivity.mGreedyGameAgent.getPath(unitId);
            if(!TextUtils.isEmpty(adPath)){
                File adFile = new File(adPath);
                RequestCreator picasso = Picasso.with(iv.getContext()).load(adFile)
                        .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE);
                picasso.transform(new RoundedCornersTransformation(10,0));
                picasso.into(iv);
            } else{
                iv.setVisibility(View.GONE);
            }
        }

    }

    public static int[] getCenterCoordinates(View view){
        int[] viewposition = new int[2];
        view.getLocationOnScreen(viewposition);
        viewposition[0]= viewposition[0]+ view.getWidth()/2;
        viewposition[1]=viewposition[1]+view.getHeight()/2;
        return viewposition;
    }
}
