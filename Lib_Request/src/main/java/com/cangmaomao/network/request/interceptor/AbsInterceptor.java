package com.cangmaomao.network.request.interceptor;

import java.io.IOException;

import io.reactivex.annotations.NonNull;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 更新2018/06/11 23:05
 */
public class AbsInterceptor {

    @NonNull
    public static Interceptor writeInterceptor = chain -> chain.proceed(chain.request());

    @NonNull
    public static Interceptor readInterceptor = chain -> chain.proceed(chain.request().newBuilder().build());


}
