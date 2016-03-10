package com.yancy.imageselector;

import com.yancy.imageselector.utils.FileUtils;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * ImageConfig
 * Created by Yancy on 2015/12/6.
 */
public class ImageConfig {

    private boolean mutiSelect;
    private int maxSize;

    private boolean showCamera;

    private boolean crop;
    private int aspectX;
    private int aspectY;
    private int outputX;
    private int outputY;

    private ImageLoader imageLoader;

    private int titleBgColor;
    private int titleTextColor;
    private int titleSubmitTextColor;

    private int steepToolBarColor;

    private String filePath;

    private ArrayList<String> pathList;

    private int requestCode;


    private ImageConfig(final Builder builder) {
        this.maxSize = builder.maxSize;
        this.showCamera = builder.showCamera;
        this.imageLoader = builder.imageLoader;
        this.mutiSelect = builder.mutiSelect;
        this.pathList = builder.pathList;
        this.filePath = builder.filePath;

        this.crop = builder.crop;
        this.aspectX = builder.aspectX;
        this.aspectY = builder.aspectY;
        this.outputX = builder.outputX;
        this.outputY = builder.outputY;

        this.requestCode = builder.requestCode;

        this.titleBgColor = builder.titleBgColor;
        this.titleTextColor = builder.titleTextColor;
        this.titleSubmitTextColor = builder.titleSubmitTextColor;
        this.steepToolBarColor = builder.steepToolBarColor;

        FileUtils.createFile(this.filePath);
    }

    public static class Builder implements Serializable {
        private boolean mutiSelect = true;
        private int maxSize = 9;
        private boolean showCamera = false;

        private boolean crop = false;
        private int aspectX = 1;
        private int aspectY = 1;
        private int outputX = 500;
        private int outputY = 500;
        private int requestCode = ImageSelector.IMAGE_REQUEST_CODE;

        private ImageLoader imageLoader;

        private String filePath = "/temp/pictures";

        private int titleBgColor = 0XFF000000;
        private int titleTextColor = 0XFFFFFFFF;
        private int titleSubmitTextColor = 0XFFFFFFFF;

        private int steepToolBarColor = 0XFF000000;

        private ArrayList<String> pathList = new ArrayList<String>();


        public Builder(ImageLoader imageLoader) {
            this.imageLoader = imageLoader;
        }

        public Builder mutiSelect() {
            this.mutiSelect = true;
            return this;
        }

        public Builder crop() {
            this.crop = true;
            return this;
        }

        public Builder crop(int aspectX, int aspectY, int outputX, int outputY) {
            this.crop = true;
            this.aspectX = aspectX;
            this.aspectY = aspectY;
            this.outputX = outputX;
            this.outputY = outputY;
            return this;
        }

        public Builder requestCode(int requestCode) {
            this.requestCode = requestCode;
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

    public boolean isCrop() {
        return crop;
    }

    public int getAspectX() {
        return aspectX;
    }

    public int getAspectY() {
        return aspectY;
    }

    public int getOutputX() {
        return outputX;
    }

    public int getOutputY() {
        return outputY;
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

    public int getRequestCode() {
        return requestCode;
    }
}
