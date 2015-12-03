package com.yancy.imageselector.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Yancy on 2015/12/2.
 */
public class FileUtils {

    private final static String TAG = "FileUtils";

    private final static String PATTERN = "yyyyMMddHHmmss";


    public static File createTmpFile(Context context) {

        String timeStamp = new SimpleDateFormat(PATTERN, Locale.CHINA).format(new Date());

        String fileName = "IMAGE_" + timeStamp;

        String externalStorageState = Environment.getExternalStorageState();

        File dir = new File(Environment.getExternalStorageDirectory() + "/temp/pictures");
        if (!dir.exists()) {
            dir.mkdirs();
        }

        if (externalStorageState.equals(Environment.MEDIA_MOUNTED)) {
//            File pic = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            return new File(dir, fileName + ".jpg");
        } else {
            File cacheDir = context.getCacheDir();
            return new File(cacheDir, fileName + ".jpg");
        }

    }


}