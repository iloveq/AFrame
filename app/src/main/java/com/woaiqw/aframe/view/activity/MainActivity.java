package com.woaiqw.aframe.view.activity;


import android.Manifest;
import android.os.Bundle;
import android.os.Looper;
import android.os.MessageQueue;
import android.os.SystemClock;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import com.woaiqw.aframe.R;
import com.woaiqw.aframe.adapter.CardListAdapter;
import com.woaiqw.aframe.bean.CardListBean;
import com.woaiqw.aframe.contract.MainContract;
import com.woaiqw.aframe.presenter.MainPresenter;
import com.woaiqw.aframe.view.widget.BorderDividerItemDecoration;
import com.woaiqw.base.common.BaseActivity;
import com.woaiqw.base.utils.PermissionListener;
import com.woaiqw.base.utils.ToastUtil;

import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements MainContract.IMainView {

    @BindView(R.id.rv)
    RecyclerView rv;
    MainContract.IMainPresenter presenter;
    private CardListAdapter adapter;
    private String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        BorderDividerItemDecoration itemDecoration = new BorderDividerItemDecoration(this.getResources().getDimensionPixelOffset(R.dimen.border_divider_height), this.getResources().getDimensionPixelOffset(R.dimen.border_padding_spans));
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rv.setLayoutManager(staggeredGridLayoutManager);
        rv.addItemDecoration(itemDecoration);
        adapter = new CardListAdapter();
        presenter = new MainPresenter();
        presenter.onAttach(this);
        rv.setAdapter(adapter);
        requestPermissions(permissions, new PermissionListener() {
            @Override
            public void onGranted() {
                presenter.getCardList();
            }

            @Override
            public void onDenied(List<String> deniedPermissions) {
                showEmptyDataView();
            }
        });

    }

    @Override
    protected void onResume() {
        final long time = SystemClock.uptimeMillis();
        super.onResume();
        Looper.myQueue().addIdleHandler(new MessageQueue.IdleHandler() {
            @Override
            public boolean queueIdle() {
                // on Measure() -> onDraw() 耗时
                Log.i(MainActivity.this.getClass().getSimpleName(), "onCreate -> idle : " + (SystemClock.uptimeMillis() - time));
                return false;
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDetach();
    }

    @Override
    public void onNetworkViewRefresh() {
        super.onNetworkViewRefresh();
        ToastUtil.showShortToast("重新请求中...");
        presenter.getCardList();
    }

    @Override
    public void showLoading() {
        showLoadingView();
    }

    @Override
    public void hideLoading() {
        showContentView();
    }

    @Override
    public void onError(String message) {
        ToastUtil.showShortToast(message);
        showErrorView();
    }

    @Override
    public void showEmptyDataView() {
        showEmptyView();
    }

    @Override
    public void showCardList(List<CardListBean.CardBean> cardBeanList) {
        if (adapter != null)
            adapter.replaceData(cardBeanList);
    }
}
