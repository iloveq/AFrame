package com.woaiqw.base.utils;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * Created by woaigmz on 2018/1/17.
 */

public class WeakHandler extends Handler {

    private WeakReference<Callback> mWeakReference;

    public WeakHandler(Callback callback) {
        mWeakReference = new WeakReference<>(callback);
    }

    public WeakHandler(Callback callback, Looper looper) {
        super(looper);
        mWeakReference = new WeakReference<>(callback);
    }

    @Override
    public void handleMessage(Message msg) {
        if (mWeakReference != null && mWeakReference.get() != null) {
            Callback callback = mWeakReference.get();
            callback.handleMessage(msg);
        }
    }

}
