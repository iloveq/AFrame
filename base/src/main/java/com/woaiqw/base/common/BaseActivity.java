package com.woaiqw.base.common;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.woaiqw.base.utils.ActivityUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by haoran on 2017/4/17.
 * Activity基类
 */

public abstract class BaseActivity extends CommonActivity {

    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        unbinder = ButterKnife.bind(this);
        ActivityUtils.addActivity(this);
        afterCreate(savedInstanceState);
    }

    protected abstract int getLayoutId();

    protected abstract void afterCreate(Bundle savedInstanceState);


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        ActivityUtils.removeActivity(this);
    }


}
