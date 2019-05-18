package com.gusrinda.kodetree.Adapter;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gusrinda.kodetree.Model.BannerPojo;
import com.gusrinda.kodetree.Model.User;
import com.gusrinda.kodetree.R;

import java.util.ArrayList;

public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.BannerViewHolder> {
    public ArrayList<User> data = new ArrayList<>();
    public ScoreAdapter( ArrayList<User> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public BannerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.score_layout,viewGroup,false);

        return new BannerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BannerViewHolder bannerViewHolder, int i) {
        bannerViewHolder.setdata(i+1,data.get(i));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class BannerViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView rank;
        TextView score;
        BottomNavigationView nav;
        public BannerViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_score_layout_name);
            rank= itemView.findViewById(R.id.tv_score_layout_rank);
            score = itemView.findViewById(R.id.tv_score_layout_score);

        }
        public void setdata(int userRankrank , final User user){
            name.setText(user.getUsername());
            rank.setText(Integer.toString(userRankrank));
            score.setText(Integer.toString(user.getPoint()));


        }
    }
}
