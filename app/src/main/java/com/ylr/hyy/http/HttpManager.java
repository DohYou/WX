package com.ylr.hyy.http;

import android.text.TextUtils;
import android.util.Log;

import com.blankj.utilcode.util.SPUtils;
import com.ylr.hyy.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpManager {
    private final static Long OUT_TIME = 10L;
    private static final String TAG = "HttpManager";
    private  static OkHttpClient client;
    private static HttpService httpService;
    private static Retrofit retrofit;

    public static HttpService getHttpService(){
        httpService = getRetrofit().create(HttpService.class);
        return httpService;
    }

    public static Retrofit getRetrofit(){
        synchronized (HttpManager.class){
            if (SPUtils.getInstance().getBoolean("tokenExist") && retrofit != null) {
                Log.i(TAG, "tokenExist: "+retrofit);
                return retrofit;
            }

            Interceptor interceptor = chain -> {
                Request original = chain.request();
                Request request = original.newBuilder()
                        .header("access-control-allow-token", SPUtils.getInstance().getString("token"))
                        .header("access-control-allow-time", System.currentTimeMillis()+"")
                        .method(original.method(), original.body())
                        .build();
                Log.i(TAG, "access-control-allow-token = "+SPUtils.getInstance().getString("token"));
                Log.i(TAG, "access-control-allow-time = "+System.currentTimeMillis()+"");
                return chain.proceed(request);
            };

            //添加一个log拦截器,打印log
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            if (BuildConfig.DEBUG) {//开发模式中记录整个body的日志
                httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            } else {//开发模式中记录基本的一些日志，如状态值返回200
                httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            }

            OkHttpClient client = new OkHttpClient().newBuilder()
                    .followRedirects(false)//禁止重定向操作
                    .followSslRedirects(false)//
                    .readTimeout(OUT_TIME, TimeUnit.SECONDS)//设置读取超时时间
                    .connectTimeout(OUT_TIME, TimeUnit.SECONDS)//设置请求超时时间
                    .writeTimeout(OUT_TIME,TimeUnit.SECONDS)//设置写入超时时间
                    .addInterceptor(httpLoggingInterceptor)//添加打印拦截器
                    .addInterceptor(interceptor)
                    .retryOnConnectionFailure(true)//设置出现错误进行重新连接。
                    .build();
            // 获取retrofit的实例
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(HttpService.BASE_URL)
                    .client(client)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            if (!TextUtils.isEmpty(SPUtils.getInstance().getString("token"))) {
                SPUtils.getInstance().put("tokenExist",true);
            }
            return retrofit;
        }
    }
}
