package com.woaiqw.aframe.utils;


import com.woaiqw.aframe.bean.BaseResult;

import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by haoran on 2017/10/30.
 */

public final class RxUtils {

    private RxUtils() {
        throw new IllegalStateException(" cannot new this object ");
    }


    public static <T> ObservableTransformer<T, T> io2main() {
        return upstream -> upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }


    public static <T> ObservableTransformer<BaseResult<T>, T> transform() {
        return upstream -> upstream.map(new GsonFactoryFunc<>()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).unsubscribeOn(Schedulers.io());
    }

    public static class GsonFactoryFunc<T> implements Function<BaseResult<T>, T> {

        @Override
        public T apply(BaseResult<T> tBaseResult) {
            if (!tBaseResult.isSuccess())
                throw new ApiException(tBaseResult.getCode(),tBaseResult.getMsg());
            return tBaseResult.getData();

        }

    }
}



