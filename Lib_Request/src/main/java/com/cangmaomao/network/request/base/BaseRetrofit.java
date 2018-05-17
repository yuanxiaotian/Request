package com.cangmaomao.network.request.base;

import io.reactivex.Observable;
import retrofit2.Retrofit;

public interface BaseRetrofit<T,T1> {

    T setRetrofit(Retrofit retrofit);


    T1 getClassApi(T t);
}
