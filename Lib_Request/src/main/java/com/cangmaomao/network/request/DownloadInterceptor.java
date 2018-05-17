package com.cangmaomao.network.request;

import com.cangmaomao.network.request.base.BaseFileObserver;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class DownloadInterceptor implements Interceptor {

    private BaseFileObserver fileUploadObserver;


    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        return response.newBuilder().body(new ProgressDownResponseBody(response.body(), fileUploadObserver)).build();
    }

    public void setBaseFileObserver(BaseFileObserver<ResponseBody> fileUploadObserver) {
        this.fileUploadObserver = fileUploadObserver;
    }

    public BaseFileObserver getFileUploadObserver() {
        return fileUploadObserver;
    }
}
