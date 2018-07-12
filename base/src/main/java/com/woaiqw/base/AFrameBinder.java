package com.woaiqw.base;

import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;

/**
 * Created by haoran on 2018/6/28.
 */

public interface AFrameBinder {

    Class getApiService();
    OkHttpClient getOkHttpClient();
    String getServerHost();
    Converter.Factory getConverterFactory();
    CallAdapter.Factory getCallAdapterFactory();
}
