package com.woaiqw.aframe.model;

import com.woaiqw.aframe.IApiService;
import com.woaiqw.aframe.bean.CardListBean;
import com.woaiqw.aframe.contract.MainContract;
import com.woaiqw.aframe.utils.RxUtils;
import com.woaiqw.base.AFrameProxy;
import com.woaiqw.base.mvp.IBaseModel;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Created by haoran on 2018/6/28.
 */

public class MainModel implements MainContract.IMainModel {

    public static IBaseModel newInstance() {
        return new MainModel();
    }

    @Override
    public Observable<List<CardListBean.CardBean>> getCardList() {
        return ((IApiService) AFrameProxy.getInstance().createService()).getCardList("111", "0","2").compose(RxUtils.<CardListBean>transform()).map(new Function<CardListBean, List<CardListBean.CardBean>>() {
            @Override
            public List<CardListBean.CardBean> apply(CardListBean cardListBean) throws Exception {
                return cardListBean.getCardList();
            }
        });
    }
}
