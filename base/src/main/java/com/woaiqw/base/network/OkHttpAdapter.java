package com.woaiqw.base.network;

import com.woaiqw.base.core.ThreadPool;
import com.woaiqw.base.network.constants.HConstants;
import com.woaiqw.base.network.core.RequestCtx;
import com.woaiqw.base.network.internel.HAdapter;
import com.woaiqw.base.network.utils.OkHttpHelper;
import com.woaiqw.base.network.utils.ParamsUtils;
import com.woaiqw.base.utils.WeakHandler;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by haoran on 2019/6/4.
 */
public class OkHttpAdapter implements HAdapter {

    private OkHttpClient clone;
    private WeakHandler handler;
    private CompositeDisposable disposable;
    private Disposable dispatcher;

    public OkHttpAdapter() {
        this.clone = OkHttpHelper.getInstance().getClient();
        disposable = new CompositeDisposable();
    }

    @Override
    public HAdapter request(final RequestCtx ctx) {
        if (ctx == null) {
            throw new RuntimeException(" please new ctx ");
        }
        if (ctx.getCallback() == null) {
            throw new RuntimeException(" callback is empty ");
        }
        if (ctx.getUrl() == null || ctx.getUrl().isEmpty()) {
            throw new RuntimeException(" url is empty ");
        }
        ThreadPool.getInstance().getPool().execute(new Runnable() {
            @Override
            public void run() {
                doRequestTask(ctx);
            }
        });
        return this;

    }

    private void doRequestTask(final RequestCtx ctx) {
//        dispatcher = Observable.create(new ObservableOnSubscribe<Call>() {
//
//            @Override
//            public void subscribe(ObservableEmitter<Call> emitter) throws Exception {
//                emitter.onNext(enqueue(ctx));
//            }
//        }).map(new Function<Call, String>() {
//        })
    }

    private Call enqueue(final RequestCtx ctx) {

        String baseUrl = ctx.getUrl();
        if (ctx.getParamMap() != null && !ctx.getParamMap().isEmpty()) {
            baseUrl = ParamsUtils.getUrl(ctx.getUrl(), ctx.getParamMap());
        }
        Request.Builder builder = new Request.Builder()
                .url(baseUrl);

        if (ctx.getMethod() != null && !ctx.getMethod().isEmpty()) {
            switch (ctx.getMethod()) {
                case HConstants.get:
                    break;
                case HConstants.post:
                    builder.post(RequestBody.create(MediaType.parse(HConstants.JSON), ctx.getBody()));
                    break;
            }
        }
        if (ctx.getHeaderMap() != null && !ctx.getHeaderMap().isEmpty()) {
            for (Map.Entry<String, String> entry : ctx.getHeaderMap().entrySet()) {
                builder.addHeader(entry.getKey(), entry.getValue());
            }
        }
        Call call = clone.newCall(builder.build());

        if (ctx.isCanceled()) {
            call.cancel();
        }

        return call;


    }

    private void dispatcher(final boolean success, final Throwable throwable, final Response response) {


    }
}
