package com.woaiqw.aframe.presenter;

import com.woaiqw.aframe.contract.MainContract;
import com.woaiqw.aframe.model.MainModel;
import com.woaiqw.base.mvp.BasePresenter;

/**
 * Created by haoran on 2018/6/28.
 */

public class MainPresenter extends BasePresenter<MainContract.IMainView, MainModel> implements MainContract.IMainPresenter {


    @Override
    public void getCardList(int pageNum) {
        checkViewAttached();
        view.showLoading("");
        disposable.add(model.getCardList(pageNum).subscribe(cardBeanList -> {
            view.closeLoading();
            if (cardBeanList == null || cardBeanList.size() == 0) {
                view.showEmpty();
            } else {
                view.renderPage(cardBeanList);
            }
        }, throwable -> view.showError(throwable.getMessage())));
    }

}
