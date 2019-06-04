package com.woaiqw.base.network.core;

import com.woaiqw.base.network.internel.Callback;

import java.util.HashMap;


/**
 * Created by haoran on 2019/6/4.
 */
public class RequestCtx {

    private final String url;
    private final String method;
    private final HashMap<String, String> paramMap;
    private final HashMap<String, String> headerMap;
    private final String body;
    private boolean canceled;
    private final Callback callback;

    RequestCtx(Builder builder) {
        this.url = builder.url;
        this.method = builder.method;
        this.paramMap = builder.paramMap;
        this.headerMap = builder.headerMap;
        this.body = builder.body;
        this.callback = builder.callback;
        this.canceled = builder.canceled;
    }

    public String getUrl() {
        return url;
    }

    public String getMethod() {
        return method;
    }

    public HashMap<String, String> getParamMap() {
        return paramMap;
    }

    public HashMap<String, String> getHeaderMap() {
        return headerMap;
    }

    public String getBody() {
        return body;
    }

    public Callback getCallback() {
        return callback;
    }

    public boolean isCanceled() {
        return canceled;
    }

    public static class Builder {
        String url;
        String method;
        HashMap<String, String> paramMap;
        HashMap<String, String> headerMap;
        String body;
        boolean canceled;
        Callback callback;

        public Builder setUrl(String url) {
            this.url = url;
            return this;
        }

        public Builder setMethod(String method) {
            this.method = method;
            return this;
        }

        public Builder setParamsMap(HashMap<String, String> paramsMap) {
            this.paramMap = paramsMap;
            return this;
        }

        public Builder setHeaderMap(HashMap<String, String> headerMap) {
            this.headerMap = headerMap;
            return this;
        }

        public Builder setBody(String body) {
            this.body = body;
            return this;
        }

        public Builder setCanceled(boolean canceled) {
            this.canceled = canceled;
            return this;
        }

        public Builder setCallback(Callback callback) {
            this.callback = callback;
            return this;
        }

        public RequestCtx build() {
            return new RequestCtx(this);
        }

    }


}
