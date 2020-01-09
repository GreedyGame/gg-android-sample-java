package com.greedygame.sample.sdk8.java.showcase.nongames.travel_app.model;

import android.os.Parcel;
import android.os.Parcelable;

public class AdPagerItem extends BaseItem {

    String adValue;

    public AdPagerItem(ItemTypes type, String adValue){
        super(type,adValue);
        this.adValue = adValue;
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
