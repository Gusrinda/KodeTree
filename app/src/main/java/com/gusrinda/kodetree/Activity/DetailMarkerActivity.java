package com.gusrinda.kodetree.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.gusrinda.kodetree.R;

public class DetailMarkerActivity extends AppCompatActivity {

    private ImageView imgDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_marker);

        imgDetail = findViewById(R.id.img_detail_marker);

        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        Glide.with(this)
                .load(url)
                .into(imgDetail);
    }
}
