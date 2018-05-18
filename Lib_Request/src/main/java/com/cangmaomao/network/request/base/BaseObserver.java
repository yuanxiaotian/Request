package com.cangmaomao.network.request.base;

import com.cangmaomao.network.request.RxHttpMange;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class BaseObserver<T> implements Observer<T> {

    private String tag;

    protected BaseObserver(String Tag) {
        tag = tag;
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

    /**
     * 请求成功
     */
    public abstract void success(T t);

    /**
     * 失败
     */
    public abstract void fail(String err);
}
