package com.cangmaomao.network.request.base;

import retrofit2.Retrofit;

public interface BaseFileRetrofit<T, T1, T2> {

    T setRetrofit(Retrofit retrofit);


    T1 getClassApi(T t, T2 T2);
}
