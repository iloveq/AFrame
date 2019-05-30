package com.woaiqw.aframe.contract;

import com.woaiqw.aframe.bean.CardBean;
import com.woaiqw.base.mvp.IBaseLoadingListView;
import com.woaiqw.base.mvp.IBaseModel;
import com.woaiqw.base.mvp.IPresenter;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by haoran on 2018/6/28.
 */

public interface MainContract {

    interface IMainView extends IBaseLoadingListView { }

    interface IMainModel extends IBaseModel {
        Observable<List<CardBean>> getCardList(int index);
    }

    interface IMainPresenter extends IPresenter<IMainView> {
        void getCardList(int index);
    }
}
