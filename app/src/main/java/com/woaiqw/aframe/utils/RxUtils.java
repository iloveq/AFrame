package com.woaiqw.aframe.utils;



import com.woaiqw.aframe.bean.BaseResult;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by haoran on 2017/10/30.
 */

public final class RxUtils {

    private RxUtils() {
    }


    public static <T> ObservableTransformer<T, T> io2main() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }


    public static <T> ObservableTransformer<BaseResult<T>, T> transform() {
        return new ObservableTransformer<BaseResult<T>, T>() {
            @Override
            public ObservableSource<T> apply(Observable<BaseResult<T>> upstream) {
                return upstream.map(new GsonFactoryFunc<T>()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).unsubscribeOn(Schedulers.io());
            }
        };
    }

    public static class GsonFactoryFunc<T> implements Function<BaseResult<T>, T> {

        @Override
        public T apply(BaseResult<T> tBaseResult) throws Exception {
            if (!tBaseResult.isSuccess())
                throw new ApiException(tBaseResult.getCode(),tBaseResult.getMsg());
            return tBaseResult.getData();

        }

    }
}



