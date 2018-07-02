# AFrame

#### 项目介绍
https://www.jianshu.com/p/62f33de16522

//慎用，只适合本人的《微笑明信片》项目
//AFrameProxy里的一个坑：
//String HOST_MAPPING = "/api/";
上面两行代码以去除，但还是慎用，谢谢关注：)


```
    // project下
    repositories {
        //google()
        //jcenter()
        maven { url "https://jitpack.io" }
    }
```
```
    //module下
    //a frame 一些通用的类
    //simple版本
    //compile 'com.github.woaigmz:AFrame:0.0.2'
    //proxy版本
    //compile 'com.github.woaigmz:AFrame:0.0.3'
    //去除host-mapping版本，可正常使用
    compile 'com.github.woaigmz:AFrame:0.0.4'
```

#### 软件架构


#### 使用说明
1：创建IApiService (因为AFrame用到retrofit，网络权限可以不添加AFrame的manifest里已经声明过了)
```
public interface IApiService {

    //欢迎页获取全局配置信息
    @POST("getCardList")
    @FormUrlEncoded
    Observable<BaseResult<CardListBean>> getCardList(@Field("name") String name, @Field("page") String page, @Field("max") String max);

}
```
2：创建App 继承BaseApp (注意：主项目的manifest的application里android:name=".App")
```
public class App extends BaseApp {
    @Override
    public void onCreate() {
        super.onCreate();
    }
}
```
3：初始化代理(在你的App里)
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
4：网络请求部分(使用rxjava)
注: 通过 
```
AFrameProxy.getInstance().<IApiService>createService() 
```
创建apiservice
使用:
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


#### 参与贡献

