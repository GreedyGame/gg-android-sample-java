package com.greedygame.sample.sdk.java.showcase.nongames.utils;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class Utils {

    public static void  loadImage(ImageView iv,String url){
        if(!TextUtils.isEmpty(url)){
            Picasso.with(iv.getContext()).load(url).into(iv);
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
