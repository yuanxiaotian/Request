package com.cangmaomao.network.request;


import com.cangmaomao.network.request.base.BaseFileObserver;
import com.cangmaomao.network.request.config.Config;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpManage {

    private Retrofit retrofit;
    private DownloadInterceptor downloadInterceptor;

    private static class HttpManageHolder {
        private static final HttpManage INSTANCE = new HttpManage();
    }

    public static HttpManage getInstance() {
        return HttpManageHolder.INSTANCE;
    }


    HttpManage() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        downloadInterceptor = new DownloadInterceptor();
        OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(interceptor)
                .addInterceptor(downloadInterceptor)
                .retryOnConnectionFailure(true)
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(Config.S_HTTP_ROOT_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }


    @SuppressWarnings("ALL")
    public void map(Class clazz, String methodName, Map<String, Object> param, Observer observer) {
        try {
            Method[] methods = clazz.getDeclaredMethods();
            for (int i = 0; i < methods.length; i++) {
                if (methodName.equals(methods[i].getName())) {
                    Object obj = retrofit.create(clazz);
                    post(observer, (Observable) methods[i].invoke(obj, param));
                    return;
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("ALL")
    public void upLoadFile(Class<?> clazz, String methodName, File file, Map<String, Object> param, Observer observer) {
        try {
            Method[] methods = clazz.getDeclaredMethods();
            for (int i = 0; i < methods.length; i++) {
                if (methodName.equals(methods[i].getName())) {
                    Object obj = retrofit.create(clazz);
                    RequestBody body = new ProgressResponseBody(file, (BaseFileObserver) observer);
                    Observable observable = (Observable) methods[i].invoke(obj, body, param);
                    post(observer, observable);
                    return;
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.getTargetException().printStackTrace();
        }
    }

    @SuppressWarnings("ALL")
    public void downFile(Class<?> clazz, String methodName, Map<String, Object> param, BaseFileObserver observer) {
        if (downloadInterceptor.getFileUploadObserver() == null) {
            downloadInterceptor.setBaseFileObserver(observer);
        }
        try {
            Method[] methods = clazz.getDeclaredMethods();
            for (int i = 0; i < methods.length; i++) {
                if (methodName.equals(methods[i].getName())) {
                    Object obj = retrofit.create(clazz);
                    post(observer, (Observable) methods[i].invoke(obj, param));
                    return;
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    @SuppressWarnings("ALL")
    private <K1, K2> void post(Observer<K1> observer, Observable<K2> observable) {
        observable.map(new Function<K2, K1>() {
            @Override
            public K1 apply(@NonNull K2 t2) throws Exception {
                return (K1) t2;
            }
        })
                .subscribeOn(Schedulers.io())//指定网络请求在io线程
                .observeOn(AndroidSchedulers.mainThread())//指定返回结果处理在主线程，这样我们就可以在onnext中更新ui了
                .subscribe(observer);
    }

}
