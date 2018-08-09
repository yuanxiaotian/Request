package com.cangmaomao.network.request.interceptor;

import android.text.TextUtils;

import com.cangmaomao.network.request.config.Config;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class TokenInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (TextUtils.isEmpty(Config.TO_KEN_VAL)) {
            return chain.proceed(request);

        }
        Request updateRequest = request.newBuilder()
                .header(Config.TO_KEN_KEY, Config.TO_KEN_VAL).build();
        return chain.proceed(updateRequest);
    }

}
