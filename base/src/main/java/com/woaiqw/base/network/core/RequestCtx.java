package com.woaiqw.base.network.core;

import com.woaiqw.base.network.internel.Callback;
import com.woaiqw.base.network.internel.Parser;

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
    private final boolean canceled;
    private final Parser parser;
    private final Callback callback;

    RequestCtx(Builder builder) {
        this.url = builder.url;
        this.method = builder.method;
        this.paramMap = builder.paramMap;
        this.headerMap = builder.headerMap;
        this.body = builder.body;
        this.parser = builder.parser;
        this.canceled = builder.canceled;
        this.callback = builder.callback;
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

    public boolean isCanceled() {
        return canceled;
    }

    public Parser getParser() {
        return parser;
    }

    public Callback getCallback() {
        return callback;
    }



    public static class Builder {
        String url;
        String method;
        HashMap<String, String> paramMap;
        HashMap<String, String> headerMap;
        String body;
        boolean canceled;
        Parser parser;
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

        public Builder setParser(Parser parser) {
            this.parser = parser;
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
