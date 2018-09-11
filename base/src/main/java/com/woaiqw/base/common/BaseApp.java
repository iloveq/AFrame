package com.woaiqw.base.common;

import android.app.Application;

import com.woaiqw.base.utils.FileHelper;

/**
 * Created by haoran on 2018/5/11.
 */

public class BaseApp extends Application {

    private static BaseApp baseApp;

    public static Application getApplication() {
        return baseApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        baseApp = this;
        FileHelper.initialize(this);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}
