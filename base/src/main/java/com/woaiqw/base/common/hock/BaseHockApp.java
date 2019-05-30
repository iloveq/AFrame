package com.woaiqw.base.common.hock;

import android.app.Application;

import com.woaiqw.base.utils.FileHelper;

/**
 * Created by haoran on 2018/5/11.
 * 为了做一些公共操作
 */

public class BaseHockApp extends Application {

    private static BaseHockApp baseApp;

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
