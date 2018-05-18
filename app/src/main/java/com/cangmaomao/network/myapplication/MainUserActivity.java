package com.cangmaomao.network.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.cangmaomao.network.request.HttpManage;
import com.cangmaomao.network.request.base.BaseFileObserver;
import com.cangmaomao.network.request.base.BaseObserver;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import me.nereo.multi_image_selector.MultiImageSelector;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class MainUserActivity extends AppCompatActivity {


    private static final String TAG = MainUserActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Map<String, Object> map = new HashMap<>();
        map.put("devid", "6af67ec1-a504-3848-ba8c-4fa32eaefba9");
        map.put("ct", 1);
        map.put("password", 1212);
        map.put("loginid", 1212);


        HttpManage.getInstance()
                .map(UserApi.class, "login", map, new BaseObserver<Login>(TAG) {
                    @Override
                    public void success(Login login) {
                    }

                    @Override
                    public void fail(String err) {
                    }
                });


        findViewById(R.id.text1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MultiImageSelector.create()
                        .showCamera(false) // show camera or not. true by default
                        .count(1) // max select image size, 9 by default. used width #.multi()
                        .single() // single mode
                        .multi() // multi mode, default mode;
                        .start(MainUserActivity.this, 1001);
            }
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1001 && resultCode == Activity.RESULT_OK) {
            String path = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT).get(0);
            File file = new File(path);
            Map<String, Object> map = new HashMap<>();
            map.put("devid", RequestBody.create(MediaType.parse("text/plain"), "6af67ec1-a504-3848-ba8c-4fa32eaefba9"));
            map.put("ptype", RequestBody.create(MediaType.parse("text/plain"), "headpic"));
            map.put("apptoken", RequestBody.create(MediaType.parse("text/plain"), "515bphu1anOdGJg3esOPnYwTZVhjEm5Ay7Ubl5nxfyNsJiB8dcjPmF70t0f7XO+bMrrJzsdJibvjkRI2xoSz1Dt9G+5ZMAn+VlKrXFAegNyk2cOZxb3uSZczd5YJMXo9ZPWX7gHjZmSgbA"));
            HttpManage.getInstance()
                    .upLoadFile(UserApi.class, "userHeadUpdate", file, map, new BaseFileObserver(TAG) {
                        @Override
                        public void success(Object o) {

                        }

                        @Override
                        public void fail(String err) {

                        }

                        @Override
                        public void progress(long bytesWritten, long contentLength) {

                        }
                    });
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


}
