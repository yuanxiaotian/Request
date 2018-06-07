package com.cangmaomao.network.request.interceptor;

import java.io.IOException;

import io.reactivex.annotations.NonNull;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AbsInterceptor {

    @NonNull
    public static Interceptor writeInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            return chain.proceed(chain.request());
        }
    };

    @NonNull
    public static Interceptor readInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request.Builder builder = chain.request().newBuilder();
            return chain.proceed(builder.build());
        }
    };


}
