package com.greedygame.sample.sdk8.java.showcase.nongames.travel_app.adapters.viewpager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
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

public class PlacesPagerAdapter extends RecyclerView.Adapter<PlacesPagerAdapter.ViewHolder> {

    OnPageClick pageClickListener;

    public PlacesPagerAdapter(OnPageClick pageClickListener){
        this.pageClickListener = pageClickListener;
    }

    /**
     * This ad unit is inserted in between the data set. You can add multiple ad units based on your requirements.
     */

    private final String  AD_UNIT_4343 = "float-4343";

    private List<BaseItem> originalData = new ArrayList<>(Arrays.asList(
            new PlacesPagerItem(
                    ItemTypes.CONTENT,
                    "https://i.imgur.com/y7v9pCJ.png",
                    "Antelope\nCanyon",
                    "Arizona,USA"
            ), new PlacesPagerItem(
                    ItemTypes.CONTENT,
                    "https://i.imgur.com/JbZ92pE.png",
                    "Beach\nMaldives",
                    "Maldives"
            ), new PlacesPagerItem(
                    ItemTypes.CONTENT,
                    "https://i.imgur.com/ZBFe67z.png",
                    "Amristar\nFort",
                    "Amrister,India"
            ), new AdPagerItem(
                    ItemTypes.AD,
                    AD_UNIT_4343
            ), new PlacesPagerItem(
                    ItemTypes.CONTENT,
                    "https://i.imgur.com/T5tPude.png",
                    "Malibu\nIsland",
                    "California,USA"
            ), new  PlacesPagerItem(
                    ItemTypes.CONTENT,
                    "https://i.imgur.com/v9CS3W3.png",
                    "Eiffel\nTower",
                    "Paris,France"
            ), new AdPagerItem(
                    ItemTypes.AD,
                    AD_UNIT_4343
            )

    ));
    private List<BaseItem> data = new ArrayList<>();

    /**
     * This function will filter data based on the campaign availability. Each time a refresh is called on GreedyGame agent
     * data will be filtered based on campaign status.
     */
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


    @Override
    public int getItemViewType(int position) {
        switch(data.get(position).itemType){
            case AD:
                return R.layout.places_pager_ad_item;
            case CONTENT:
                return R.layout.places_pager_item;
        }
        return 0;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(viewType,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(data.get(position),pageClickListener);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        View mView;
        ImageView adUnit,heroImage;
        ConstraintLayout container;
        TextView title,location;
        ViewHolder(View view){
            super(view);
            mView = view;

        }
        void bind(final BaseItem listItem, final OnPageClick onPageClick) {
            switch (listItem.itemType){
                case AD:
                    adUnit = mView.findViewById(R.id.adUnit);
                    container = mView.findViewById(R.id.container);

                    if(BaseActivity.mGreedyGameAgent.isCampaignAvailable()) {
                        Utils.loadAd(adUnit,listItem.value,false);
                        adUnit.setScaleType(ImageView.ScaleType.FIT_CENTER);
                        container.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                BaseActivity.mGreedyGameAgent.showUII(listItem.value);
                            }
                        });
                    }
                    break;
                case CONTENT:
                    final PlacesPagerItem dataItem = (PlacesPagerItem) listItem;
                    title = mView.findViewById(R.id.title);
                    location = mView.findViewById(R.id.location);
                    heroImage  = mView.findViewById(R.id.heroImage);
                    container = mView.findViewById(R.id.container);
                    title.setText(dataItem.title);
                    location.setText(dataItem.location);
                    Utils.loadImage(heroImage,dataItem.heroUrl);
                    container.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onPageClick.onClick(dataItem);
                        }
                    });
            }
        }

    }

}

