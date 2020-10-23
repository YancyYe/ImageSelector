### 本项目已停止维护

### 本项目的背景是当时没有十分好用的图片选择的库，所以自己写了一个。

### 目前已经找到一个功能很齐全的库 [PictureSelector](https://github.com/LuckSiege/PictureSelector) 推荐大家使用



# ImageSelector 简介


####近期将会重构代码，修复bug，感谢各位使用者的支持[重构地址](https://github.com/YancyYe/GalleryPick)
Android自定义相册，实现了拍照、图片选择（单选/多选）、ImageLoader无绑定 任由开发者选择

###[GitHub 项目地址](https://github.com/YancyYe/ImageSelector)

![多选](https://raw.githubusercontent.com/YancyYe/ImageSelector/master/resource/image_1.png) 
![截图](https://raw.githubusercontent.com/YancyYe/ImageSelector/master/resource/image_2.png)

[Download Apk](https://raw.githubusercontent.com/YancyYe/ImageSelector/master/resource/app-debug.apk)
 

## ImageSelector 优点
* UI重改
* 所有功能可配置
* 解决OOM情况
* 图片多选、单选
* 支持裁剪功能


## Gif展示


![单选截图](https://raw.githubusercontent.com/YancyYe/ImageSelector/master/resource/gif_1.gif) 
![多选](https://raw.githubusercontent.com/YancyYe/ImageSelector/master/resource/gif_2.gif)

## 版本说明
### 1.3.0 
* 合并 由 xxxifan  提供的优化方案，配置只需要配置一次，需要用的地方可以直接 open。
* 修改如果手机中没有图片，选择图片夹闪退的问题
* 隐藏调用该图片选择器时，所拍摄的照片和裁剪的图片，改善选择图片时存在的垃圾图片。
* 修改最低版本兼容，由原来的 API 15 降到 API 14，兼容 Android 4.0 以上系统。
 
## 使用说明

### 步骤一：

#### 通过Gradle抓取

```groovy
dependencies {
    compile 'com.yancy.imageselector:imageselector:1.3.3'
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

### 步骤四：

#### 配置 `ImageConfig`

##### UI 视图配置

```java
 ImageConfig imageConfig
      = new ImageConfig.Builder(new GlideLoader())
     // 如果在 4.4 以上，则修改状态栏颜色 （默认黑色）
     .steepToolBarColor(getResources().getColor(R.color.blue))
     // 标题的背景颜色 （默认黑色）
     .titleBgColor(getResources().getColor(R.color.blue))
     // 提交按钮字体的颜色  （默认白色）
     .titleSubmitTextColor(getResources().getColor(R.color.white))
     // 标题颜色 （默认白色）
     .titleTextColor(getResources().getColor(R.color.white))
     .build();
```

##### 多选
```java
 ImageConfig imageConfig
        = new ImageConfig.Builder(new GlideLoader())
        .steepToolBarColor(getResources().getColor(R.color.blue))
        .titleBgColor(getResources().getColor(R.color.blue))
        .titleSubmitTextColor(getResources().getColor(R.color.white))
        .titleTextColor(getResources().getColor(R.color.white))
        // 开启多选   （默认为多选） 
        .mutiSelect()
        // 多选时的最大数量   （默认 9 张）
        .mutiSelectMaxSize(9)
        // 开启拍照功能 （默认关闭）
        .showCamera()
        // 已选择的图片路径
        .pathList(path)
        // 拍照后存放的图片路径（默认 /temp/picture） （会自动创建）
        .filePath("/ImageSelector/Pictures")
        .build();


ImageSelector.open(MainActivity.this, imageConfig);   // 开启图片选择器
```

##### 单选
```java
 ImageConfig imageConfig
        = new ImageConfig.Builder(new GlideLoader())
        .steepToolBarColor(getResources().getColor(R.color.blue))
        .titleBgColor(getResources().getColor(R.color.blue))
        .titleSubmitTextColor(getResources().getColor(R.color.white))
        .titleTextColor(getResources().getColor(R.color.white))
        // 开启单选   （默认为多选） 
        .singleSelect()
        // 开启拍照功能 （默认关闭）
        .showCamera()
        // 拍照后存放的图片路径（默认 /temp/picture） （会自动创建）
        .filePath("/ImageSelector/Pictures")
        .build();


ImageSelector.open(MainActivity.this, imageConfig);   // 开启图片选择器
```

##### 单选1：1 便捷截图
```java
 ImageConfig imageConfig
        = new ImageConfig.Builder(new GlideLoader())
        .steepToolBarColor(getResources().getColor(R.color.blue))
        .titleBgColor(getResources().getColor(R.color.blue))
        .titleSubmitTextColor(getResources().getColor(R.color.white))
        .titleTextColor(getResources().getColor(R.color.white))
        // (截图默认配置：关闭    比例 1：1    输出分辨率  500*500)
        .crop()  
        // 开启单选   （默认为多选） 
        .singleSelect()
        // 开启拍照功能 （默认关闭）
        .showCamera()
        // 拍照后存放的图片路径（默认 /temp/picture） （会自动创建）
        .filePath("/ImageSelector/Pictures")
        .build();


ImageSelector.open(MainActivity.this, imageConfig);   // 开启图片选择器
```

##### 单选自定义截图
```java
 ImageConfig imageConfig
        = new ImageConfig.Builder(new GlideLoader())
        .steepToolBarColor(getResources().getColor(R.color.blue))
        .titleBgColor(getResources().getColor(R.color.blue))
        .titleSubmitTextColor(getResources().getColor(R.color.white))
        .titleTextColor(getResources().getColor(R.color.white))
        // (截图默认配置：关闭    比例 1：1    输出分辨率  500*500)
        .crop(1, 2, 500, 1000) 
        // 开启单选   （默认为多选） 
        .singleSelect()
        // 开启拍照功能 （默认关闭）
        .showCamera()
        // 拍照后存放的图片路径（默认 /temp/picture） （会自动创建）
        .filePath("/ImageSelector/Pictures")
        .build();


ImageSelector.open(MainActivity.this, imageConfig);   // 开启图片选择器
```
### 步骤五：
 
在  `onActivityResult` 中获取选中的照片路径 数组 :
 
```java
@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
 super.onActivityResult(requestCode, resultCode, data);
  if (requestCode == ImageSelector.IMAGE_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
  
    // Get Image Path List
     List<String> pathList = data.getStringArrayListExtra(ImageSelectorActivity.EXTRA_RESULT);

     for (String path : pathList) {
         Log.i("ImagePathList", path);
     }
  }
}
```

[代码示例](https://github.com/YancyYe/ImageSelector/blob/master/app/src/main/java/com/yancy/imageselectordemo/MainActivity.java)

## 历史版本说明
### 1.3.0 
* 合并 由 xxxifan  提供的优化方案，现在在fragment 中也可以进行调用
* 修改如果手机中没有图片，选择图片夹闪退的问题
* 隐藏调用该图片选择器时，所拍摄的照片和裁剪的图片，改善选择图片时存在的垃圾图片。
* 修改最低版本兼容，由原来的 API 15 降到 API 14，兼容 Android 4.0 以上系统。

### 1.2.0 
* 新增截图功能

### 1.1.1 
* 修改APP名被覆盖的bug

### 1.1.0 
* 优化代码，开放部分UI接口

### 1.0.0 
* 选择图片功能
 
## 关于作者
* Email: [yancy_world@outlook.com](mailto:yancy_world@outlook.com)

