package com.woaiqw.base;

/**
 * Created by haoran on 2018/5/11.
 */

public interface IProxy {

    void initAFrame(AFrameBinder binder);

    <T> T createService();
}
