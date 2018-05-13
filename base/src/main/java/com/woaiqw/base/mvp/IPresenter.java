package com.woaiqw.base.mvp;

/**
 * Created by haoran on 2017/10/30.
 */


public interface IPresenter<T> {

    void onAttach(T t);

    void onDetach();


}
