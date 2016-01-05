package com.yancy.imageselector;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yancy.imageselector.utils.Utils;

import java.io.File;
import java.util.ArrayList;

/**
 * ImageSelectorActivity
 * Created by Yancy on 2015/12/2.
 */
public class ImageSelectorActivity extends FragmentActivity implements ImageSelectorFragment.Callback {


    public static final String EXTRA_RESULT = "select_result";

    private ArrayList<String> pathList = new ArrayList<>();

    private ImageConfig imageConfig;

    private TextView title_text;
    private TextView submitButton;
    private RelativeLayout imageselector_title_bar_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imageselector_activity);

        imageConfig = ImageSelector.getImageConfig();

        Utils.hideTitleBar(this, R.id.imageselector_activity_layout, imageConfig.getSteepToolBarColor());

        getSupportFragmentManager().beginTransaction()
                .add(R.id.image_grid, Fragment.instantiate(this, ImageSelectorFragment.class.getName(), null))
                .commit();

        submitButton = (TextView) super.findViewById(R.id.title_right);
        title_text = (TextView) super.findViewById(R.id.title_text);
        imageselector_title_bar_layout = (RelativeLayout) super.findViewById(R.id.imageselector_title_bar_layout);

        init();

    }

    private void init() {

        submitButton.setTextColor(imageConfig.getTitleSubmitTextColor());
        title_text.setTextColor(imageConfig.getTitleTextColor());
        imageselector_title_bar_layout.setBackgroundColor(imageConfig.getTitleBgColor());

        pathList = imageConfig.getPathList();


        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                exit();
            }
        });


        if (pathList == null || pathList.size() <= 0) {
            submitButton.setText(R.string.finish);
            submitButton.setEnabled(false);
        } else {
            submitButton.setText((getResources().getText(R.string.finish)) + "(" + pathList.size() + "/" + imageConfig.getMaxSize() + ")");
            submitButton.setEnabled(true);
        }
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pathList != null && pathList.size() > 0) {
                    Intent data = new Intent();
                    data.putStringArrayListExtra(EXTRA_RESULT, pathList);
                    setResult(RESULT_OK, data);
                    exit();
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ImageSelector.IMAGE_CROP_CODE && resultCode == RESULT_OK) {
            Intent intent = new Intent();
            pathList.add(cropImagePath);
            intent.putStringArrayListExtra(EXTRA_RESULT, pathList);
            setResult(RESULT_OK, intent);
            exit();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void exit() {
        finish();
    }

    private String cropImagePath;

    private void crop(String imagePath, int aspectX, int aspectY, int outputX, int outputY) {
        File file;
        if (Utils.existSDCard()) {
            file = new File(Environment.getExternalStorageDirectory() + imageConfig.getFilePath(), Utils.getImageName());
        } else {
            file = new File(getCacheDir(), Utils.getImageName());
        }


        cropImagePath = file.getAbsolutePath();
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(Uri.fromFile(new File(imagePath)), "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", aspectX);
        intent.putExtra("aspectY", aspectY);
        intent.putExtra("outputX", outputX);
        intent.putExtra("outputY", outputY);
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        startActivityForResult(intent, ImageSelector.IMAGE_CROP_CODE);
    }

    @Override
    public void onSingleImageSelected(String path) {
        if (imageConfig.isCrop()) {
            crop(path, imageConfig.getAspectX(), imageConfig.getAspectY(), imageConfig.getOutputX(), imageConfig.getOutputY());
        } else {
            Intent data = new Intent();
            pathList.add(path);
            data.putStringArrayListExtra(EXTRA_RESULT, pathList);
            setResult(RESULT_OK, data);
            exit();
        }
    }

    @Override
    public void onImageSelected(String path) {
        if (!pathList.contains(path)) {
            pathList.add(path);
        }
        if (pathList.size() > 0) {
            submitButton.setText((getResources().getText(R.string.finish)) + "(" + pathList.size() + "/" + imageConfig.getMaxSize() + ")");
            if (!submitButton.isEnabled()) {
                submitButton.setEnabled(true);
            }
        }
    }

    @Override
    public void onImageUnselected(String path) {
        if (pathList.contains(path)) {
            pathList.remove(path);
            submitButton.setText((getResources().getText(R.string.finish)) + "(" + pathList.size() + "/" + imageConfig.getMaxSize() + ")");
        } else {
            submitButton.setText((getResources().getText(R.string.finish)) + "(" + pathList.size() + "/" + imageConfig.getMaxSize() + ")");
        }
        if (pathList.size() == 0) {
            submitButton.setText(R.string.finish);
            submitButton.setEnabled(false);
        }
    }

    @Override
    public void onCameraShot(File imageFile) {
        if (imageFile != null) {
            Intent data = new Intent();
            pathList.add(imageFile.getAbsolutePath());
            data.putStringArrayListExtra(EXTRA_RESULT, pathList);
            setResult(RESULT_OK, data);
            exit();
        }
        if (imageFile != null) {
            if (imageConfig.isCrop()) {
                crop(imageFile.getAbsolutePath(), imageConfig.getAspectX(), imageConfig.getAspectY(), imageConfig.getOutputX(), imageConfig.getOutputY());
            } else {
                Intent data = new Intent();
                pathList.add(imageFile.getAbsolutePath());
                data.putStringArrayListExtra(EXTRA_RESULT, pathList);
                setResult(RESULT_OK, data);
                exit();
            }
        }

    }
}