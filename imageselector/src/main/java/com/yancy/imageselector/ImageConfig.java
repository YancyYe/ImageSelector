package com.yancy.imageselector;

import android.app.Activity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * ImageConfig
 * Created by Yancy on 2015/12/6.
 */
public class ImageConfig implements Serializable {

    private boolean mutiSelect;
    private int maxSize;

    private boolean showCamera;

    private Activity activity;
    private ImageLoader imageLoader;

    private int titleBgColor;
    private int titleTextColor;
    private int titleSubmitTextColor;

    private int steepToolBarColor;

    private String filePath;

    private ArrayList<String> pathList;


    private ImageConfig(final Builder builder) {
        this.maxSize = builder.maxSize;
        this.showCamera = builder.showCamera;
        this.activity = builder.activity;
        this.imageLoader = builder.imageLoader;
        this.mutiSelect = builder.mutiSelect;
        this.pathList = builder.pathList;
        this.filePath = builder.filePath;


        this.titleBgColor = builder.titleBgColor;
        this.titleTextColor = builder.titleTextColor;
        this.titleSubmitTextColor = builder.titleSubmitTextColor;
        this.steepToolBarColor = builder.steepToolBarColor;

    }

    public static class Builder implements Serializable {
        private boolean mutiSelect = true;
        private int maxSize = 9;
        private boolean showCamera = false;

        private Activity activity;
        private ImageLoader imageLoader;

        private String filePath = "/temp/pictures";

        private int titleBgColor = 0XFF000000;
        private int titleTextColor = 0XFFFFFFFF;
        private int titleSubmitTextColor = 0XFFFFFFFF;

        private int steepToolBarColor = 0XFF000000;

        private ArrayList<String> pathList = new ArrayList<String>();


        public Builder(Activity activity, ImageLoader imageLoader) {
            this.activity = activity;
            this.imageLoader = imageLoader;
        }

        public Builder mutiSelect() {
            this.mutiSelect = true;
            return this;
        }

        public Builder filePath(String filePath) {
            this.filePath = filePath;
            return this;
        }

        public Builder pathList(ArrayList<String> pathList) {
            this.pathList = pathList;
            return this;
        }


        public Builder titleBgColor(int titleBgColor) {
            this.titleBgColor = titleBgColor;
            return this;
        }

        public Builder titleTextColor(int titleTextColor) {
            this.titleTextColor = titleTextColor;
            return this;
        }

        public Builder titleSubmitTextColor(int titleSubmitTextColor) {
            this.titleSubmitTextColor = titleSubmitTextColor;
            return this;
        }

        public Builder steepToolBarColor(int steepToolBarColor) {
            this.steepToolBarColor = steepToolBarColor;
            return this;
        }

        public Builder singleSelect() {
            this.mutiSelect = false;
            return this;
        }

        public Builder mutiSelectMaxSize(int maxSize) {
            this.maxSize = maxSize;
            return this;
        }


        public Builder showCamera() {
            this.showCamera = true;
            return this;
        }


        public ImageConfig build() {
            return new ImageConfig(this);
        }
    }

    public boolean isMutiSelect() {
        return mutiSelect;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public boolean isShowCamera() {
        return showCamera;
    }

    public Activity getActivity() {
        return activity;
    }

    public ImageLoader getImageLoader() {
        return imageLoader;
    }

    public int getTitleBgColor() {
        return titleBgColor;
    }

    public int getTitleTextColor() {
        return titleTextColor;
    }

    public int getTitleSubmitTextColor() {
        return titleSubmitTextColor;
    }

    public int getSteepToolBarColor() {
        return steepToolBarColor;
    }

    public ArrayList<String> getPathList() {
        return pathList;
    }

    public String getFilePath() {
        return filePath;
    }

}