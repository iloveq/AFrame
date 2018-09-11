package com.woaiqw.base.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;


/**
 * Created by haoran on 2017/8/29.
 */

public abstract class LazyFragment extends Fragment {

    private Bundle savedInstanceState;
    private boolean isInit = false;
    private boolean isVisibleToUser;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        View root = getView();

        if (isVisibleToUser && !isInit && root != null) {
            onViewCreatedLazy(savedInstanceState);
            isInit = true;
        }

        if (isInit && root != null) {
            if (isVisibleToUser) {
                onResumeLazy();
            } else {
                onPauseLazy();
            }
        }
    }


    @Deprecated
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (isVisibleToUser && !isInit) {
            this.savedInstanceState = savedInstanceState;
            onViewCreatedLazy(savedInstanceState);
            isInit = true;
            onResumeLazy();
        }

    }

    @Deprecated
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Deprecated
    public void onDestroyView() {
        super.onDestroyView();
        isInit = false;
        onDestroyViewLazy();
    }

    @Deprecated
    public void onResume() {
        super.onResume();
        if (getUserVisibleHint())
            onResumeLazy();
    }

    @Deprecated
    public void onPause() {
        super.onPause();
        if (getUserVisibleHint())
            onPauseLazy();
    }

    public abstract void onViewCreatedLazy(Bundle savedInstanceState);

    public abstract void onResumeLazy();

    public abstract void onPauseLazy();

    public abstract void onDestroyViewLazy();

}
