package com.woaiqw.aframe.utils;



import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.functions.Consumer;
import retrofit2.HttpException;


/**
 * Created by haoran on 2017/11/30.
 */

public abstract class ApiErrorConsumer implements Consumer<Throwable> {

    @Override
    public void accept(Throwable e) throws Exception {

        if (e instanceof SocketTimeoutException || e instanceof ConnectException ||
                e instanceof HttpException ||
                e instanceof UnknownHostException ||
                e instanceof InterruptedIOException) {
            onFail(ServerCode.API_ERROR_NET_WORK, ServerCode.NET_WORK_ERROR);
        } else if (e instanceof ApiException) {
            ApiException exception = (ApiException) e;
            String code = exception.getCode();
            switch (code) {
                case ServerCode.UNAUTHORIZED:
                case ServerCode.FORBIDDEN:
                case ServerCode.NOT_FOUND:
                case ServerCode.REQUEST_TIMEOUT:
                case ServerCode.GATEWAY_TIMEOUT:
                case ServerCode.INTERNAL_SERVER_ERROR:
                case ServerCode.BAD_GATEWAY:
                case ServerCode.SERVICE_UNAVAILABLE:
                    onFail(ServerCode.API_ERROR_NET_WORK, ServerCode.NET_WORK_ERROR);
                    break;
                case ServerCode.SERVICE_TOKEN_INVALID:
                    //RxBus.getDefault().post(0x1, exception.getMsg());
                    onFail(ServerCode.SERVICE_TOKEN_INVALID, ServerCode.TOKEN_INVALID);
                    break;
                default:
                    onFail(code, exception.getMsg());
            }

        } else {
            onFail(ServerCode.API_ERROR_UNKNOW, ServerCode.UNKNOW_ERROR);
        }
    }

    public abstract void onFail(String code, String message);
}
