package jyx.com.aw.retrofit;



import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import jyx.com.aw.BuildConfig;
import jyx.com.aw.app.AppApplication;
import jyx.com.aw.global.Config;
import jyx.com.aw.global.Constants;
import jyx.com.aw.util.AppUtil;
import jyx.com.aw.util.MLog;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 作者：
 * 邮箱：
 * 功能：设置网络请求client
 */
public class AppClient3 {
    private static final int DEFAULT_CONN_TIME = 7 * 1000;
    private static final int DEFAULT_RED_TIME = 7 * 1000;

    public static Retrofit mRetrofit;
    public static OkHttpClient okHttpClient;


    public static Retrofit retrofit() {
        if (mRetrofit == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();

            builder.readTimeout(DEFAULT_RED_TIME, TimeUnit.MILLISECONDS)
                    .connectTimeout(DEFAULT_CONN_TIME, TimeUnit.MILLISECONDS);


            if (BuildConfig.DEBUG) {
                //设置日志拦截器
                HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
                // Log信息拦截器
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                //设置拦截器
                builder.addInterceptor(loggingInterceptor);
            }

            //缓存
            builder = setCache(builder);

            //设置公用header拦截器
            builder.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request newRequest = chain.request().newBuilder()
                            .addHeader("hxts", String.valueOf(System.currentTimeMillis() / 1000))
                            .build();
                    return chain.proceed(newRequest);
                }
            });


            OkHttpClient okHttpClient = builder.build();

            mRetrofit = new Retrofit.Builder()
//                    .baseUrl(base_ur)
                    .baseUrl(Config.BASE_URL[3])
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return mRetrofit;
    }


    /**
     * 设置缓存
     *
     * @param builder
     */
    public static OkHttpClient.Builder setCache(OkHttpClient.Builder builder) {
        File cacheFile = new File(AppApplication.getAppContext().getExternalCacheDir(), Constants.NET_WORK_DIR);
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
        Interceptor cacheInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!AppUtil.hasNetWork(AppApplication.getAppContext())) {
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                    MLog.e("http", "无网络");
                }
                Response response = chain.proceed(request);
                if (AppUtil.hasNetWork(AppApplication.getAppContext())) {
                    int maxAge = 0 * 60; // read from cache for 60 minute
                    // 有网络时 设置缓存超时时间0小时
                    response.newBuilder()
                            .header("Cache-Control", "public, max-age=" + maxAge)
                            .removeHeader("Pragma")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                            .build();
                } else {
                    // 无网络时，设置超时为1周
                    int maxStale = 60 * 60 * 24 * 7;
                    response.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .removeHeader("Pragma")
                            .build();
                }
                return response;
            }
        };
//        先判断网络，网络好的时候，移除header后添加haunch失效时间为1小时，网络未连接的情况下设置缓存时间为7天。
        builder.cache(cache).addInterceptor(cacheInterceptor);
        return builder;
    }
}
