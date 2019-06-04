package com.woaiqw.aframe.contract;

import com.woaiqw.aframe.bean.CardBean;
import com.woaiqw.base.mvp.IBaseLoadingListView;
import com.woaiqw.base.mvp.IBaseModel;
import com.woaiqw.base.mvp.IPresenter;
import com.woaiqw.base.network.internel.Callback;

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

    interface IMain1Model extends IBaseModel {
        void getCardList(int index , Callback callback);
    }

    interface IMainPresenter extends IPresenter<IMainView> {
        void getCardList(int index);
    }
}
