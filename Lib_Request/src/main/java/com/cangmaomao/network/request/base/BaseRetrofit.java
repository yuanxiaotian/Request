package com.cangmaomao.network.request.base;

import io.reactivex.Observable;

public interface BaseRetrofit<T> {

    Observable getClassApi(T t);
}
