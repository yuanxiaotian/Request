package com.cangmaomao.network.myapplication;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Url;

public interface UserApi {

    @POST
    @FormUrlEncoded
    Observable<Login> post(@Url String url, @FieldMap Map<String, Object> map);

    @POST
    @Multipart
    Observable<Object> userHeadUpdate(@Url String url, @Part("file\"; filename=\"avatar.png\"") RequestBody requestBody, @PartMap Map<String, RequestBody> map);

}
