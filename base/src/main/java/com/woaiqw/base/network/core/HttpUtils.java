package com.woaiqw.base.network.core;

import com.woaiqw.base.network.internel.Callback;
import com.woaiqw.base.network.internel.HAdapter;
import com.woaiqw.base.network.internel.Parser;


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

    public void req(final RequestCtx ctx, Parser parser, Callback callback) {
        if (adapter == null) {
            throw new RuntimeException(" adapter must be new ");
        }
        adapter.request(ctx,parser,callback);
    }

}
