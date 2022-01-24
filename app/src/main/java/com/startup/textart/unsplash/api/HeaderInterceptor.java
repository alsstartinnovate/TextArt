package com.startup.textart.unsplash.api;

import java.io.IOException;

import androidx.annotation.NonNull;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeaderInterceptor implements Interceptor {
    private final String clientId;

    public HeaderInterceptor(String str) {
        this.clientId = str;
    }

    @NonNull
    public Response intercept(Chain chain) throws IOException {
        Request.Builder newBuilder = chain.request().newBuilder();
        return chain.proceed(newBuilder.addHeader("Authorization", "Client-ID " + this.clientId).build());
    }
}
