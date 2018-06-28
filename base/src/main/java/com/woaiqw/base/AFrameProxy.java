package com.woaiqw.base;

import com.woaiqw.base.network.OkHttpHelper;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by haoran on 2018/6/28.
 */

public class AFrameProxy implements IProxy {

    private AFrameBinder binder;
    private Retrofit retrofit;

    private AFrameProxy() {
    }

    private void initRetrofit() {
        String HOST_MAPPING = "/api/";
        retrofit = new Retrofit.Builder().client(OkHttpHelper.getInstance().getClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(binder.getServerHost() + HOST_MAPPING).build();
    }


    public AFrameBinder getBinder() {
        return binder;
    }


    private static class Holder {
        private static final AFrameProxy IN = new AFrameProxy();
    }

    public static AFrameProxy getInstance() {
        return Holder.IN;
    }

    @Override
    public void initAFrame(AFrameBinder binder) {
        this.binder = binder;
        initRetrofit();
    }

    @Override
    public <T> T createService() {
        return (T) retrofit.create(binder.getApiService());
    }
}
