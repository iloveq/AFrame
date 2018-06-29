package com.woaiqw.aframe.view.activity;


import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.woaiqw.aframe.R;
import com.woaiqw.aframe.adapter.CardListAdapter;
import com.woaiqw.aframe.bean.CardListBean;
import com.woaiqw.aframe.contract.MainContract;
import com.woaiqw.aframe.presenter.MainPresenter;
import com.woaiqw.aframe.view.widget.BorderDividerItemDecoration;
import com.woaiqw.base.common.BaseActivity;
import com.woaiqw.base.utils.ToastUtil;

import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements MainContract.IMainView {

    @BindView(R.id.rv)
    RecyclerView rv;
    MainContract.IMainPresenter presenter;
    private CardListAdapter adapter;

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
        presenter.getCardList();
    }

    @Override
    public void onNetworkViewRefresh() {
        super.onNetworkViewRefresh();
        showContentView();
        ToastUtil.showShortToast("重试请求成功");
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
        if (adapter!=null)
            adapter.replaceData(cardBeanList);
    }
}
