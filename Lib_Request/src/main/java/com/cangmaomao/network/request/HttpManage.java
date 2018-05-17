package com.cangmaomao.network.request;


import com.cangmaomao.network.request.base.BaseFileObserver;
import com.cangmaomao.network.request.base.BaseFileResponseListener;
import com.cangmaomao.network.request.base.BaseFileRetrofit;
import com.cangmaomao.network.request.base.BaseRetrofit;
import com.cangmaomao.network.request.bean.Base;
import com.cangmaomao.network.request.config.Config;
import com.cangmaomao.network.request.utils.StringUtils;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpManage {

    private Retrofit retrofit;
    private OkHttpClient client;
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
        client = new OkHttpClient.Builder()
                .addNetworkInterceptor(interceptor)
                .addInterceptor(downloadInterceptor)
                .retryOnConnectionFailure(true)
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(Config.S_HTTP_ROOT_URL_a)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    private <T, K> T createClass(BaseRetrofit<T, K> baseRetrofit) {
        return baseRetrofit.setRetrofit(retrofit);
    }

    private <T, K, H> T createClass(BaseFileRetrofit<T, K, H> baseRetrofit) {
        return baseRetrofit.setRetrofit(retrofit);
    }

    @SuppressWarnings("ALL")
    public void map(BaseRetrofit baseRetrofit, Observer observer) {
        post(observer, (Observable) baseRetrofit.getClassApi(createClass(baseRetrofit)));
    }

    @SuppressWarnings("ALL")
    public void upLoadFile(File file, BaseFileRetrofit baseRetrofit, Observer observer) {
        final ProgressResponseBody responseBody = new ProgressResponseBody(file, (BaseFileObserver<ResponseBody>) observer);
        post(observer, (Observable) baseRetrofit.getClassApi(createClass(baseRetrofit), responseBody));
    }

    @SuppressWarnings("ALL")
    public void downFile(BaseRetrofit baseRetrofit, BaseFileObserver observer) {
        if (downloadInterceptor.getFileUploadObserver() == null) {
            downloadInterceptor.setBaseFileObserver(observer);
        }
        post(observer, (Observable) baseRetrofit.getClassApi(createClass(baseRetrofit)));
    }


    @SuppressWarnings("ALL")
    public <K1, K2> void post(Observer<K1> observer, Observable<K2> observable) {
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
