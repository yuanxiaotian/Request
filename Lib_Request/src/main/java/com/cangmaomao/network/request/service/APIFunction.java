package com.cangmaomao.network.request.service;

import com.cangmaomao.network.request.BaseBean;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface APIFunction {
    @POST
    @Multipart
    Observable<ResponseBody> uploadFile(@Url String url, @Part("file\"; filename=\"avatar.png\"") RequestBody requestBody, @PartMap Map<String, RequestBody> map);

    @POST
    @Multipart
    Observable<ResponseBody> uploadFile(@Url String url, @Part("file\"; filename=\"avatar.png\"") RequestBody requestBody);

    @GET
    Observable<ResponseBody> download(@Url String url);
}
