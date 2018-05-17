package com.cangmaomao.network.request;

import android.util.ArrayMap;
import android.util.SparseArray;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class RxHttpMange {

    private static class HttpManageHolder {
        private static final RxHttpMange INSTANCE = new RxHttpMange();
    }

    public static RxHttpMange getInstance() {
        return HttpManageHolder.INSTANCE;
    }

    //用于存放单个请求
    private Map<String, Disposable> map;
    //请求队列
    private CompositeDisposable compositeDisposable;


    RxHttpMange() {
        map = new HashMap<>();
        compositeDisposable = new CompositeDisposable();
    }


    /**
     * 添加单个请求到sp
     */
    public void add(String tag, Disposable disposable) {
        map.put(tag, disposable);
        compositeDisposable.add(disposable);
    }

    /**
     * 移除某一个请求
     */
    public void remove(String tag) {
        if (map == null) {
            throw new NullPointerException(String.format("当前暂无任何请求", tag));
        }
        if (map.isEmpty()) return;
        if (map.get(tag) == null) return;
        compositeDisposable.remove(map.get(tag));
        map.remove(tag);
    }

    /**
     * 移除所有请求
     */
    public void removeAll() {
        if (map == null) return;
        if (map.isEmpty()) return;
        compositeDisposable.clear();
        map.clear();

    }
}
