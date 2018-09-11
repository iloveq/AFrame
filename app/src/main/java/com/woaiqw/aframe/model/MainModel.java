package com.woaiqw.aframe.model;

import com.woaiqw.aframe.IApiService;
import com.woaiqw.aframe.bean.CardListBean;
import com.woaiqw.aframe.contract.MainContract;
import com.woaiqw.aframe.utils.RxUtils;
import com.woaiqw.base.AFrameProxy;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by haoran on 2018/6/28.
 */

public class MainModel implements MainContract.IMainModel {

    @Override
    public Observable<List<CardListBean.CardBean>> getCardList() {
        return AFrameProxy.getInstance().<IApiService>createService().getCardList("111", "0", "0").compose(RxUtils.transform()).map(CardListBean::getCardList);
    }
}
