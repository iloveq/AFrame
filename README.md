# AFrame
# 最新0.1.6
 //增加了一些工具类
 root ：
```
    repositories {
        //google()
        //jcenter()
        maven { url "https://jitpack.io" }
    }
```
 app ：  
```
    compile 'com.github.woaigmz:AFrame:0.1.4'
    //要使用AFrame的BaseActivity系列的话别忘了添加butterknife
    annotationProcessor 'com.jakewharton:butterknife-compiler:8.8.1'
    
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


