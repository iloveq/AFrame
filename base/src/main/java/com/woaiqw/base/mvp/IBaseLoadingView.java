package com.woaiqw.base.mvp;

/**
 * Created by haoran on 2019/5/30.
 */
public interface IBaseLoadingView extends IBaseView {

    void showLoading(String msg);

    void closeLoading();

    void renderPage(Object o);

    void reload();

    void showError(String msg);

    void showDisconnect();

}
