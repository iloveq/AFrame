package com.woaiqw.base.network;

import android.support.annotation.NonNull;
import android.util.Log;

import com.woaiqw.base.common.BaseApp;
import com.woaiqw.base.utils.FileHelper;
import com.woaiqw.base.utils.NetworkUtils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;


/**
 * Created by haoran on 2018/5/7.
 */

public class OkHttpHelper {

    private static volatile OkHttpHelper mInstance;

    public static OkHttpHelper getInstance() {
        if (mInstance == null) {
            synchronized (OkHttpHelper.class) {
                if (mInstance == null) {
                    mInstance = new OkHttpHelper();
                }
            }
        }
        return mInstance;
    }

    public OkHttpClient getClient() {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.connectTimeout(15, TimeUnit.SECONDS);
        client.writeTimeout(30, TimeUnit.SECONDS);
        client.readTimeout(30, TimeUnit.SECONDS);

        File apiCache = new File(FileHelper.get().getCache(), "api");

        client.addNetworkInterceptor(new CacheInterceptor());
        client.cache(new Cache(apiCache, 1024 * 1024 * 10));

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(@NonNull String message) {
                Log.d("response_body_cipher", message);
            }
        });
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        client.addInterceptor(logging);
        return client.build();
    }

    private class CacheInterceptor implements Interceptor {

        private final String pragma = "pragma";
        private final String cacheControl = "Cache-Control";
        private volatile int maxAge = 60 * 60;
        private volatile int maxStale = 60 * 60 * 24 * 28;
        private String maxStaleString = "public, only-if-cached, max-stale=" + maxStale;
        private String maxAgeString = "public, only-if-cached, max-stale=" + maxAge;

        @Override
        public Response intercept(@NonNull Chain chain) throws IOException {

            Request request = chain.request();
            if (!NetworkUtils.isNetworkAvailable(BaseApp.getApplication())) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();

                return chain.proceed(request);
            }
            Response response = chain.proceed(request);

            if (NetworkUtils.isNetworkAvailable(BaseApp.getApplication())) {
                // read from cache for 1 minute
                response.newBuilder()
                        .removeHeader(pragma)
                        .header(cacheControl, maxAgeString)
                        .build();
            } else {
                // tolerate 4-weeks stale
                response.newBuilder()
                        .removeHeader(pragma)
                        .header(cacheControl, maxStaleString)
                        .build();
            }
            return response;

        }
    }


}
