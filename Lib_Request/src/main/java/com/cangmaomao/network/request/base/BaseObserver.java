package com.cangmaomao.network.request.base;

import com.cangmaomao.network.request.RxHttpMange;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class BaseObserver<T> implements Observer<T> {

    private String tag;

    private BaseResponseListener<T> listener;

    public BaseObserver(String tag, BaseResponseListener listener) {
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
}
