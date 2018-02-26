package com.mwj.core.utils;

import com.squareup.okhttp.*;

import java.io.IOException;

/**
 * Created by microlink on 2017/6/22.
 */
public abstract class HttpUtil {

    private static final OkHttpClient CLIENT = new OkHttpClient();

    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=utf-8");

    private HttpUtil() {
    }

    public static String get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = CLIENT.newCall(request).execute();
        return response.body().string();
    }

    public static String post(String url, String data) throws IOException {
        RequestBody body = RequestBody.create(MEDIA_TYPE, data);
        Request request = new Request
                .Builder()
                .url(url)
                .post(body)
                .build();
        Response response = CLIENT.newCall(request).execute();
        return response.body().string();
    }
}