package com.cangmaomao.network.request;


import android.annotation.SuppressLint;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cangmaomao.network.request.base.BaseFileObserver;
import com.cangmaomao.network.request.cache.SetCookieCache;
import com.cangmaomao.network.request.config.Config;
import com.cangmaomao.network.request.cookie.AbsCookieJar;
import com.cangmaomao.network.request.interceptor.DownloadInterceptor;
import com.cangmaomao.network.request.interceptor.TokenInterceptor;
import com.cangmaomao.network.request.persistence.SharedPrefsCookiePersistor;
import com.cangmaomao.network.request.service.APIFunction;
import com.cangmaomao.network.request.utils.RxSchedulers;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpManage {

    private Retrofit retrofit;
    private AlertDialog dialog;
    private View loading;
    private View loadingErr;
    private ViewGroup mViewGroup;
    private ConstraintLayout.LayoutParams params;
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
        OkHttpClient client;
        if (AbsCookieJar.mContext == null) {
            client = new OkHttpClient.Builder()
                    .addNetworkInterceptor(interceptor)
                    .addInterceptor(downloadInterceptor)
                    .addInterceptor(new TokenInterceptor())
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(10, TimeUnit.SECONDS)
                    .build();
        } else {
            client = new OkHttpClient.Builder()
                    .addNetworkInterceptor(interceptor)
                    .addInterceptor(downloadInterceptor)
                    .addInterceptor(new TokenInterceptor())
                    .cookieJar(new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(AbsCookieJar.mContext)))
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(10, TimeUnit.SECONDS)
                    .build();
        }


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
    public Observable<Object> concat(Observable<?> observable1, Observable<?> observable2) {
        return Observable.concat(observable1, observable2);
    }

    @SuppressWarnings("ALL")
    public Observable<Object> concat(Observable<?> observable1, Observable<?> observable2, Observable<?> observable3) {
        return Observable.concat(observable1, observable2, observable3);
    }

    @SuppressWarnings("ALL")
    public Observable<Object> concat(Observable<?> observable1, Observable<?> observable2, Observable<?> observable3, Observable<?> observable4) {
        return Observable.concat(observable1, observable2, observable3, observable4);
    }

    @SuppressLint("InflateParams")
    public HttpManage loadingView(ViewGroup view, boolean flag) {
        if (loadingErr != null) {
            mViewGroup.removeView(loadingErr);
            removeViewParent(loadingErr);
        }

        if (loading != null) {
            mViewGroup.removeView(loading);
            removeViewParent(loading);
        }

        this.mViewGroup = view;

        if (loading == null) {
            loading = LayoutInflater.from(view.getContext()).inflate(R.layout.loading_view, null);
        }
        if (flag) {
            if (dialog == null) {
                dialog = new AlertDialog.Builder(view.getContext(), R.style.DialogTheme).create();
                dialog.setView(LayoutInflater.from(view.getContext()).inflate(R.layout.dialog_view, null));
            }
            dialog.show();
        } else {
            int size = view.getChildCount();
            for (int i = 0; i < size; i++) {
                View childAt = view.getChildAt(i);
                if (!(childAt instanceof Toolbar)) {
                    childAt.setVisibility(View.GONE);
                }
            }
            layoutParams(loading);
            mViewGroup.addView(loading);
        }
        return this;
    }

    @SuppressLint("InflateParams")
    public void loadingErr(View.OnClickListener onClickListener) {
        mViewGroup.removeView(loading);
        if (loadingErr == null) {
            loadingErr = LayoutInflater.from(mViewGroup.getContext()).inflate(R.layout.loading_err, null);
            loadingErr.setOnClickListener(onClickListener);
            layoutParams(loadingErr);
        }
        mViewGroup.addView(loadingErr);
    }

    private void layoutParams(View view) {
        if (params == null) {
            params = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
            params.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
            params.leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID;
            params.rightToRight = ConstraintLayout.LayoutParams.PARENT_ID;
            params.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID;
        }
        view.setLayoutParams(params);
    }

    private void removeViewParent(View view) {
        if (view == null) return;
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        if (viewGroup == null) return;
        viewGroup.removeView(view);
    }

    public void loadingEnd() {
        if (dialog != null) {
            dialog.dismiss();
            return;
        }
        int size = mViewGroup.getChildCount();
        for (int i = 0; i < size; i++) {
            View childAt = mViewGroup.getChildAt(i);
            if (!(childAt instanceof Toolbar)) {
                childAt.setVisibility(View.VISIBLE);
            }
        }
        mViewGroup.removeView(loading);
    }

}
