# Android Image Selector
 

## Demo
 
 #### [中文文档](https://github.com/YancyYe/ImageSelector/blob/master/README-cn.md)
 
 
![](https://github.com/YancyYe/ImageSelector/blob/master/resource/ImageSelector.gif)

[Download Apk](https://github.com/YancyYe/ImageSelector/blob/master/resource/app-debug.apk)
 
## Usage

### Step 1

#### Gradle

```groovy
dependencies {
        compile 'com.android.support:appcompat-v7:22.2.1'
        compile 'com.android.support:support-v4:22.2.1'
        compile 'com.yancy.imageselector:imageselector:1.0.0'
}
```



### Step 2

Add permissions (if necessary) to your `AndroidManifest.xml`

```xml
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />

<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />

<uses-permission android:name="android.permission.MOUNT_UNMOUNT_FILESYSTEMS" />



<application>

    <activity
        android:name="com.yancy.imageselector.ImageSelectorActivity"
        android:configChanges="orientation|screenSize" />
        
</application>

```


### Step 3

Add the Code to your Activity:
 
```java
private static int REQUEST_IMAGE = 1;

    Intent intent = new Intent(MainActivity.this, ImageSelectorActivity.class);  
    
    intent.putExtra(ImageSelectorActivity.EXTRA_SHOW_CAMERA, true);     // Capturing Photos
    
    intent.putExtra(ImageSelectorActivity.EXTRA_SELECT_COUNT, 9);      // Max Picture Number
    
    /**
     * Setting Pattern
     * Radio        :    ImageSelectorActivity.MODE_SINGLE
     * MultiSelect  :    ImageSelectorActivity.MODE_MULTI
     */
    intent.putExtra(ImageSelectorActivity.EXTRA_SELECT_MODE, ImageSelectorActivity.MODE_MULTI);
    
    startActivityForResult(intent, REQUEST_IMAGE);

```        
 
Add the Code to your onActivityResult:
 
```java
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE && resultCode == RESULT_OK && data != null) {
        
            // Get Image Path List
            List<String> pathList = data.getStringArrayListExtra(ImageSelectorActivity.EXTRA_RESULT);

            for (String path : pathList) {
                Log.i("ImagePathList", path);
            }

        }
    }
```

[Code example](https://github.com/YancyYe/ImageSelector/blob/master/app/src/main/java/com/yancy/imageselectordemo/MainActivity.java)
 
====
 

## Thanks

- [Glide](https://github.com/bumptech/glide)

##About me
 
I am a student in mainland China. I love Google, love Android, love everything that is interesting. If you get any problems when using this library or you have an internship opportunity, please feel free to [email me](mailto:yancy_world@outlook.com). :smiley:
