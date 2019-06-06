package com.woaiqw.aframe.model.jsonParser;

import android.util.Log;

import com.woaiqw.aframe.bean.CardBean;
import com.woaiqw.base.network.internel.Parser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GetCardListJsonParser implements Parser {
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
