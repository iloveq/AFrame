package com.woaiqw.base.utils;

import java.util.List;

/**
 * Created by haoran on 2017/4/19.
 * 权限回调接口
 */

public interface PermissionListener {

    void onGranted();

    void onDenied(List<String> deniedPermissions);

}
