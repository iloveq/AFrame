package com.woaiqw.base.common;

import android.app.Application;

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
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        System.gc();
        System.runFinalization();
    }
}
