package com.cangmaomao.network.request;


import com.cangmaomao.network.request.base.BaseFileObserver;
import com.cangmaomao.network.request.base.BaseObserver;
import com.cangmaomao.network.request.config.Config;
import com.cangmaomao.network.request.cookie.AbsCookieJar;
import com.cangmaomao.network.request.interceptor.DownloadInterceptor;
import com.cangmaomao.network.request.service.APIFunction;
import com.cangmaomao.network.request.utils.RxSchedulers;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.prefs.Preferences;

import io.reactivex.Observable;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
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
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        downloadInterceptor = new DownloadInterceptor();
        client = new OkHttpClient.Builder()
                .addNetworkInterceptor(interceptor)
                .addInterceptor(downloadInterceptor)
                .cookieJar(new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(AbsCookieJar.getContext())))
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

    public <T> T create(Class<T> clazz) {
        return retrofit.create(clazz);
    }

    /**
     * 单上传文件的封装 多参数
     *
     * @param url                完整的接口地址
     * @param file               需要上传的文件
     * @param fileUploadObserver 上传回调
     */
    @SuppressWarnings("ALL")
    public void upLoadFile(String url, File file, Map<String, RequestBody> map, BaseFileObserver fileUploadObserver) {
        create(APIFunction.class)
                .uploadFile(url, new ProgressResponseBody(file, fileUploadObserver), map)
                .compose(RxSchedulers.io_main())
                .subscribe(fileUploadObserver);
    }

    /**
     * 单上传文件的封装 无参数
     *
     * @param url                完整的接口地址
     * @param file               需要上传的文件
     * @param fileUploadObserver 上传回调
     */
    @SuppressWarnings("ALL")
    public void upLoadFile(String url, File file, BaseFileObserver fileUploadObserver) {
        create(APIFunction.class)
                .uploadFile(url, new ProgressResponseBody(file, fileUploadObserver))
                .compose(RxSchedulers.io_main())
                .subscribe(fileUploadObserver);
    }


    /**
     * 单下载文件的封装 无参数
     *
     * @param url                完整的接口地址
     * @param fileUploadObserver 上传回调
     */
    @SuppressWarnings("ALL")
    public void downFile(String url, BaseFileObserver observer) {
        if (downloadInterceptor.getFileUploadObserver() == null) {
            downloadInterceptor.setBaseFileObserver(observer);
        }
        create(APIFunction.class)
                .download(url)
                .compose(RxSchedulers.io_main())
                .subscribe(observer);
    }

    @SuppressWarnings("ALL")
    public void concat(Observable<?> observable1, Observable<?> observable2, BaseObserver observer) {
        Observable.concat(observable1, observable2)
                .compose(RxSchedulers.io_main())
                .subscribe(observer);
    }

    @SuppressWarnings("ALL")
    public void concat(Observable<?> observable1, Observable<?> observable2, Observable<?> observable3, BaseObserver observer) {
        Observable.concat(observable1, observable2, observable3)
                .compose(RxSchedulers.io_main())
                .subscribe(observer);
    }


}
