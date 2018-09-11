package com.woaiqw.base.mvp;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by haoran on 2017/10/30.
 */

public abstract class BasePresenter<T extends IBaseView, M extends IBaseModel> implements IPresenter<T> {


    protected T mView;
    protected M mModel;

    protected CompositeDisposable mDisposable;

    public BasePresenter() {
        this.mModel = bindModel();
        mDisposable = new CompositeDisposable();
    }


    public boolean isViewAttached() {
        return mView != null;
    }


    public void checkViewAttached() {
        if (!isViewAttached()) throw new RuntimeException("未注册View");
    }

    @Override
    public void onAttach(T t) {
        mView = t;
        if (mModel == null) {
            throw new NullPointerException("model没有绑定 不能使用");
        }
    }

    /**
     * 返回presenter想持有的Model引用
     *
     * @return presenter持有的Model引用
     */
    private M bindModel() {
        Type[] params = ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments();
        try {
            mModel = (M) ((Class) params[1]).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return mModel;
    }

    @Override
    public void onDetach() {
        if (!mDisposable.isDisposed())
            mDisposable.dispose();
        mView = null;
        mModel = null;
    }


}
