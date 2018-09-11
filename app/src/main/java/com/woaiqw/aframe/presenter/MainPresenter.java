package com.woaiqw.aframe.presenter;

import com.woaiqw.aframe.contract.MainContract;
import com.woaiqw.aframe.model.MainModel;
import com.woaiqw.base.mvp.BasePresenter;

/**
 * Created by haoran on 2018/6/28.
 */

public class MainPresenter extends BasePresenter<MainContract.IMainView, MainModel> implements MainContract.IMainPresenter {


    @Override
    public void getCardList() {
        checkViewAttached();
        mView.showLoading();
        mDisposable.add(mModel.getCardList().subscribe(cardBeanList -> {
            mView.hideLoading();
            if (cardBeanList == null || cardBeanList.size() == 0) {
                mView.showEmptyDataView();
            } else {
                mView.showCardList(cardBeanList);
            }
        }, throwable -> mView.onError(throwable.getMessage())));
    }

}
