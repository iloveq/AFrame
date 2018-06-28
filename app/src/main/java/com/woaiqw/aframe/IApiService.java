package com.woaiqw.aframe;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by haoran on 2017/11/2.
 */

public interface IApiService {


    //欢迎页获取全局配置信息
    @POST("doRequest2/globalConfigV2")
    Observable<String> getSplashGlobalConfig(@QueryMap Map<String, String> map);

}


