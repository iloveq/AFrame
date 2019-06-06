package com.woaiqw.aframe.model;


import android.util.Log;

import com.woaiqw.aframe.bean.CardBean;
import com.woaiqw.aframe.contract.MainContract;
import com.woaiqw.base.network.core.HttpUtils;
import com.woaiqw.base.network.core.RequestCtx;
import com.woaiqw.base.network.internel.Callback;
import com.woaiqw.base.network.internel.Parser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by haoran on 2018/6/28.
 */

public class Main1Model implements MainContract.IMain1Model {

    @Override
    public void getCardList(int index, Callback callback) {
        RequestCtx ctx = new RequestCtx.Builder()
                .setUrl("http://gank.io/api/data/福利/" + "10/" + index)
                .build();
        HttpUtils.get().req(ctx,new GetCardListJsonParser(),callback);
    }

    static class GetCardListJsonParser implements Parser {
        @Override
        public List<CardBean> parse(String jsonStr)throws Exception {
            Log.e("jsonStr",jsonStr);
            List<CardBean> list = new ArrayList<>();
            JSONObject data = new JSONObject(jsonStr);
            JSONArray array = data.getJSONArray("results");
            for (int i = 0; i < array.length(); i++) {
                CardBean bean = new CardBean();
                bean.setImgUrl(array.getJSONObject(i).getString("url"));
                list.add(bean);
            }
            return list;
        }
    }

}
