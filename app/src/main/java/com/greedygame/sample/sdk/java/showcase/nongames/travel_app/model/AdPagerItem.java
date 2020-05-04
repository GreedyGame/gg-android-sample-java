package com.greedygame.sample.sdk.java.showcase.nongames.travel_app.model;

import android.os.Parcel;

public class AdPagerItem extends BaseItem {

    String adValue;

    public AdPagerItem(ItemTypes type){
        super(type);
    }

   private AdPagerItem(Parcel in){
        super(in);
        adValue = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(adValue);
    }

    public static final Creator<AdPagerItem> CREATOR = new Creator<AdPagerItem>() {
        @Override
        public AdPagerItem createFromParcel(Parcel in) {
            return new AdPagerItem(in);
        }

        @Override
        public AdPagerItem[] newArray(int size) {
            return new AdPagerItem[size];
        }
    };
}
