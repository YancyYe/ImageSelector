# ImageSelector 简介
Android自定义相册，实现了拍照、图片选择（单选/多选）、ImageLoader无绑定 任由开发者选择

## Demo
 
#### [英文版文档](https://github.com/yancyworld/ImageSelector/blob/master/README.md)
 
 
![](https://github.com/yancyworld/ImageSelector/blob/master/resource/ImageSelector.gif)

[Download Apk](https://raw.githubusercontent.com/YancyYe/ImageSelector/master/resource/app-debug.apk)
 

## 更新内容
* UI重改
* 所有功能可配置
* 解决OOM情况
* 图片手动选择
* 支持汉语和英语


## 截图展示
![](https://github.com/YancyYe/ImageSelector/blob/master/resource/ImageSelector.png)

 
## 使用说明

### 步骤一：

#### 通过Gradle抓取

```groovy
dependencies {
    compile 'com.yancy.imageselector:imageselector:1.1.0'
}
```



### 步骤二：

在 `AndroidManifest.xml` 中 添加 如下权限

```xml
<!-- 从sdcard中读取数据的权限 -->
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
<!-- 往sdcard中写入数据的权限 -->
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />

```


### 步骤三：

#####创建 图片加载器 (其中可以按照 喜好  使用不同的 第三方图片加载框架 以下为Glide示例)


```java
public class GlideLoader implements com.yancy.imageselector.ImageLoader {

    @Override
    public void displayImage(Context context, String path, ImageView imageView) {
        Glide.with(context)
                .load(path)
                .placeholder(com.yancy.imageselector.R.mipmap.imageselector_photo)
                .centerCrop()
                .into(imageView);
    }

}

```    

##### 配置ImageSelector

```java
 ImageConfig imageConfig
        = new ImageConfig.Builder(MainActivity.this , new GlideLoader())
        // 如果在 4.4 以上，则修改状态栏颜色 （默认黑色）
        .steepToolBarColor(getResources().getColor(R.color.blue))
        // 标题的背景颜色 （默认黑色）
        .titleBgColor(getResources().getColor(R.color.blue))
        // 提交按钮字体的颜色  （默认白色）
        .titleSubmitTextColor(getResources().getColor(R.color.white))
        // 标题颜色 （默认白色）
        .titleTextColor(getResources().getColor(R.color.white))
        // 开启多选   （默认为多选）  (单选 为 singleSelect)
        .mutiSelect()
        // 多选时的最大数量   （默认 9 张）
        .mutiSelectMaxSize(9)
        // 已选择的图片路径
        .pathList(path)
        // 拍照后存放的图片路径（默认 /temp/picture）
        .filePath("/ImageSelector/Pictures")
        // 开启拍照功能 （默认开启）
        .showCamera()
        .build();


ImageSelector.open(imageConfig);   // 开启图片选择器
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

[代码示例](https://github.com/YancyYe/ImageSelector/blob/master/app/src/main/java/com/yancy/imageselectordemo/MainActivity.java)
 
====
 
## 关于作者
* QQ: 297555818
* Email: [yancy_world@outlook.com](mailto:yancy_world@outlook.com)

## Thanks

- [Glide](https://github.com/bumptech/glide)

##About me
 
I am a student in mainland China. I love Google, love Android, love everything that is interesting. If you get any problems when using this library or you have an internship opportunity, please feel free to [email me](mailto:yancy_world@outlook.com). :smiley:

## License
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
