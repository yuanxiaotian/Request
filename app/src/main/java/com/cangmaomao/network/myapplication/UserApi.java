package com.cangmaomao.network.myapplication;

import android.util.Log;

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
import retrofit2.http.Path;

public interface UserApi {


    @POST("Login/app_client_login")
    @FormUrlEncoded
    Observable<Login> login(@FieldMap Map<String, Object> map);


    @POST("Upload")
    @Multipart
    Observable<Object> userHeadUpdate(@Part("file\"; filename=\"avatar.png\"") RequestBody requestBody,@PartMap Map<String, RequestBody> map);
}
