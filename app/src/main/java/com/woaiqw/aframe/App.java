package com.woaiqw.aframe;

import com.woaiqw.base.AFrameBinder;
import com.woaiqw.base.AFrameProxy;
import com.woaiqw.base.common.BaseApp;

/**
 * Created by haoran on 2018/5/13.
 */

public class App extends BaseApp {
    @Override
    public void onCreate() {
        super.onCreate();
        AFrameProxy.getInstance().initAFrame(new AFrameBinder() {
            @Override
            public String getServerHost() {
                return "localhost:8080";
            }

            @Override
            public Class getApiService() {
                return IApiService.class;
            }
        });
    }
}
