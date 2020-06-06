package com.example.wubi;

import android.content.Context;
import android.util.Log;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {
    static Context context;
    private static RetrofitManager mRetrofitManager;
    public static OkHttpClient okHttpClient;
    private String baseUrl;
    private Retrofit mRetrofit;

    static {
        getOkHttpClient();
    }

    public RetrofitManager(String baseUrl, Context context) {
        this.baseUrl = baseUrl;
        context = context;
        initRetrofit(context);
    }

    public static synchronized RetrofitManager getInstance(String baseUrl, Context context) {
        RetrofitManager retrofitManager;
        Class cls = RetrofitManager.class;
        synchronized (cls) {
            if (mRetrofitManager == null) {
                synchronized (cls) {
                    if (mRetrofitManager == null) {
                        mRetrofitManager = new RetrofitManager(baseUrl, context);
                    }
                }
            }
            retrofitManager = mRetrofitManager;
        }
        return retrofitManager;
    }

    public static OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            synchronized (OkHttpClient.class) {
                if (okHttpClient == null) {
                    try {

                        Builder httpClientBuilder = new Builder();
                        httpClientBuilder.followSslRedirects(false);
                        httpClientBuilder.followRedirects(false);
                        httpClientBuilder.connectTimeout(150000, TimeUnit.SECONDS).readTimeout(150000, TimeUnit.SECONDS).writeTimeout(150000, TimeUnit.SECONDS);
                        OkHttpClient build = httpClientBuilder.build();
                        return build;
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return okHttpClient;
    }

    private void initRetrofit(Context context) {
        this.mRetrofit = new Retrofit.Builder().baseUrl(this.baseUrl).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).client(getOkHttpClient()).build();
    }

    public <T> T setCreate(Class<T> reqServer) {
        return this.mRetrofit.create(reqServer);
    }
}
