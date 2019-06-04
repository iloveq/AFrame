# AFrame
# 最近更新

提供新的网络请求方式：
```
/**------ model ------*/
// 对 okhttp 二次封装
RequestCtx ctx = new RequestCtx.Builder()
    .setUrl("http://gank.io/api/data/福利/" + "10/" + index)
    .setParser(new GetCardListJsonParser())
    .setCallback(callback).build();
HttpUtils.get().req(ctx);
/**------ presenter ------*/
 @Override
    public void getCardList(int pageNum) {
        checkViewAttached();
        view.showLoading("");
        model.getCardList(pageNum, new Callback() {
            @Override
            public void then(Object o) {
                view.closeLoading();
                view.renderPage(o);
            }

            @Override
            public void error(Object o) {
                view.closeLoading();
                view.showError((String) o);
            }
        });
    }
```

```
version - 0.1.7

Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
	
Add the dependency

	dependencies {
	     implementation 'com.github.woaigmz:AFrame:0.1.7'
	}
--------------------------------  更新说明   --------------------------
base 里增加一些库

    // 下拉刷新
    compile 'com.scwang.smartrefresh:SmartRefreshLayout:1.0.5.1'
    compile 'com.scwang.smartrefresh:SmartRefreshHeader:1.0.5.1'
    // 权限动态申请
    compile 'com.yanzhenjie:permission:2.0.0-rc12'
    // recyclerview adapter 
    compile 'com.github.woaigmz:SmartDiffAdapter:0.3.4'
    // recyclerview
    compile 'com.android.support:recyclerview-v7:27.1.1'
    
----------------------------------------------------------------------

```

感谢 gankio 的 api

建议直接依赖 base 使用 BaseLoadingActivity 便于修改 NetworkStateView 的 UI

去除 ButterKnife 并不是所有人都喜欢控制反转

Mvp结构调整 IBaseView IBaseLoadingView IBaseLoadingListView ,IBaseView 默认不再拥有方法，增加可扩展性

调整继承树为：  AppComponentActivity - BaseHockActivity(做一些针对当前项目的事情) - BaseActivity - BaseLoadingActivity - BaseLoadingListActivity


模板模式减少了之前版本 MainActivity 的代码行数

```
public class MainActivity extends BaseLoadingListActivity implements MainContract.IMainView, OnRefreshListener, OnLoadMoreListener {

    RecyclerView rv;
    SmartRefreshLayout srl;
    MainContract.IMainPresenter presenter;
    private CardListAdapter adapter;
    private int currentIndex = 1;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new MainPresenter();
        presenter.onAttach(this);
        AndPermission.with(this)
                .runtime()
                .permission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .onGranted(data -> {
                    try {
                        presenter.getCardList(currentIndex);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }).onDenied(data -> {
                    ToastUtils.showShort("可能耗费更多流量");
                    presenter.getCardList(currentIndex);
                }
        ).start();

    }

    @Override
    public void reload() {
        super.reload();
        currentIndex = 1;
        presenter.getCardList(currentIndex);
    }

    @Override
    protected void onResume() {
        final long time = SystemClock.uptimeMillis();
        super.onResume();
        Looper.myQueue().addIdleHandler(() -> {
            // on Measure() -> onDraw() 耗时  ActivityThread.handleResumeActivity 之后会 调用  Looper.myQueue().addIdleHandler(new Idle()) 之前都在 ViewRootImpl.performTravel
            Log.i(MainActivity.this.getClass().getSimpleName(), "onCreate -> idle : " + (SystemClock.uptimeMillis() - time));
            return false;
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDetach();
    }

    @Override
    public void renderPage(Object o) {
        super.renderPage(o);
        if (rv == null) {
            // 延迟调用 find set 方法
            rv = findViewById(R.id.rv);
            srl = findViewById(R.id.srl);
            srl.setEnableRefresh(true);
            srl.setRefreshHeader(new ClassicsHeader(this));
            srl.setRefreshFooter(new ClassicsFooter(this));
            srl.setOnRefreshListener(this);
            srl.setOnLoadMoreListener(this);
            BorderDividerItemDecoration itemDecoration = new BorderDividerItemDecoration(
                    this.getResources().getDimensionPixelOffset(R.dimen.border_divider_height),
                    this.getResources().getDimensionPixelOffset(R.dimen.border_padding_spans));
            StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(
                    2,
                    StaggeredGridLayoutManager.VERTICAL);
            rv.setLayoutManager(staggeredGridLayoutManager);
            rv.addItemDecoration(itemDecoration);
            adapter = new CardListAdapter();
            rv.setAdapter(adapter);
        }
        if (o != null) {
            List<CardBean> cardBeanList = (List<CardBean>) o;
            if (cardBeanList.isEmpty()) {
                if (currentIndex == 1) {
                    showEmpty();
                } else {
                    ToastUtils.showShort("没有更多了666");
                }
                return;
            }
            // 通过 index 判断 当前状态
            if (currentIndex == 1) {
                adapter.replaceData(cardBeanList);
            } else {
                adapter.addData(cardBeanList);
            }
        }
    }

    @Override
    public void onRefresh(RefreshLayout refreshLayout) {
        refreshLayout.finishRefresh(800, true);
        this.reload();
    }

    @Override
    public void onLoadMore(RefreshLayout refreshLayout) {
        refreshLayout.finishLoadMore(800);
        presenter.getCardList(++currentIndex);
    }

}
```

### 更新说明

(0.0.7)：

    移动 FileHelper 到 BaseApp 里
    移动 权限声明到 BaseApp 里
    封装 ImageLoader 基于 Glide4+ ，GlideModule


(0.0.6)：
```
增加了 image 加载的工具,Serialize的序列化工具：
使用 GlideAppModule, 图片加载性能体验更好
```

ImageLoader使用方法：

```
    ImageLoader.loadImage(myViewHolder.getImage(), cardBean.getImgurl());

```

(0.0.5)：
提供更多接口：
   
```
         AFrameProxy.getInstance().initAFrame(new AFrameBinder() {
            @Override
            public Class getApiService() {
                return IApiService.class;
            }

            @Override
            public OkHttpClient getOkHttpClient() {
                return OkHttpHelper.getInstance().getClient();
            }

            @Override
            public String getServerHost() {
                return "http://118.89.233.211:3000/api/";
            }


            @Override
            public Converter.Factory getConverterFactory() {
                return GsonConverterFactory.create();
            }

            @Override
            public CallAdapter.Factory getCallAdapterFactory() {
                return RxJava2CallAdapterFactory.create();
            }
        });
```
AFrameProxy-校验binder：
    
```
         @Override
        public void initAFrame(AFrameBinder binder) {
            this.binder = binder;
            validateAFrameBinderStatus(binder);
            initRetrofit();
        }

 ```
    
### 项目介绍 （ 基于 0.0.4 ） ：
https://www.jianshu.com/p/62f33de16522

建议直接引用base-module，有bug方便修改(慎用)，谢谢大家关注，欢迎fork，issues ：)

#####  root build.gradle at the end of repositories :
```
    repositories {
        //google()
        //jcenter()
        maven { url "https://jitpack.io" }
    }
    
```
#####   Add the dependency of your app module :
```
    compile 'com.github.woaigmz:AFrame:0.0.4'
    //要使用AFrame的BaseActivity系列的话别忘了添加butterknife
    annotationProcessor 'com.jakewharton:butterknife-compiler:8.8.1'
    
```

### 软件架构 ：

```
1. 单一职责原则（Single Responsibility Principle - SRP）
2. 开放封闭原则（Open Closed Principle - OCP）
3. 里氏替换原则（Liskov Substitution Principle - LSP）
4. 最少知识原则（Least Knowledge Principle - LKP）
5. 接口隔离原则（Interface Segregation Principle - ISP）
6. 依赖倒置原则（Dependence Inversion Principle - DIP）
```

### 使用说明 ：

##### 1：创建IApiService (因为AFrame用到retrofit，网络权限可以不添加AFrame的manifest里已经声明过了)
```
public interface IApiService {

    //欢迎页获取全局配置信息
    @POST("getCardList")
    @FormUrlEncoded
    Observable<BaseResult<CardListBean>> getCardList(@Field("name") String name, @Field("page") String page, @Field("max") String max);

}

```
##### 2：创建App 继承BaseApp (注意：主项目的manifest的application里android:name=".App")
```
public class App extends BaseApp {
    @Override
    public void onCreate() {
        super.onCreate();
    }
}

```
##### 3：初始化代理(在你的App里)
```
public class App extends BaseApp {
    @Override
    public void onCreate() {
        super.onCreate();
        AFrameProxy.getInstance().initAFrame(new AFrameBinder() {
            @Override
            public String getServerHost() {
                return "http://118.89.233.211:3000";
            }

            @Override
            public Class getApiService() {
                return IApiService.class;
            }
        });
    }
}

```
##### 4：网络请求部分(使用rxjava)
注: 创建apiservice是网络请求的关键，可在mvp的model层里网络请求 ：）
```
AFrameProxy.getInstance().<IApiService>createService() 

```

model层的使用:
```
public class MainModel implements MainContract.IMainModel {

    public static IBaseModel newInstance() {
        return new MainModel();
    }

    @Override
    public Observable<List<CardListBean.CardBean>> getCardList() {
        return AFrameProxy.getInstance().<IApiService>createService().getCardList("111", "0","0").compose(RxUtils.<CardListBean>transform()).map(new Function<CardListBean, List<CardListBean.CardBean>>() {
            @Override
            public List<CardListBean.CardBean> apply(CardListBean cardListBean) throws Exception {
                return cardListBean.getCardList();
            }
        });
    }
}

```

### 版本说明 ：

```
    1：test 版本
    compile 'com.github.woaigmz:AFrame:0.0.2'
    2：simple 版本
    compile 'com.github.woaigmz:AFrame:0.0.2'
    3：proxy版本 含 host-mapping
    compile 'com.github.woaigmz:AFrame:0.0.3'
    4：去除 mapping 版本
    compile 'com.github.woaigmz:AFrame:0.0.4'
    5：提供外层接口 版本
    compile 'com.github.woaigmz:AFrame:0.0.5'
```


