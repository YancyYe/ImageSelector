package com.yancy.imageselectordemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.yancy.imageselector.ImageSelectorActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yancy on 2015/12/4.
 */
public class MainActivity extends AppCompatActivity {


    private Adapter adapter;

    private List<String> path = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button but = (Button) super.findViewById(R.id.but);
        RecyclerView recycler = (RecyclerView) super.findViewById(R.id.recycler);

        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ImageSelectorActivity.class);

                // Capturing Photos
                intent.putExtra(ImageSelectorActivity.EXTRA_SHOW_CAMERA, true);

                // Max Picture Number
                intent.putExtra(ImageSelectorActivity.EXTRA_SELECT_COUNT, 9);

                /**
                 * Setting Pattern
                 * Radio        :    ImageSelectorActivity.MODE_SINGLE
                 * MultiSelect  :    ImageSelectorActivity.MODE_MULTI
                 */
                intent.putExtra(ImageSelectorActivity.EXTRA_SELECT_MODE, ImageSelectorActivity.MODE_MULTI);

                startActivityForResult(intent, REQUEST_IMAGE);
            }
        });


        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        recycler.setLayoutManager(gridLayoutManager);
        adapter = new Adapter(this, path);
        recycler.setAdapter(adapter);

    }


    private static int REQUEST_IMAGE = 1;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE && resultCode == RESULT_OK && data != null) {
            // Get Image Path List
            List<String> pathList = data.getStringArrayListExtra(ImageSelectorActivity.EXTRA_RESULT);

            for (String path : pathList) {
                Log.i("ImagePathList", path);
            }

            path.clear();
            path.addAll(pathList);
            adapter.notifyDataSetChanged();


        }
    }
}
