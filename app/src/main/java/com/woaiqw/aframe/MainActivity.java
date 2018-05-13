package com.woaiqw.aframe;


import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.woaiqw.base.common.BaseActivity;
import com.woaiqw.base.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.tv0)
    TextView tv0;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.tv3)
    TextView tv3;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        tv0.setText("空数据");
        tv1.setText("网络错误");
        tv2.setText("重试");
        tv3.setText("加载中");
    }


    @OnClick({R.id.tv0, R.id.tv1, R.id.tv2, R.id.tv3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv0:
                showEmptyView();
                break;
            case R.id.tv1:
                showErrorView();
                break;
            case R.id.tv2:
                showNoNetworkRetryView();
                break;
            case R.id.tv3:
                showLoadingView();
                break;
        }
    }

    @Override
    public void onNetworkViewRefresh() {
        super.onNetworkViewRefresh();
        showContentView();
        ToastUtil.showShortToast("重试请求成功");
    }
}
