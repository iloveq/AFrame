package com.woaiqw.base;

import retrofit2.Retrofit;

import static com.woaiqw.base.utils.AUtils.validateAFrameBinderStatus;


/**
 * Created by haoran on 2018/6/28.
 */

public class AFrameProxy implements IProxy {

    private AFrameBinder binder;
    private Retrofit retrofit;

    private AFrameProxy() {
    }

    private void initRetrofit() {

        retrofit = new Retrofit.Builder().client(binder.getOkHttpClient())
                .addConverterFactory(binder.getConverterFactory())
                .addCallAdapterFactory(binder.getCallAdapterFactory())
                .baseUrl(binder.getServerHost()).build();

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
        validateAFrameBinderStatus(binder);
        this.binder = binder;
        initRetrofit();
    }

    @Override
    public <T> T createService() {
        return (T) retrofit.create(binder.getApiService());
    }
}
