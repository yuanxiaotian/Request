package com.cangmaomao.network.request.base;

public interface BaseResponseListener<T> {

    /**
     * 请求成功
     */
    void success(T t);

    /**
     * 失败
     */
    void fail(String err);


}
