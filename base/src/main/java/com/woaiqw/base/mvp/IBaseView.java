package com.woaiqw.base.mvp;

/**
 * Created by haoran on 2017/10/30.
 */

public interface IBaseView {


    void showLoading();

    void hideLoading();

    void onError(String message);

    void showEmptyDataView();

}
