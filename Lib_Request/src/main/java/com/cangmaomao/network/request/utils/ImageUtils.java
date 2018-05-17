package com.cangmaomao.network.request.utils;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.ByteArrayOutputStream;

/**
 * 图片帮助类
 */

public class ImageUtils {


    public static byte[] qualityCompress(String filePath) {
        Bitmap bm = null;
        Bitmap bit = BitmapFactory.decodeFile(filePath);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bit.compress(Bitmap.CompressFormat.JPEG, 20, baos);
        byte[] bytes = baos.toByteArray();
        bm = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        Log.i("wechat", "压缩后图片的大小" + (bm.getByteCount() / 1024 / 1024)
                + "M宽度为" + bm.getWidth() + "高度为" + bm.getHeight()
                + "bytes.length=  " + (bytes.length / 1024) + "KB"
        );
        return bytes;
    }


}
