package com.woaiqw.base.mvp;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by haoran on 2017/10/30.
 */

public abstract class BasePresenter<T extends IBaseView, M extends IBaseModel> implements IPresenter<T> {


    protected T view;
    protected M model;

    protected CompositeDisposable disposable;

    public BasePresenter() {
        this.model = bindModel();
        disposable = new CompositeDisposable();
    }


    public boolean isViewAttached() {
        return view != null;
    }


    public void checkViewAttached() {
        if (!isViewAttached()) throw new RuntimeException("未注册View");
    }

    @Override
    public void onAttach(T t) {
        view = t;
        if (model == null) {
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
            model = (M) ((Class) params[1]).newInstance();
        } catch (Exception e) {
            model = null;
        }
        return model;
    }

    @Override
    public void onDetach() {
        if (!disposable.isDisposed())
            disposable.dispose();
        view = null;
        model = null;
    }


}
