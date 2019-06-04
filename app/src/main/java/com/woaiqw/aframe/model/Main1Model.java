package com.woaiqw.aframe.model;


import com.woaiqw.aframe.contract.MainContract;
import com.woaiqw.aframe.model.jsonParser.GetCardListJsonParser;
import com.woaiqw.base.network.core.HttpUtils;
import com.woaiqw.base.network.core.RequestCtx;
import com.woaiqw.base.network.internel.Callback;



/**
 * Created by haoran on 2018/6/28.
 */

public class Main1Model implements MainContract.IMain1Model {

    @Override
    public void getCardList(int index, Callback callback) {
        RequestCtx ctx = new RequestCtx.Builder()
                .setUrl("http://gank.io/api/data/福利/" + "10/" + index)
                .setParser(new GetCardListJsonParser())
                .setCallback(callback).build();
        HttpUtils.get().req(ctx);
    }
}
