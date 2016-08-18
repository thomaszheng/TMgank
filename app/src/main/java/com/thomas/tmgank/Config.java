package com.thomas.tmgank;

import android.graphics.Bitmap;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

/**
 * Created by thomas on 2015/10/11.
 */
public class Config {

    public static DisplayImageOptions list_options = new DisplayImageOptions.Builder()
            .showImageOnLoading(R.mipmap.pagefailed_bg)// 设置加载过程中显示的图片
            .showImageForEmptyUri(R.mipmap.pagefailed_bg)// 设置如图图片为空的时候显示的图片
            .showImageOnFail(R.mipmap.pagefailed_bg)// 设置加载失败显示的图片
            .cacheInMemory(true)// 在内存中缓存
            .cacheOnDisk(true)// 在硬盘缓存
            .considerExifParams(true)// 会识别图片的方向信息
                    //.displayer(new FadeInBitmapDisplayer(500)).build();// 渐渐显示的动画
            .displayer(new RoundedBitmapDisplayer(28)).build();//带有圆角的图片;

    // ViewPager中使用的options
    public static DisplayImageOptions pager_options = new DisplayImageOptions.Builder()
            .showImageForEmptyUri(R.mipmap.pagefailed_bg)
            .showImageOnFail(R.mipmap.pagefailed_bg)
            .resetViewBeforeLoading(true)// 在加载图片之前，清空ImageView上面的图片
            .cacheOnDisk(true).imageScaleType(ImageScaleType.EXACTLY)// 对图片进行进一步的压缩
            .bitmapConfig(Bitmap.Config.RGB_565)// 设置图片的色彩模式,是比较节省内存的模式
            .considerExifParams(true)// 会识别图片的方向信息
            .displayer(new FadeInBitmapDisplayer(500)).build();
}
