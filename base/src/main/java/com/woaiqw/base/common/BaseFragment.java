package com.woaiqw.base.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.woaiqw.base.common.hock.BaseHockFragment;


/**
 * Created by haoran on 2017/4/18.
 * Fragment基类
 */

public abstract class BaseFragment extends BaseHockFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), container, false);
    }

    protected abstract int getLayoutId();


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}
