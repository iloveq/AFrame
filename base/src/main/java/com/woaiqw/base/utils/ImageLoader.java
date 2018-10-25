package com.woaiqw.base.utils;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.io.File;

/**
 * Created by haoran on 2017/4/20.
 * 图片加载
 * Glide的二次封装
 */

public class ImageLoader {

    private static float defaultScale = 0.75f;

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

        loadImage(context, imageView, imageUrl);

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
        loadImageWithPlaceHolder(context, imageView, imageUrl, placeholderRes, errorRes);

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
        loadCircleImage(context, imageView, imageUrl);

    }


    /**
     * 直接加载图片(没有占位图等其他处理，仅仅是加载一张图片)
     *
     * @param context   Context
     * @param imageView ImageView
     * @param imageUrl  图片地址
     */
    public static void loadImage(Context context, ImageView imageView, String imageUrl) {
        try {
            GlideApp.with(context)
                    .load(imageUrl)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .into(imageView);
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
    public static void loadImageWithPlaceHolder(Context context, ImageView imageView, String imageUrl, @DrawableRes int placeholderRes, @DrawableRes int errorRes) {
        try {
            GlideApp.with(context)
                    .load(imageUrl)
                    .placeholder(placeholderRes)
                    .error(errorRes == 0 ? placeholderRes : errorRes)
                    .dontAnimate()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView);
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
    public static void loadCircleImage(Context context, ImageView imageView, String imageUrl) {
        try {
            GlideApp.with(context)
                    .load(imageUrl)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .dontAnimate()
                    .transform(new CircleImageTransform())
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    public static void loadImageWithSize(Context context, ImageView imageView, String imageUrl, int width, int height, float scale) {
        try {
            GlideApp.with(context)
                    .load(imageUrl)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .thumbnail(scale == 0 ? defaultScale : scale)
                    .override(width, height)
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void loadImageWithSize(Context context, ImageView imageView, File file, int width, int height, float scale) {
        try {
            GlideApp.with(context)
                    .load(file)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .thumbnail(scale == 0 ? defaultScale : scale)
                    .override(width, height)
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void loadImageWithSize(Context context, ImageView imageView, Uri uri, int width, int height, float scale) {
        try {
            GlideApp.with(context)
                    .load(uri)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .thumbnail(scale == 0 ? defaultScale : scale)
                    .override(width, height)
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void loadImageWithUri(Context context, ImageView imageView, Uri uri) {
        try {
            GlideApp.with(context)
                    .load(uri)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void loadImageWithPath(Context context, ImageView imageView, String path) {
        try {
            GlideApp.with(context)
                    .load(path)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void loadImageWithFile(Context context, ImageView imageView, File file) {
        try {
            GlideApp.with(context)
                    .load(file)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
