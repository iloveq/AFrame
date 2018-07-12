package com.woaiqw.base.utils;

import com.woaiqw.base.AFrameBinder;

/**
 * Created by haoran on 2018/7/12.
 */

public class AUtils {

    private AUtils() {
        throw new IllegalStateException(" cannot to new the Object ");
    }

    public static void validateAFrameBinderStatus(AFrameBinder binder) {
        if (binder == null)
            throw new NullPointerException(" binder must be initialized ");

        if (binder.getApiService() == null || binder.getOkHttpClient() == null || binder.getServerHost() == null || binder.getCallAdapterFactory() == null || binder.getConverterFactory() == null)
            throw new IllegalStateException(" AFrame config error exception ");
    }
}
