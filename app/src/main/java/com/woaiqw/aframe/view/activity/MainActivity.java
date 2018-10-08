package com.woaiqw.aframe.view.activity;


import android.Manifest;
import android.os.Bundle;
import android.os.Looper;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import com.woaiqw.aframe.R;
import com.woaiqw.aframe.adapter.CardListAdapter;
import com.woaiqw.aframe.bean.CardListBean;
import com.woaiqw.aframe.contract.MainContract;
import com.woaiqw.aframe.presenter.MainPresenter;
import com.woaiqw.aframe.view.widget.BorderDividerItemDecoration;
import com.woaiqw.base.common.PermissionActivity;
import com.woaiqw.base.utils.PermissionListener;
import com.woaiqw.base.utils.ToastUtils;
import com.woaiqw.base.widget.NetworkStateView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends PermissionActivity implements MainContract.IMainView, NetworkStateView.OnRetryClickListener {

    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.nsv)
    NetworkStateView nsv;
    MainContract.IMainPresenter presenter;
    private CardListAdapter adapter;
    private String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
        afterCreate();
    }


    private void afterCreate() {
        nsv.setOnRetryClickListener(this);
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
        Looper.myQueue().addIdleHandler(() -> {
            // on Measure() -> onDraw() 耗时
            Log.i(MainActivity.this.getClass().getSimpleName(), "onCreate -> idle : " + (SystemClock.uptimeMillis() - time));
            return false;
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        presenter.onDetach();
    }


    @Override
    public void showLoading() {
        nsv.showLoading();
    }

    @Override
    public void hideLoading() {
        nsv.showSuccess();
    }

    @Override
    public void onError(String message) {
        ToastUtils.showShort(message);
        nsv.showError();
    }

    @Override
    public void showEmptyDataView() {
        nsv.showEmpty();
    }

    @Override
    public void showCardList(List<CardListBean.CardBean> cardBeanList) {
        if (adapter != null)
            adapter.replaceData(cardBeanList);
    }

    @Override
    public void onRefresh() {
        ToastUtils.showShort("重新请求中...");
        presenter.getCardList();
    }
}
