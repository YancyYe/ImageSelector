# Android Image Selector
 

## Demo
 
 #### [English Doc](https://github.com/yancyworld/ImageSelector/blob/master/README.md)
 
 
![](https://github.com/yancyworld/ImageSelector/blob/master/resource/ImageSelector.gif)

[Download Apk](https://github.com/yancyworld/ImageSelector/blob/master/resource/app-debug.apk)
 
## 使用说明

### 步骤一：

#### 在 Gradle 中应用 imageselector 依赖

```groovy
dependencies {
        compile 'com.android.support:appcompat-v7:22.2.1'
        compile 'com.android.support:support-v4:22.2.1'
        
        compile 'com.yancy.imageselector:imageselector:1.0.0'
        
}
```



### 步骤二：

在 `AndroidManifest.xml` 中 添加 如下权限

```xml
<!-- 从sdcard中读取数据的权限 -->
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
<!-- 往sdcard中写入数据的权限 -->
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
<!-- 在sdcard中创建/删除文件的权限 -->
<uses-permission android:name="android.permission.MOUNT_UNMOUNT_FILESYSTEMS" />


```

在 `AndroidManifest.xml` 中  `application` 节点下  添加 以下 Activity

```xml
<activity
    android:name="com.yancy.imageselector.ImageSelectorActivity"
    android:configChanges="orientation|screenSize" />
    

```


### 步骤三：

将以下代码添加到 您需要跳转的 位置中
 
```java
private static int REQUEST_IMAGE = 1;


    Intent intent = new Intent(MainActivity.this, ImageSelectorActivity.class);  
    
    intent.putExtra(ImageSelectorActivity.EXTRA_SHOW_CAMERA, true);     // 是否开启相机  默认 开启
    
    intent.putExtra(ImageSelectorActivity.EXTRA_SELECT_COUNT, 9);      //  如果开启多选，则配置可选图片的最大数量 默认 9 张
    
    /**
     * 配置模式
     * 单选  :    ImageSelectorActivity.MODE_SINGLE
     * 多选  :    ImageSelectorActivity.MODE_MULTI
     */
    intent.putExtra(ImageSelectorActivity.EXTRA_SELECT_MODE, ImageSelectorActivity.MODE_MULTI);     // 多选
    
    startActivityForResult(intent, REQUEST_IMAGE);

```        
 
在  `onActivityResult` 中获取选中的照片路径 数组 :
 
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

[代码示例](https://github.com/yancyworld/ImageSelector/blob/master/app/src/main/java/com/yancy/imageselectordemo/MainActivity.java)
 
====
 

## Thanks

- [Glide](https://github.com/bumptech/glide)

##About me
 
I am a student in mainland China. I love Google, love Android, love everything that is interesting. If you get any problems when using this library or you have an internship opportunity, please feel free to [email me](mailto:yancy_world@outlook.com). :smiley:
