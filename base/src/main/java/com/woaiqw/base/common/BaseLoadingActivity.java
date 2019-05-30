package com.woaiqw.base.common;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.woaiqw.base.R;
import com.woaiqw.base.common.hock.BaseHockActivity;
import com.woaiqw.base.mvp.IBaseLoadingView;
import com.woaiqw.base.utils.ActivityUtils;
import com.woaiqw.base.utils.ToastUtils;
import com.woaiqw.base.widget.NetworkStateView;

/**
 * Created by haoran on 2019/5/30.
 */
public abstract class BaseLoadingActivity extends BaseHockActivity implements IBaseLoadingView, NetworkStateView.OnRetryClickListener {

    protected NetworkStateView nsv;
    private View child;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_loading);
        int layoutId = getLayoutId();
        if (layoutId != 0) {
            FrameLayout contentParent =  findViewById(R.id.fl);
            child = LayoutInflater.from(this).inflate(layoutId, null);
            contentParent.addView(child);
            nsv = new NetworkStateView(this);
            contentParent.addView(nsv,1);
            nsv.setOnRetryClickListener(this);
        }
        ActivityUtils.addActivity(this);
    }

    protected abstract int getLayoutId();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityUtils.removeActivity(this);
    }

    @Override
    public void showLoading(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            ToastUtils.showShort(msg);
        }
        nsv.showLoading();
    }

    @Override
    public void closeLoading() {
        nsv.showSuccess();
    }

    @Override
    public void renderPage(Object o) {

    }

    @Override
    public void reload() {
        if (child.getVisibility()==View.GONE){
            child.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showError(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            ToastUtils.showShort(msg);
        }
        if (child.getVisibility()==View.VISIBLE){
            child.setVisibility(View.GONE);
        }
        nsv.showError();
    }

    @Override
    public void showDisconnect() {
        if (child.getVisibility()==View.VISIBLE){
            child.setVisibility(View.GONE);
        }
        nsv.showNoNetworkRetry();
    }

    @Override
    public void onRetry() {
        this.reload();
    }
}
