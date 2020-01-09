package com.greedygame.sample.sdk8.java.showcase.nongames.travel_app.adapters.recyclerview;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.greedygame.sample.sdk8.java.BaseActivity;
import com.greedygame.sample.sdk8.java.R;
import com.greedygame.sample.sdk8.java.showcase.nongames.travel_app.model.AdPagerItem;
import com.greedygame.sample.sdk8.java.showcase.nongames.travel_app.model.BaseItem;
import com.greedygame.sample.sdk8.java.showcase.nongames.travel_app.model.ItemTypes;
import com.greedygame.sample.sdk8.java.showcase.nongames.travel_app.model.PlacesPagerItem;
import com.greedygame.sample.sdk8.java.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class NewPlacesAdapter extends RecyclerView.Adapter<NewPlacesAdapter.NewPlaceViewHolder> {

    private final String  AD_UNIT_FLOAT_4348 = "float-4348";

    private List<BaseItem> originalData = new ArrayList<>(Arrays.asList(
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
                    ItemTypes.AD,
                    AD_UNIT_FLOAT_4348
            ),
            new PlacesPagerItem(
                    ItemTypes.CONTENT,
                    "https://i.imgur.com/BihS6yR.png",
                    "Venice",
                    "River Aga"

            ),
            new AdPagerItem(
                    ItemTypes.AD,
                    AD_UNIT_FLOAT_4348
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
    private List<BaseItem> data = new ArrayList<>();

    public NewPlacesAdapter(){
        filterData();
    }


    public void filterData(){
        Log.d("CAMPAIGN_AVAILABLE","Called with"+ BaseActivity.mGreedyGameAgent.isCampaignAvailable());
        if(!BaseActivity.mGreedyGameAgent.isCampaignAvailable()) {
            for (BaseItem item : originalData) {
                if (item.itemType == ItemTypes.CONTENT) {
                    data.add(item);
                }
            }
        }else
            data = originalData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NewPlaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
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
        holder.bind(data.get(position));
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

    class NewPlaceViewHolder extends RecyclerView.ViewHolder {
        View mView;
        ImageView placeImage;
        TextView place,placeName;
        NewPlaceViewHolder(View view){
            super(view);
            mView = view;
        }
        public void bind( final BaseItem baseItem) {
            switch(baseItem.itemType){
                case AD:
                    placeImage  = mView.findViewById(R.id.placeImage);
                    Utils.loadWithRoundedCorners(placeImage,baseItem.value);

                    placeImage.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            BaseActivity.mGreedyGameAgent.showUII(baseItem.value);;
                        }
                    });
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
    }

}

