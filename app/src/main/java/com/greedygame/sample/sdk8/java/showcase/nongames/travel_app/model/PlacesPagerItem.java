package com.greedygame.sample.sdk8.java.showcase.nongames.travel_app.model;

import android.os.Parcel;
import android.os.Parcelable;

public class PlacesPagerItem extends BaseItem {

    public String title;
    public String location;
    public String heroUrl;

    public PlacesPagerItem(ItemTypes type, String heroUrl, String location ,String title){
        super(type,heroUrl);
        this.title = title;
        this.location = location;
        this.heroUrl = heroUrl;
    }

    private PlacesPagerItem(Parcel in){
        super(in);
        this.title = in.readString();
        this.location = in.readString();
        this.heroUrl  = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(title);
        dest.writeString(location);
        dest.writeString(heroUrl);
    }


    public static final Creator<PlacesPagerItem> CREATOR = new Creator<PlacesPagerItem>() {
        @Override
        public PlacesPagerItem createFromParcel(Parcel in) {
            return new PlacesPagerItem(in);
        }

        @Override
        public PlacesPagerItem[] newArray(int size) {
            return new PlacesPagerItem[size];
        }
    };
}
