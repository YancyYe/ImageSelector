package com.yancy.imageselector;

import android.content.Intent;
import android.widget.Toast;

import com.yancy.imageselector.utils.Utils;

/**
 * Desction
 * Created by Yancy on 2015/12/6.
 */
public class ImageSelector {


    public static final int IMAGE_REQUEST_CODE = 1002;

    private static ImageConfig mImageConfig;

    public static ImageConfig getImageConfig() {
        return mImageConfig;
    }

    public static void open(ImageConfig config) {
        if (config == null) {
            return;
        }
        mImageConfig = config;

        if (config.getImageLoader() == null) {
            Toast.makeText(config.getActivity(), R.string.open_camera_fail, Toast.LENGTH_SHORT).show();
            return;
        }

        if (!Utils.existSDCard()) {
            Toast.makeText(config.getActivity(), R.string.empty_sdcard, Toast.LENGTH_SHORT).show();
            return;
        }


        Intent intent = new Intent(config.getActivity(), ImageSelectorActivity.class);
        config.getActivity().startActivityForResult(intent, IMAGE_REQUEST_CODE);
    }

}