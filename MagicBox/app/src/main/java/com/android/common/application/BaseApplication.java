package com.android.common.application;

import android.app.Application;
import android.util.Log;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.squareup.okhttp.OkHttpClient;
import com.zhy.http.okhttp.OkHttpClientManager;

import java.util.concurrent.TimeUnit;

/**
 * Created by wangjundong on 2015/11/25.
 */
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("BaseApplication", "start......");

        OkHttpClient client =
                OkHttpClientManager.getInstance().getOkHttpClient();
        client.setConnectTimeout(30000, TimeUnit.MILLISECONDS);

        Fresco.initialize(getApplicationContext());
    }

}
