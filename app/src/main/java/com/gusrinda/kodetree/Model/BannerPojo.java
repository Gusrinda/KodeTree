package com.gusrinda.kodetree.Model;

import android.support.v4.app.Fragment;

public class BannerPojo  {
    public int fragmentId;
    public int image;
    public String text;

    public BannerPojo(int target, int image, String text) {
        this.fragmentId = target;
        this.image = image;
        this.text = text;
    }
}
