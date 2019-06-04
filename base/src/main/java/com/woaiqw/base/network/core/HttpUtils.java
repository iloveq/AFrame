package com.woaiqw.base.network.core;

import com.woaiqw.base.network.internel.HAdapter;


/**
 * Created by haoran on 2019/6/4.
 */
public class HttpUtils {

    private HAdapter adapter;

    private HttpUtils() {
    }

    private static class Holder {
        private final static HttpUtils IN = new HttpUtils();
    }

    public static HttpUtils get() {
        return Holder.IN;
    }

    /***************************************************************************/

    public void setAdapter(HAdapter adapter) {
        this.adapter = adapter;
    }

    public HAdapter req(final RequestCtx ctx) {
        if (adapter == null) {
            throw new RuntimeException(" adapter must be new ");
        }
        return adapter.request(ctx);
    }

}
