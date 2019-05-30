package com.woaiqw.aframe.view.activity;


import android.Manifest;
import android.os.Bundle;
import android.os.Looper;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.woaiqw.aframe.R;
import com.woaiqw.aframe.adapter.CardListAdapter;
import com.woaiqw.aframe.bean.CardBean;
import com.woaiqw.aframe.contract.MainContract;
import com.woaiqw.aframe.presenter.MainPresenter;
import com.woaiqw.aframe.view.widget.BorderDividerItemDecoration;
import com.woaiqw.base.common.BaseLoadingListActivity;
import com.woaiqw.base.utils.ToastUtils;
import com.yanzhenjie.permission.AndPermission;

import java.util.List;


public class MainActivity extends BaseLoadingListActivity implements MainContract.IMainView, OnRefreshListener, OnLoadMoreListener {

    RecyclerView rv;
    SmartRefreshLayout srl;

    MainContract.IMainPresenter presenter;
    private CardListAdapter adapter;
    private int currentIndex = 1;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new MainPresenter();
        presenter.onAttach(this);
        AndPermission.with(this)
                .runtime()
                .permission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .onGranted(data -> {
                    try {
                        presenter.getCardList(currentIndex);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }).onDenied(data -> {
                    ToastUtils.showShort("可能耗费更多流量");
                    presenter.getCardList(currentIndex);
                }
        ).start();

    }


    @Override
    public void reload() {
        super.reload();
        currentIndex = 1;
        presenter.getCardList(currentIndex);
    }

    @Override
    protected void onResume() {
        final long time = SystemClock.uptimeMillis();
        super.onResume();
        Looper.myQueue().addIdleHandler(() -> {
            // on Measure() -> onDraw() 耗时  ActivityThread.handleResumeActivity 之后会 调用  Looper.myQueue().addIdleHandler(new Idle()) 之前都在 ViewRootImpl.performTravel
            Log.i(MainActivity.this.getClass().getSimpleName(), "onCreate -> idle : " + (SystemClock.uptimeMillis() - time));
            return false;
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDetach();
    }

    @Override
    public void renderPage(Object o) {
        super.renderPage(o);
        if (rv == null) {
            // 延迟调用 find set 方法
            rv = findViewById(R.id.rv);
            srl = findViewById(R.id.srl);
            srl.setEnableRefresh(true);
            srl.setRefreshHeader(new ClassicsHeader(this));
            srl.setRefreshFooter(new ClassicsFooter(this));
            srl.setOnRefreshListener(this);
            srl.setOnLoadMoreListener(this);
            BorderDividerItemDecoration itemDecoration = new BorderDividerItemDecoration(
                    this.getResources().getDimensionPixelOffset(R.dimen.border_divider_height),
                    this.getResources().getDimensionPixelOffset(R.dimen.border_padding_spans));
            StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(
                    2,
                    StaggeredGridLayoutManager.VERTICAL);
            rv.setLayoutManager(staggeredGridLayoutManager);
            rv.addItemDecoration(itemDecoration);
            adapter = new CardListAdapter();
            rv.setAdapter(adapter);
        }
        if (o != null) {
            List<CardBean> cardBeanList = (List<CardBean>) o;
            if (cardBeanList.isEmpty()) {
                if (currentIndex == 1) {
                    showEmpty();
                } else {
                    ToastUtils.showShort("没有更多了666");
                }
                return;
            }
            // 通过 index 判断 当前状态
            if (currentIndex == 1) {
                adapter.replaceData(cardBeanList);
            } else {
                adapter.addData(cardBeanList);
            }

        }

    }


    @Override
    public void onRefresh(RefreshLayout refreshLayout) {
        refreshLayout.finishRefresh(800, true);
        this.reload();
    }

    @Override
    public void onLoadMore(RefreshLayout refreshLayout) {
        refreshLayout.finishLoadMore(800);
        presenter.getCardList(++currentIndex);
    }

}
