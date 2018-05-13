package com.woaiqw.base.widget;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.woaiqw.base.widget.NetworkState.STATE_EMPTY;
import static com.woaiqw.base.widget.NetworkState.STATE_LOADING;
import static com.woaiqw.base.widget.NetworkState.STATE_NETWORK_ERROR;
import static com.woaiqw.base.widget.NetworkState.STATE_NO_NETWORK_RETRY;
import static com.woaiqw.base.widget.NetworkState.STATE_SUCCESS;

/**
 * Created by haoran on 2018/5/10.
 */
@Retention(RetentionPolicy.SOURCE)
@IntDef({STATE_SUCCESS, STATE_LOADING, STATE_NETWORK_ERROR, STATE_NO_NETWORK_RETRY, STATE_EMPTY})
public @interface NetworkState {
    int STATE_SUCCESS = 0;
    int STATE_LOADING = 1;
    int STATE_NETWORK_ERROR = 2;
    int STATE_NO_NETWORK_RETRY = 3;
    int STATE_EMPTY = 4;
}
