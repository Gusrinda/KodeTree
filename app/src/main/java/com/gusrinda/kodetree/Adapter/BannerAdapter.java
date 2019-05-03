package com.gusrinda.kodetree.Adapter;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gusrinda.kodetree.Model.BannerPojo;
import com.gusrinda.kodetree.R;

import java.util.ArrayList;
import java.util.UUID;

public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.BannerViewHolder> {
    public ArrayList<BannerPojo> data = new ArrayList<>();
    public BottomNavigationView nav;
    public BannerAdapter(BottomNavigationView nav, ArrayList<BannerPojo> data) {
        this.data = data;
        this.nav = nav;
    }

    @NonNull
    @Override
    public BannerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.compenent_banner_card_view,viewGroup,false);
        if(nav == null){
            Log.d("HOME ","Nav null");
        }
        return new BannerViewHolder(v,nav);
    }

    @Override
    public void onBindViewHolder(@NonNull BannerViewHolder bannerViewHolder, int i) {
        bannerViewHolder.setdata(data.get(i));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class BannerViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView textView;
        CardView cardView;
        BottomNavigationView nav;
        public BannerViewHolder(@NonNull View itemView,BottomNavigationView nav) {
            super(itemView);
            this.nav =  nav;
            cardView = itemView.findViewById(R.id.cv_banner_card_view);
            image = itemView.findViewById(R.id.iv_banner_card_view);
            textView = itemView.findViewById(R.id.tv_banner_card_view);

        }
        public void setdata(final BannerPojo data){
            image.setImageResource(data.image);
            cardView.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                nav.setSelectedItemId(data.fragmentId);
                                            }
                                        }

            );

            textView.setText(data.text);

        }
    }
}
