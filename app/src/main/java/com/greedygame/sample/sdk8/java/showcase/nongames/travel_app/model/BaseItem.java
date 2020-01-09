package com.greedygame.sample.sdk8.java.showcase.nongames.travel_app.model;

import android.os.Parcel;
import android.os.Parcelable;

public class BaseItem implements Parcelable {
    public ItemTypes itemType ;
    public String value;

    BaseItem(ItemTypes type,String value){
        itemType = type;
        this.value  = value;
    }

    public BaseItem(Parcel in){
        this.itemType = (ItemTypes) in.readSerializable();
        this.value = in.readString();
    }
    public static final Creator<BaseItem> CREATOR = new Creator<BaseItem>() {
        @Override
        public BaseItem createFromParcel(Parcel in) {
            return new BaseItem(in);
        }

        @Override
        public BaseItem[] newArray(int size) {
            return new BaseItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeSerializable(itemType);
        dest.writeString(value);
    }
}
