package com.woaiqw.aframe;

import com.woaiqw.aframe.config.SpConstants;
import com.woaiqw.base.AFrameBinder;
import com.woaiqw.base.AFrameProxy;
import com.woaiqw.base.common.hock.BaseHockApp;
import com.woaiqw.base.network.OkHttpAdapter;
import com.woaiqw.base.network.core.HttpUtils;
import com.woaiqw.base.network.utils.HttpsUtils;
import com.woaiqw.base.network.utils.OkHttpHelper;
import com.woaiqw.base.utils.SPUtils;

import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by haoran on 2018/5/13.
 */

public class App extends BaseHockApp {
    @Override
    public void onCreate() {
        super.onCreate();
        AFrameProxy.getInstance().initAFrame(new AFrameBinder() {
            @Override
            public Class getApiService() {
                return IApiService.class;
            }

            @Override
            public OkHttpClient getOkHttpClient() {
                return OkHttpHelper.getInstance().getClient();
            }

            @Override
            public String getServerHost() {
                return "http://gank.io/api/data/";
            }


            @Override
            public Converter.Factory getConverterFactory() {
                return GsonConverterFactory.create();
            }

            @Override
            public CallAdapter.Factory getCallAdapterFactory() {
                return RxJava2CallAdapterFactory.create();
            }
        });
        SPUtils.getInstance().put(SpConstants.CONFIG,"test");

        HttpUtils.get().setAdapter(new OkHttpAdapter());

    }
}
