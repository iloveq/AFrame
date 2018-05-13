package com.woaiqw.base.mvp;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by haoran on 2017/10/30.
 */

public abstract class BasePresenter<T extends IBaseView,M extends IBaseModel> implements IPresenter<T> {


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
    public abstract M bindModel();

    @Override
    public void onDetach() {
        if (!mDisposable.isDisposed())
            mDisposable.dispose();
        mView = null;
    }


}
