package com.greedygame.sample.sdk.java.showcase.nongames.travel_app.adapters.recyclerview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.greedygame.core.adview.GGAdview;
import com.greedygame.core.adview.interfaces.AdLoadCallback;
import com.greedygame.core.adview.modals.AdRequestErrors;
import com.greedygame.sample.BaseActivity;
import com.greedygame.sample.sdk8.java.R;
import com.greedygame.sample.sdk.java.showcase.nongames.travel_app.model.AdPagerItem;
import com.greedygame.sample.sdk.java.showcase.nongames.travel_app.model.BaseItem;
import com.greedygame.sample.sdk.java.showcase.nongames.travel_app.model.ItemTypes;
import com.greedygame.sample.sdk.java.showcase.nongames.travel_app.model.PlacesPagerItem;
import com.greedygame.sample.sdk.java.showcase.nongames.utils.Utils;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NewPlacesAdapter extends RecyclerView.Adapter<NewPlacesAdapter.NewPlaceViewHolder> {

    private Context context;
    /**
     The list data represents your apps data for the recyclerview. When loading data from an api, insert ad objects
     within the data at predetermined positions like every 5th position. In this example it is every 3rd position.
     ** IMPORTANT **
     When displaying admob ads make sure that there is only one unit visible on the screen at any time.
     */
    private List<BaseItem> data = new ArrayList<>(Arrays.asList(
           new PlacesPagerItem(
                    ItemTypes.CONTENT,
                   "https://i.imgur.com/0a6l6n2.png",
                    "Ireland",
                    "Causeaway"
            ),
            new PlacesPagerItem(
                    ItemTypes.CONTENT,
                    "https://i.imgur.com/uNcRope.png",
                    "Iceland",
                    "Castle"

            ),
            new AdPagerItem(
                    ItemTypes.AD
            ),
            new PlacesPagerItem(
                    ItemTypes.CONTENT,
                    "https://i.imgur.com/BihS6yR.png",
                    "Venice",
                    "River Aga"

            ),
            new AdPagerItem(
                    ItemTypes.AD
            ),
            new PlacesPagerItem(
                    ItemTypes.CONTENT,
                    "https://i.imgur.com/7tPNcqK.png",
                    "Turkey",
                    "The Mosque"

            ),
            new PlacesPagerItem(
                    ItemTypes.CONTENT,
                    "https://i.imgur.com/dgzviKz.png",
                    "Baghdad",
                    "The Valley"

            )
    ));



    @NonNull
    @Override
    public NewPlaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new NewPlaceViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false)
        );
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onBindViewHolder(@NonNull NewPlaceViewHolder holder, int position) {
        holder.bind(position,data.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        switch(data.get(position).itemType){
            case AD:
                return R.layout.new_places_ad_item;
            case CONTENT:
                return R.layout.new_places_rv_item;
        }
        return 0;
    }

    class NewPlaceViewHolder extends RecyclerView.ViewHolder implements AdLoadCallback {
        View mView;
        ImageView placeImage;
        TextView place,placeName;
        GGAdview adUnit;
        NewPlaceViewHolder(View view){
            super(view);
            mView = view;
        }
        public void bind(int position,final BaseItem baseItem) {
            switch(baseItem.itemType){
                case AD:
                    adUnit = mView.findViewById(R.id.placeImageAd);
                    adUnit.loadAd(new AdLoadListener(position));
                    break;
                case CONTENT:
                    placeImage = mView.findViewById(R.id.placeImage);
                    place = mView.findViewById(R.id.place);
                    placeName = mView.findViewById(R.id.placeName);

                    placeImage.setScaleType(ImageView.ScaleType.FIT_XY);
                    PlacesPagerItem dataItem = (PlacesPagerItem) baseItem;
                    place.setText(dataItem.location);
                    placeName.setText(dataItem.title);
                    Utils.loadImage(placeImage,dataItem.value);
                    break;
            }
        }

        @Override
        public void onAdLoadFailed(@NotNull AdRequestErrors adRequestErrors) {
            Toast.makeText(context,"AdLoad Failed",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onAdLoaded() {
            Toast.makeText(context,"AdLoaded",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onReadyForRefresh() {
            Toast.makeText(context,"Ready for refresh",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onUiiClosed() {
            Toast.makeText(context,"Uii Closed",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onUiiOpened() {
            Toast.makeText(context,"Uii Opened",Toast.LENGTH_SHORT).show();
        }
    }

    class AdLoadListener implements AdLoadCallback{
        int mPosition;
        AdLoadListener(int position){
            mPosition = position;
        }

        @Override
        public void onAdLoadFailed(@NotNull AdRequestErrors adRequestErrors) {
            data.remove(mPosition);
            notifyItemRemoved(mPosition);
        }

        @Override
        public void onAdLoaded() {

        }

        @Override
        public void onReadyForRefresh() {

        }

        @Override
        public void onUiiClosed() {

        }

        @Override
        public void onUiiOpened() {

        }
    }

}

