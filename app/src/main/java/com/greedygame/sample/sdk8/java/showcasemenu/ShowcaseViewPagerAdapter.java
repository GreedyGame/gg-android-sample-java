package com.greedygame.sample.sdk8.java.showcasemenu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.greedygame.sample.sdk8.java.R;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

class ShowcaseViewPagerAdapter extends  RecyclerView.Adapter<ShowcaseViewPagerAdapter.ViewHolder> {

    private List<ShowCasePagerItem> data = Arrays.asList(
            new ShowCasePagerItem(
                    "For Publishers",
                    "Solving two key issues was important for native to be a viable ad strategy for publishers – placement rule-set and fill-rate. With our product suite, publishers \n" +
                            "can now implement native ads with ease, automate design optimization to improve CTR for a considerable jump in revenue.",
                    R.drawable.publisher
            ),
            new ShowCasePagerItem(
                    "For Advertiser",
                    "User experience is key for us. Any ad from our platform is quality, compliant, relevant and most importantly opt-in. This results into effective outcome metrics for the advertiser and non-intrusive ads for the user automatically.",
                    R.drawable.publisher
            )


    );

    @Override
    public void onBindViewHolder(ViewHolder holder, int position ) {
        holder.bind(data.get(position));
    }


    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(
            LayoutInflater.from(parent.getContext()).inflate(R.layout.showcase_item_points, parent, false));

    }


    @Override
    public int getItemCount() {return data.size();}

    class ViewHolder extends RecyclerView.ViewHolder{
        private View  view;
        ImageView targetIcon;
        TextView target,targetDesc;

        ViewHolder(View view){
            super(view);
            this.view  = view;
            targetIcon = view.findViewById(R.id.targetIcon);
            target = view.findViewById(R.id.target);
            targetDesc = view.findViewById(R.id.targetDesc);
        }
        void bind(ShowCasePagerItem listItem) {
            targetIcon.setImageDrawable(ContextCompat.getDrawable(view.getContext(),listItem.icon));
            target.setText(listItem.title);
            targetDesc.setText(listItem.desc);
        }

    }

}

class ShowCasePagerItem{
    String title;
    String desc;
    int icon;

    ShowCasePagerItem(String title, String desc, int icon) {
        this.title = title;
        this.desc = desc;
        this.icon = icon;
    }
}

