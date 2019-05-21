package com.gusrinda.kodetree.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.gusrinda.kodetree.Activity.DetailMarkerActivity;
import com.gusrinda.kodetree.Fragment.LocationFragment;
import com.gusrinda.kodetree.R;
import com.squareup.picasso.Picasso;

public class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {
    private final View mWindow;
    private Context mContext;

    public CustomInfoWindowAdapter(Context context) {
        mContext = context;
        mWindow = LayoutInflater.from(context).inflate(R.layout.info_window, null);
    }

    private void SetDataWindow(Marker marker, View view) {
        final String snippet = marker.getSnippet();
        ImageView imgPohon = view.findViewById(R.id.img_pohon);

        Glide.with(mContext)
                .load(snippet)
                .into(imgPohon);

        String title = marker.getTitle();
        TextView tvNamaPohon = view.findViewById(R.id.tv_namapohon);
        tvNamaPohon.setText(title);
    }

    @Override
    public View getInfoWindow(Marker marker) {
        SetDataWindow(marker, mWindow);
        return mWindow;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }
}
