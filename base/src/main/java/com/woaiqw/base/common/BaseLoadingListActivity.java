package com.woaiqw.base.common;


import com.woaiqw.base.mvp.IBaseLoadingListView;
import com.woaiqw.base.widget.NetworkStateView;

/**
 * Created by haoran on 2019/5/30.
 */
public abstract class BaseLoadingListActivity extends BaseLoadingActivity implements IBaseLoadingListView, NetworkStateView.OnRetryClickListener {
    @Override
    public void showEmpty() {
        nsv.showEmpty();
    }
}
