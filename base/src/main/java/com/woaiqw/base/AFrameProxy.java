package com.woaiqw.base;

import retrofit2.Retrofit;

/**
 * Created by haoran on 2018/6/28.
 */

public class AFrameProxy implements IProxy {

    private AFrameBinder binder;
    private Retrofit retrofit;

    private AFrameProxy() {
    }

    private void initRetrofit() {
        validateBinderStatus();
        retrofit = new Retrofit.Builder().client(binder.getOkHttpClient())
                .addConverterFactory(binder.getConverterFactory())
                .addCallAdapterFactory(binder.getCallAdapterFactory())
                .baseUrl(binder.getServerHost()).build();
    }

    private void validateBinderStatus() {
        if (binder==null){
            throw new NullPointerException("binder must be initialized");
        }
        if (binder.getApiService() == null || binder.getOkHttpClient() == null || binder.getServerHost() == null || binder.getCallAdapterFactory() == null || binder.getConverterFactory() == null)
            throw new IllegalStateException("AFrame config error exception");
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
