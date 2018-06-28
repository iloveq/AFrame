package com.woaiqw.base.utils;

import android.app.Application;
import android.os.Environment;

import java.io.File;

/**
 * Created by haoran on 2017/11/10.
 */

public class FileHelper {

    private static class Holder {
        private static FileHelper IN = new FileHelper();
    }

    public static FileHelper get() {
        return Holder.IN;
    }

    private FileHelper() {

    }


    File cache;
    File userCache;

    public static void initialize(Application application) {

        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {//如果已经挂载
            get().cache = application.getExternalCacheDir();
        } else {
            get().cache = application.getCacheDir();
        }

        get().userCache = application.getFilesDir();


    }

    public File getCache() {
        return cache;
    }


    public File getUserCache() {
        return new File(userCache, "userinfo");
    }
}
