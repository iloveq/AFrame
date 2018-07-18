package com.woaiqw.base.utils;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Created by haoran on 2017/4/20.
 * 图片加载
 * Glide的二次封装
 */

public class ImageLoader {


    private ImageLoader() {
        throw new IllegalStateException(" cannot to new the Object ");
    }


    /**
     * 直接加载图片(没有占位图等其他处理，仅仅是加载一张图片)
     *
     * @param imageView ImageView
     * @param imageUrl  图片地址
     */
    public static void loadImage(ImageView imageView, String imageUrl) {
        if (imageView == null) {
            return;
        }
        Context context = imageView.getContext();
        if (context instanceof Activity) {
            if (((Activity) context).isFinishing()) {
                return;
            }
        }
        try {
            loadImage(context, imageView, imageUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    /**
     * 直接加载图片(没有占位图等其他处理，仅仅是加载一张图片)
     *
     * @param context   Context
     * @param imageView ImageView
     * @param imageUrl  图片地址
     */
    private static void loadImage(Context context, ImageView imageView, String imageUrl) {
        GlideApp.with(context)
                .load(imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(imageView);
    }

    /**
     * 加载图片，在加载过程中会显示占位图
     *
     * @param imageView ImageView
     * @param imageUrl  图片地址
     */
    public static void loadImageWithPlaceHolder(ImageView imageView, String imageUrl, @DrawableRes int placeholderRes, @DrawableRes int errorRes) {
        if (imageView == null) {
            return;
        }
        Context context = imageView.getContext();
        if (context instanceof Activity) {
            if (((Activity) context).isFinishing()) {
                return;
            }
        }
        try {
            loadImageWithPlaceHolder(context, imageView, imageUrl, placeholderRes, errorRes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 加载图片，在加载过程中会显示占位图，失败也会显示占位图
     *
     * @param context   Context
     * @param imageView ImageView
     * @param imageUrl  图片地址
     */
    private static void loadImageWithPlaceHolder(Context context, ImageView imageView, String imageUrl, @DrawableRes int placeholderRes, @DrawableRes int errorRes) {
        GlideApp.with(context)
                .load(imageUrl)
                .placeholder(placeholderRes)
                .error(errorRes)
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }

    /**
     * 加载圆形图片
     *
     * @param imageView ImageView
     * @param imageUrl  图片地址
     */
    public static void loadCircleImage(ImageView imageView, String imageUrl) {
        if (imageView == null) {
            return;
        }
        Context context = imageView.getContext();
        if (context instanceof Activity) {
            if (((Activity) context).isFinishing()) {
                return;
            }
        }
        try {
            loadCircleImage(context, imageView, imageUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 加载圆形图片
     *
     * @param context   Context
     * @param imageView ImageView
     * @param imageUrl  图片地址
     */
    private static void loadCircleImage(Context context, ImageView imageView, String imageUrl) {
        GlideApp.with(context)
                .load(imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .dontAnimate()
                .transform(new CircleImageTransform())
                .into(imageView);
    }


    /**
     * 根据特定的宽高加载图片
     *
     * @param context   Context
     * @param imageView ImageView
     * @param imageUrl  图片地址
     * @param width     图片的宽度
     * @param height    图片的高度
     */
    public static void loadImageWithSize(Context context, ImageView imageView, String imageUrl, int width, int height) {
        GlideApp.with(context)
                .load(imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .override(width, height)
                .into(imageView);
    }

    public static void loadImageWithUri(Context context, ImageView imageView, Uri uri) {
        GlideApp.with(context)
                .load(uri)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(imageView);
    }

}
