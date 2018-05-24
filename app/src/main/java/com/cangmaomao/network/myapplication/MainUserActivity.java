package com.cangmaomao.network.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.cangmaomao.network.request.HttpManage;
import com.cangmaomao.network.request.base.BaseFileObserver;
import com.cangmaomao.network.request.base.BaseObserver;
import com.cangmaomao.network.request.utils.RxSchedulers;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;

import me.nereo.multi_image_selector.MultiImageSelector;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class MainUserActivity extends AppCompatActivity {


    private static final String TAG = MainUserActivity.class.getSimpleName();

    private String toTen;
    private int totalLength = 0;
    private long bytes = 0;
    private long curr = 0;
    private Map<String, Object> map;
    private ProgressBar progressBar, progressBar2;
    private String url = "http://www.seteat.com/Login/app_client_login";
    private String url_a = "http://www.seteat.com/Upload";
    private String url_b = "https://codeload.github.com/yuanxiaotian/Request/zip/master";


    private TextView text3, text4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        progressBar2 = findViewById(R.id.progressBar2);
        text3 = findViewById(R.id.text3);
        text4 = findViewById(R.id.text4);

        map = new HashMap<>();
        map.put("devid", "6af67ec1-a504-3848-ba8c-4fa32eaefba9");
        map.put("ct", 1);
        map.put("password", 1212);
        map.put("loginid", 1212);

//        HttpManage.getInstance()
//                .create(UserApi.class)
//                .post(url, map)
//                .compose(RxSchedulers.<Login>io_main())
//                .subscribe(new BaseObserver<Login>(TAG) {
//                    @Override
//                    public void success(Login login) {
//                        toTen = login.getData().getToken();
//                    }
//
//                    @Override
//                    public void fail(String err) {
//
//                    }
//                });

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

        findViewById(R.id.text2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                curr = System.currentTimeMillis();
                HttpManage.getInstance().downFile(url_b, new BaseFileObserver<ResponseBody>(TAG) {
                    @Override
                    public void success(ResponseBody body) {

                    }

                    @Override
                    public void fail(String err) {

                    }

                    @Override
                    public void progress(final long bytesWritten, final long contentLength) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (totalLength == 0) {
                                    totalLength = (int) contentLength;
                                    progressBar2.setMax(totalLength);
                                }
                                progressBar2.setProgress((int) bytesWritten);

                                float a = (float) contentLength / 1024 / 1024;

                                float b = (float) bytesWritten / 1024 / 1024;

                                text4.setText(FloatUtils.keepTwo(b) + "MB  /" + FloatUtils.keepTwo(a) + "MB");

                                long tmpCurr = System.currentTimeMillis();
                                long s = (tmpCurr - curr) / 1000;
                                curr = tmpCurr;

                                Log.e("s", "s:" + s);


                                if (s > 0) {
                                    long ss = (bytesWritten - bytes) / s;
                                    bytes = bytesWritten;
                                    text3.setText(ss + "KB/s");
                                    Log.e("ss", "ss:" + ss);
                                }
                            }
                        });

                    }
                });
            }
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1001 && resultCode == Activity.RESULT_OK) {
            String path = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT).get(0);
            File file = new File(path);

            Map<String, RequestBody> map = new HashMap<>();
            map.put("devid", RequestBody.create(MediaType.parse("text/plain"), "6af67ec1-a504-3848-ba8c-4fa32eaefba9"));
            map.put("ptype", RequestBody.create(MediaType.parse("text/plain"), "headpic"));
            map.put("apptoken", RequestBody.create(MediaType.parse("text/plain"), toTen));

            HttpManage.getInstance()
                    .upLoadFile(url_a, file, map, new BaseFileObserver<ResponseBody>(TAG) {
                        @Override
                        public void success(ResponseBody body) {
                            String json = null;
                            try {
                                json = body.string();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            BaseBean baseBean = null;
                            if (!TextUtils.isEmpty(json)) {
                                baseBean = new Gson().fromJson(json, BaseBean.class);
                            }
                            Toast toast = Toast.makeText(MainUserActivity.this, baseBean.getMessage(), Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.BOTTOM, 0, 0);
                            toast.show();
                        }

                        @Override
                        public void fail(String err) {

                        }

                        @Override
                        public void progress(long bytesWritten, long contentLength) {
                            if (totalLength == 0) {
                                totalLength = (int) contentLength;
                                progressBar.setMax(totalLength);
                            }
                            progressBar.setProgress((int) bytesWritten);
                        }
                    });


            super.onActivityResult(requestCode, resultCode, data);
        }


    }
}
