package com.woaiqw.base.utils;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;

/**
 * Created by haoran on 2018/4/2.
 */

@GlideModule
public class GlideAppModule extends AppGlideModule {
    @SuppressLint("CheckResult")
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        final RequestOptions defaultOptions = new RequestOptions();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            assert activityManager != null;
            defaultOptions.format(activityManager.isLowRamDevice() ? DecodeFormat.PREFER_RGB_565 : DecodeFormat.PREFER_ARGB_8888);
        } else {
            defaultOptions.format(DecodeFormat.PREFER_RGB_565);
        }
        File cache = new File(FileHelper.get().getCache(), "glide");
        String path = cache.getAbsolutePath();
        builder.setDiskCache(new DiskLruCacheFactory(path, 10 * 1024 * 1024));
        builder.setDefaultRequestOptions(defaultOptions);
    }

    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }

}
