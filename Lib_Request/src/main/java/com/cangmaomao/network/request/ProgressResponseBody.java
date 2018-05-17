package com.cangmaomao.network.request;

import com.cangmaomao.network.request.base.BaseFileObserver;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;

public class ProgressResponseBody extends RequestBody {

    private RequestBody requestBody;
    private BufferedSource bufferedSource;
    private BaseFileObserver<ResponseBody> fileUploadObserver;

    public ProgressResponseBody(File file, BaseFileObserver<ResponseBody> fileUploadObserver) {
        this.requestBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
        this.fileUploadObserver = fileUploadObserver;
    }


    @Override
    public MediaType contentType() {
        return requestBody.contentType();
    }

    @Override
    public long contentLength() throws IOException {
        return requestBody.contentLength();
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {
        CountSink countingSink = new CountSink(sink);
        BufferedSink bufferedSink = Okio.buffer(countingSink);
        requestBody.writeTo(bufferedSink);
        bufferedSink.flush();
    }

    protected final class CountSink extends ForwardingSink {
        private long bytesWritten = 0;

        public CountSink(Sink delegate) {
            super(delegate);
        }

        @Override
        public void write(Buffer source, long byteCount) throws IOException {
            super.write(source, byteCount);
            bytesWritten += byteCount;
            if (fileUploadObserver != null) {
                fileUploadObserver.onProgressChange(bytesWritten, contentLength());
            }
        }
    }
}

