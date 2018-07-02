package com.delaroystudios.userauthentication.networking.api.interceptors;

import android.support.annotation.NonNull;


import com.delaroystudios.userauthentication.utils.Constants;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by delaroy on 3/26/18.
 */

public class AuthenticationInterceptor implements Interceptor {
    private String authToken;

    public AuthenticationInterceptor(String authToken) {
        this.authToken = authToken;
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request original = chain.request();
        Request.Builder builder = original.newBuilder()
                .header(Constants.AUTHORIZATION, authToken);
        return chain.proceed(builder.build());
    }
}
