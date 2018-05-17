package com.cangmaomao.network.request.base;

public interface BaseFileResponseListener<T> {
    /**
     * 请求成功
     */
    void success(T t);

    /**
     * 进度
     */
    void onProgress(long progress, long total);

    /**
     * 失败
     */
    void fail(String err);
}
