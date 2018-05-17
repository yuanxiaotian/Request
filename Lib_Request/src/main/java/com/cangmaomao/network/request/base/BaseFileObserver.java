package com.cangmaomao.network.request.base;

import com.cangmaomao.network.request.RxHttpMange;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class BaseFileObserver<T> implements Observer<T> {

    private String tag;

    private BaseFileResponseListener<T> listener;

    public BaseFileObserver(String tag, BaseFileResponseListener listener) {
        this.listener = listener;
        this.tag = tag;
    }


    @Override
    public void onSubscribe(Disposable d) {
        RxHttpMange.getInstance().add(tag, d);
    }

    @Override
    public void onNext(T t) {
        listener.success(t);
    }

    @Override
    public void onError(Throwable e) {
        listener.fail(e.getMessage());
    }

    @Override
    public void onComplete() {

    }

    public void onProgressChange(long bytesWritten, long contentLength) {
        listener.onProgress(bytesWritten, contentLength);
    }
}


