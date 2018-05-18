package com.cangmaomao.network.request.base;

import com.cangmaomao.network.request.RxHttpMange;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class BaseFileObserver<T> implements Observer<T> {

    private String tag;

    public BaseFileObserver(String tag) {
        this.tag = tag;
    }

    @Override
    public void onSubscribe(Disposable d) {
        RxHttpMange.getInstance().add(tag, d);
    }

    @Override
    public void onNext(T t) {
        success(t);
    }

    @Override
    public void onError(Throwable e) {
        fail(e.getMessage());
    }

    @Override
    public void onComplete() {

    }

    public void onProgressChange(long bytesWritten, long contentLength) {
        progress(bytesWritten, contentLength);
    }


    //请求成功
    public abstract void success(T t);

    //失败
    public abstract void fail(String err);

    //进度
    public abstract void progress(long bytesWritten, long contentLength);
}


