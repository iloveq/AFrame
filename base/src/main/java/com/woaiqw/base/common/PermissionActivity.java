package com.woaiqw.base.common;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.woaiqw.base.utils.ActivityUtils;
import com.woaiqw.base.utils.PermissionListener;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by haoran on 2017/4/17.
 * Activity基类
 */

public abstract class PermissionActivity extends CommonActivity {


    private static final int CODE_REQUEST_PERMISSION = 1;
    private PermissionListener mPermissionListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityUtils.addActivity(this);
    }


    /**
     * 申请权限
     *
     * @param permissions 需要申请的权限(数组)
     * @param listener    权限回调接口
     */
    public void requestPermissions(String[] permissions, PermissionListener listener) {
        Activity activity = ActivityUtils.getTopActivity();

        if (null == activity) {
            return;
        }

        mPermissionListener = listener;

        List<String> permissionList = new ArrayList<>();

        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(permission);
            }
        }

        if (!permissionList.isEmpty()) {
            ActivityCompat.requestPermissions(activity, permissionList.toArray(new String[permissionList.size()]), CODE_REQUEST_PERMISSION);
        } else {
            mPermissionListener.onGranted();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case CODE_REQUEST_PERMISSION:
                if (grantResults.length > 0) {
                    List<String> deniedPermissions = new ArrayList<>();
                    for (int i = 0; i < grantResults.length; i++) {
                        int result = grantResults[i];
                        if (result != PackageManager.PERMISSION_GRANTED) {
                            String permission = permissions[i];
                            deniedPermissions.add(permission);
                        }
                    }
                    if (deniedPermissions.isEmpty()) {
                        mPermissionListener.onGranted();
                    } else {
                        mPermissionListener.onDenied(deniedPermissions);
                    }
                }
                break;

            default:
                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPermissionListener != null)
            mPermissionListener = null;
        ActivityUtils.removeActivity(this);
    }


}
