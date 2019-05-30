package com.woaiqw.aframe;

import com.woaiqw.aframe.bean.BaseResult;
import com.woaiqw.aframe.bean.CardBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by haoran on 2017/11/2.
 */

public interface IApiService {

    //api/getCardList 获取推荐明信片列表
    @GET("福利/{max}/{page}")
    Observable<BaseResult<List<CardBean>>> getCardList(@Path("page") int pageNum, @Path("max") int pageSize);

}


