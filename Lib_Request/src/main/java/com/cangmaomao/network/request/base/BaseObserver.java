package com.cangmaomao.network.request.base;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class BaseObserver<T> implements Observer<T> {


    @Override
    public void onSubscribe(Disposable d) {
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
