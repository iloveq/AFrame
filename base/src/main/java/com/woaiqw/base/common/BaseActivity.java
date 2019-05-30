package com.woaiqw.base.common;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.woaiqw.base.common.hock.BaseHockActivity;
import com.woaiqw.base.utils.ActivityUtils;




/**
 * Created by haoran on 2017/4/17.
 * Activity基类
 */

public abstract class BaseActivity extends BaseHockActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ActivityUtils.addActivity(this);
    }

    protected abstract int getLayoutId();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityUtils.removeActivity(this);
    }


}
