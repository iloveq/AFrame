package com.woaiqw.base.widget;

/**
 * Created by haoran on 2018/5/11.
 */

public interface NetworkStateAction {
    void showSuccess();
    void showLoading();
    void showError();
    void showNoNetworkRetry();
    void showEmpty();
}
