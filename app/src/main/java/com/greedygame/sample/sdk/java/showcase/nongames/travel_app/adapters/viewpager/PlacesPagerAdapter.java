package com.greedygame.sample.sdk.java.showcase.nongames.travel_app.adapters.viewpager;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
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

public class PlacesPagerAdapter extends RecyclerView.Adapter<PlacesPagerAdapter.ViewHolder> {

    OnPageClick pageClickListener;
    Context context;

    public PlacesPagerAdapter(OnPageClick pageClickListener){
        this.pageClickListener = pageClickListener;
    }

    /***
     The list data represents your apps data for the recyclerview. When loading data from an api, insert ad objects
     within the data at predetermined positions like every 5th position. In this example it is every 3rd position.
     ** IMPORTANT **
     When displaying admob ads make sure that there is only one unit visible on the screen at any time.
     */
    private List<PlacesPagerItem> data = new ArrayList<>(Arrays.asList(
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
            )

    ));



    @Override
    public int getItemViewType(int position) {
        if (data.get(position).itemType == ItemTypes.CONTENT) {
            return R.layout.places_pager_item;
        }
        return 0;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(viewType,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(position,data.get(position),pageClickListener);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        View mView;
        ImageView heroImage;
        ConstraintLayout container;
        TextView title,location;
        ViewHolder(View view){
            super(view);
            mView = view;

        }
        void bind(int position,final BaseItem listItem, final OnPageClick onPageClick) {
            switch (listItem.itemType){
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

