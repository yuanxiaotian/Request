package com.cangmaomao.network.request;

import com.cangmaomao.network.request.base.BaseFileObserver;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ForwardingSink;
import okio.ForwardingSource;
import okio.Okio;
import okio.Sink;
import okio.Source;

public class ProgressDownResponseBody extends ResponseBody {

    private ResponseBody responseBody;
    private BufferedSource bufferedSource;
    private BaseFileObserver<ResponseBody> fileUploadObserver;

    public ProgressDownResponseBody(ResponseBody responseBody, BaseFileObserver<ResponseBody> fileUploadObserver) {
        this.responseBody = responseBody;
        this.fileUploadObserver = fileUploadObserver;
    }


    @Override
    public MediaType contentType() {
        return responseBody.contentType();
    }

    @Override
    public long contentLength() {
        return responseBody.contentLength();
    }

    @Override
    public BufferedSource source() {
        if (bufferedSource == null) {
            bufferedSource = Okio.buffer(source(responseBody.source()));
        }
        return bufferedSource;
    }

    private Source source(Source source) {
        return new ForwardingSource(source) {
            long totalBytesRead = 0L;

            @Override
            public long read(Buffer sink, long byteCount) throws IOException {
                long bytesRead = super.read(sink, byteCount);
                totalBytesRead += bytesRead != -1 ? bytesRead : 0;
                if (fileUploadObserver != null) {
                    fileUploadObserver.onProgressChange(totalBytesRead, contentLength());
                }
                return bytesRead;
            }
        };

    }

}

