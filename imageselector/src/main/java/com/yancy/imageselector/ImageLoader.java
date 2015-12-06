package com.yancy.imageselector;

import android.content.Context;
import android.widget.ImageView;

import java.io.Serializable;

/**
 * ImageLoader
 * Created by Yancy on 2015/12/6.
 */
public interface ImageLoader extends Serializable {
    void displayImage(Context context, String path, ImageView imageView);
}