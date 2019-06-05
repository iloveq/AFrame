package com.woaiqw.aframe.presenter;

import android.util.Log;

import com.woaiqw.aframe.contract.MainContract;
import com.woaiqw.aframe.model.Main1Model;
import com.woaiqw.base.mvp.BasePresenter;
import com.woaiqw.base.network.internel.Callback;

/**
 * Created by haoran on 2018/6/28.
 */

public class Main1Presenter extends BasePresenter<MainContract.IMainView, Main1Model> implements MainContract.IMainPresenter {


    @Override
    public void getCardList(int pageNum) {
        checkViewAttached();
        view.showLoading("");
        model.getCardList(pageNum, new Callback() {
            @Override
            public void then(Object o) {
                Log.e("threadName - then",Thread.currentThread().getName());
                view.closeLoading();
                view.renderPage(o);
            }

            @Override
            public void error(Object o) {
                view.closeLoading();
                view.showError((String) o);
            }
        });
    }

}
