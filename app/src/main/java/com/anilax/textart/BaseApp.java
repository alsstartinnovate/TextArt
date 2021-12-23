package com.anilax.textart;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

import com.android.volley.RequestQueue;

public class BaseApp extends Application {
    private static final int SCHEMA_VERSION = 0;
    public static final String TAG = BaseApp.class.getSimpleName();
    private RequestQueue mRequestQueue;
    private static BaseApp mInstance;

    public static BaseApp getInstance(Context context) {
        return (BaseApp) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
