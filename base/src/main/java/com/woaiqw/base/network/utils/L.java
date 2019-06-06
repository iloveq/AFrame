package com.woaiqw.base.network.utils;

import android.util.Log;

import com.woaiqw.base.BuildConfig;

/**
 * Created by zhy on 15/11/6.
 */
public class L
{

    public static void e(String tag,String msg)
    {
        if (BuildConfig.DEBUG)
        {
            Log.e(tag, msg);
        }
    }

}
