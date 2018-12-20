package com.cangmaomao.network.request.interceptor;

import com.cangmaomao.network.request.HttpManage;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Author:帅气的potato
 */

public class Retry implements Interceptor{

    public int maxRetry;//最大请求数
    private int retryNum=1;//默认请求的数

    public Retry(int maxRetry){
        this.maxRetry = maxRetry;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        while (!response.isSuccessful()&& retryNum < maxRetry){
            retryNum++;
            response = chain.proceed(request);
        }
        if (retryNum >= maxRetry){
            if (!response.isSuccessful()){
                HttpManage.getInstance().loadingEnd();
            }
        }
        return response;
    }

}
