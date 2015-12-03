package com.yancy.imageselectordemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.yancy.imageselector.ImageSelectorActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ImageSelectorActivity.class);

                // 是否显示调用相机拍照
                intent.putExtra(ImageSelectorActivity.EXTRA_SHOW_CAMERA, true);

                // 最大图片选择数量
                intent.putExtra(ImageSelectorActivity.EXTRA_SELECT_COUNT, 9);

                // 设置模式 (支持 单选/MultiImageSelectorActivity.MODE_SINGLE 或者 多选/MultiImageSelectorActivity.MODE_MULTI)
                intent.putExtra(ImageSelectorActivity.EXTRA_SELECT_MODE, ImageSelectorActivity.MODE_MULTI);

                startActivityForResult(intent, REQUEST_IMAGE);
            }
        });
    }


    private static int REQUEST_IMAGE = 1;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE) {
            if (resultCode == RESULT_OK) {
                // 获取返回的图片列表
                List<String> path = data.getStringArrayListExtra(ImageSelectorActivity.EXTRA_RESULT);

                for (String s : path) {
                    Log.i("------", s);
                }
            }
        }
    }
}
