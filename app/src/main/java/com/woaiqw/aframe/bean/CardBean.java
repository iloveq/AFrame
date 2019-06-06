package com.woaiqw.aframe.bean;

import com.google.gson.annotations.SerializedName;


/**
 * Created by haoran on 2018/6/28.
 */

public class CardBean {


    @SerializedName("url")
    private String imgUrl;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String url) {
        this.imgUrl = url;
    }


}
