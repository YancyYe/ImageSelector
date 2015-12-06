package com.yancy.imageselector;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Yancy on 2015/12/2.
 */
public class ImageSelectorActivity extends FragmentActivity implements ImageSelectorFragment.Callback {

    private final static String TAG = "ImageSelectorActivity";

    public static final String EXTRA_SELECT_COUNT = "max_select_count";

    public static final String EXTRA_SELECT_MODE = "select_count_mode";

    public static final String EXTRA_SHOW_CAMERA = "show_camera";

    public static final String EXTRA_RESULT = "select_result";

    public static final String EXTRA_DEFAULT_SELECTED_LIST = "default_list";

    public static final int MODE_SINGLE = 0;

    public static final int MODE_MULTI = 1;

    private ArrayList<String> pathList = new ArrayList<>();
    private TextView submitButton;
    private int defaultCount;

    private Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imageselector_activity);

        intent = getIntent();
        defaultCount = intent.getIntExtra(EXTRA_SELECT_COUNT, 9);
        int mode = intent.getIntExtra(EXTRA_SELECT_MODE, MODE_MULTI);
        boolean isShow = intent.getBooleanExtra(EXTRA_SHOW_CAMERA, true);
        if (mode == MODE_MULTI && intent.hasExtra(EXTRA_DEFAULT_SELECTED_LIST)) {
            pathList = intent.getStringArrayListExtra(EXTRA_DEFAULT_SELECTED_LIST);
        }

        Bundle bundle = new Bundle();
        bundle.putInt(ImageSelectorFragment.EXTRA_SELECT_COUNT, defaultCount);
        bundle.putInt(ImageSelectorFragment.EXTRA_SELECT_MODE, mode);
        bundle.putBoolean(ImageSelectorFragment.EXTRA_SHOW_CAMERA, isShow);
        bundle.putStringArrayList(ImageSelectorFragment.EXTRA_DEFAULT_SELECTED_LIST, pathList);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.image_grid, Fragment.instantiate(this, ImageSelectorFragment.class.getName(), bundle))
                .commit();

        submitButton = (TextView) super.findViewById(R.id.title_right);


        init();

    }

    private void init() {

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
            submitButton.setText((getResources().getText(R.string.finish)) + "(" + pathList.size() + "/" + defaultCount + ")");
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

    private void exit() {
        finish();
        overridePendingTransition(R.anim.imageselector_in_from_left, R.anim.imageselector_out_to_right);
    }


    @Override
    public void onSingleImageSelected(String path) {
        Intent data = new Intent();
        pathList.add(path);
        data.putStringArrayListExtra(EXTRA_RESULT, pathList);
        setResult(RESULT_OK, data);
        exit();
    }

    @Override
    public void onImageSelected(String path) {
        if (!pathList.contains(path)) {
            pathList.add(path);
        }
        if (pathList.size() > 0) {
            submitButton.setText((getResources().getText(R.string.finish)) + "(" + pathList.size() + "/" + defaultCount + ")");
            if (!submitButton.isEnabled()) {
                submitButton.setEnabled(true);
            }
        }
    }

    @Override
    public void onImageUnselected(String path) {
        if (pathList.contains(path)) {
            pathList.remove(path);
            submitButton.setText((getResources().getText(R.string.finish)) + "(" + pathList.size() + "/" + defaultCount + ")");
        } else {
            submitButton.setText((getResources().getText(R.string.finish)) + "(" + pathList.size() + "/" + defaultCount + ")");
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
    }
}